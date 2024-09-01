package com.unibuc.event.ticketing.service;

import com.unibuc.event.ticketing.dto.order.OrderInfoDto;
import com.unibuc.event.ticketing.dto.order.OrderPlacedDto;
import com.unibuc.event.ticketing.dto.order.PlaceOrderDto;
import com.unibuc.event.ticketing.exception.*;
import com.unibuc.event.ticketing.model.Order;
import com.unibuc.event.ticketing.model.Ticket;
import com.unibuc.event.ticketing.repository.OrderRepository;
import com.unibuc.event.ticketing.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private UserService userService;
    @Autowired
    private EventService eventService;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private TicketRepository ticketRepository;
    public Order getOrder(String orderId) throws OrderNotFoundException {
        var order = orderRepository.findById(orderId);
        if (order.isPresent()) return order.get();
        else throw new OrderNotFoundException();
    }
    public OrderPlacedDto placeOrder(PlaceOrderDto placeOrder) throws UserNotFoundException, EventNotFoundException, AccountNotFoundException, NotEnoughEventSeatsException {
        var user = userService.getUser(placeOrder.getUserId());
        var account = userService.getAccount(user.getAccount().getAccountId());
        var event = eventService.getEvent(placeOrder.getEventId());

        var order = new Order();
        var tickets = new ArrayList<Ticket>();

        var number = placeOrder.getNumberOfTickets();
        if (event.getSeats() < number) throw new NotEnoughEventSeatsException();
        for (int i = 1; i <= number; i++) {
            tickets.add(new Ticket(event.getTicketPrice(), event, order));
        }

        order.setAccount(account);
        order.setTickets(tickets);
        var savedOrder = orderRepository.save(order);

        event.setSeats(event.getSeats() - number);
        eventService.saveEvent(event);

        var orderId = savedOrder.getOrderId();
        return new OrderPlacedDto(orderId);
    }

    public List<OrderInfoDto> getUserOrders(String userId) throws UserNotFoundException, AccountNotFoundException {
        var user = userService.getUser(userId);
        var account = userService.getAccount(user.getAccount().getAccountId());

        var orders = account.getOrders();
        var ordersList = new ArrayList<OrderInfoDto>();

        for (Order order: orders) {
            var tickets = order.getTickets();
            int ticketsNumber = tickets.size();
            Integer orderPrice = tickets.stream().mapToInt(Ticket::getPrice).sum();

            String eventId = null;
            if (ticketsNumber != 0) {
                eventId = tickets.get(0).getEvent().getEventId();
            }

            var ticketIds = tickets.stream().map(Ticket::getTicketId).toList();
            var orderInfo = new OrderInfoDto(order.getOrderNumber(), ticketsNumber, orderPrice, eventId, ticketIds);
            ordersList.add(orderInfo);
        }

        return ordersList;
    }
}
