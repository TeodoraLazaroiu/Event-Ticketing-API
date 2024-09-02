package com.unibuc.event.ticketing.service;

import com.unibuc.event.ticketing.dto.order.OrderInfoDto;
import com.unibuc.event.ticketing.dto.order.OrderPlacedDto;
import com.unibuc.event.ticketing.dto.order.PlaceOrderDto;
import com.unibuc.event.ticketing.model.*;
import com.unibuc.event.ticketing.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {
    @Mock
    private UserService userService;
    @Mock
    private EventService eventService;
    @Mock
    private OrderRepository orderRepository;
    @InjectMocks
    private OrderService orderService;
    private final Event event = new Event("eventId", "test", "", 100, 50);
    private final User user = new User("userId", "test@email.com", "password", new Account());
    private final Account account = new Account("accountId", "test", new User(), new ArrayList<>());
    private final Order order = new Order("orderId", 1);
    private final Ticket ticket = new Ticket("ticketId", 50);

    @BeforeEach
    public void setup() {
        ticket.setEvent(event);
        ticket.setOrder(order);
        order.setTickets(List.of(ticket));

        account.setUser(user);
        account.setOrders(List.of(order));
        user.setAccount(account);
    }

    @Test
    public void placeOrderTest() throws Exception {
        var placeOrder = new PlaceOrderDto(user.getUserId(), event.getEventId(), 1);

        when(userService.getUser(user.getUserId())).thenReturn(user);
        when(userService.getAccount(account.getAccountId())).thenReturn(account);
        when(eventService.getEvent(event.getEventId())).thenReturn(event);
        when(orderRepository.save(any(Order.class))).thenReturn(order);

        var result = orderService.placeOrder(placeOrder);

        Mockito.verify(orderRepository).save(any(Order.class));
        Mockito.verify(eventService).saveEvent(any(Event.class));

        assertThat(result).isNotNull();
        assertThat(result).isInstanceOf(OrderPlacedDto.class);
    }

    @Test
    public void getOrdersTest() throws Exception {
        when(userService.getUser(user.getUserId())).thenReturn(user);
        when(userService.getAccount(account.getAccountId())).thenReturn(account);

        var result = orderService.getUserOrders(user.getUserId());

        assertThat(result).isNotNull();
        assertThat(result).isInstanceOf(List.class);
        assertThat(result.get(0)).isInstanceOf(OrderInfoDto.class);
    }
}
