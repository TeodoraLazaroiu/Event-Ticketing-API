package com.unibuc.event.ticketing.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unibuc.event.ticketing.dto.order.OrderInfoDto;
import com.unibuc.event.ticketing.dto.order.OrderPlacedDto;
import com.unibuc.event.ticketing.dto.order.PlaceOrderDto;
import com.unibuc.event.ticketing.model.Order;
import com.unibuc.event.ticketing.model.Ticket;
import com.unibuc.event.ticketing.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderController.class)
public class OrderControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private OrderService orderService;
    @Autowired
    private ObjectMapper objectMapper;
    private final String userId = "userId";
    private final String eventId = "eventId";
    private final Order order = new Order("orderId", 1);
    private final Ticket ticket = new Ticket("ticketId", 50);

    @BeforeEach
    public void setup() {
        ticket.setOrder(order);
        order.setTickets(List.of(ticket));
    }

    @Test
    public void placeOrderTest() throws Exception {
        var placeOrder = new PlaceOrderDto(userId, eventId, 1);
        when(orderService.placeOrder(placeOrder)).thenReturn(new OrderPlacedDto(order.getOrderId()));

        var response = mockMvc.perform(post("/api/order")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(placeOrder)));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.orderId", is(order.getOrderId())));
    }

    @Test
    public void getOrdersTest() throws Exception {
        var numberOfTickets = order.getTickets().size();
        var orderPrice = numberOfTickets * ticket.getPrice();
        var ticketIds = List.of(ticket.getTicketId());

        var orderInfo = new OrderInfoDto(order.getOrderNumber(), numberOfTickets, orderPrice, eventId, ticketIds);
        when(orderService.getUserOrders(userId)).thenReturn(List.of(orderInfo));

        var response = mockMvc.perform(get("/api/order?userId=" + userId));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].orderNumber", is(order.getOrderNumber())))
                .andExpect(jsonPath("$.[0].numberOfTickets", is(numberOfTickets)))
                .andExpect(jsonPath("$.[0].orderPrice", is(orderPrice)))
                .andExpect(jsonPath("$.[0].eventId", is(eventId)))
                .andExpect(jsonPath("$.[0].ticketIds[0]", is(ticketIds.get(0))));
    }
}
