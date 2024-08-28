package com.unibuc.event.ticketing.service;

import com.unibuc.event.ticketing.dto.order.OrderInfoDto;
import com.unibuc.event.ticketing.dto.order.OrderPlacedDto;
import com.unibuc.event.ticketing.dto.order.PlaceOrderDto;
import com.unibuc.event.ticketing.exception.AccountNotFoundException;
import com.unibuc.event.ticketing.exception.EventNotFoundException;
import com.unibuc.event.ticketing.exception.NotEnoughEventSeatsException;
import com.unibuc.event.ticketing.exception.UserNotFoundException;
import com.unibuc.event.ticketing.model.Order;
import com.unibuc.event.ticketing.model.Ticket;
import com.unibuc.event.ticketing.repository.OrderRepository;
import com.unibuc.event.ticketing.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class OrderService {
    @Autowired
    private UserService userService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private EventService eventService;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private TicketRepository ticketRepository;
    public OrderPlacedDto placeOrder(PlaceOrderDto placeOrder) throws UserNotFoundException, EventNotFoundException, AccountNotFoundException, NotEnoughEventSeatsException {
        var user = userService.getUser(placeOrder.getUserId());
        var account = accountService.getAccount(user.getAccount().getAccountId());
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

        var orderNumber = savedOrder.getOrderNumber();
        return new OrderPlacedDto(orderNumber);
    }
}
