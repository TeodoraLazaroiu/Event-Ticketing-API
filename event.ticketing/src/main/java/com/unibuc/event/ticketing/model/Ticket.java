package com.unibuc.event.ticketing.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String ticketId;
    private Integer price;
    @ManyToOne
    @JoinColumn(name = "orderId")
    private Order order;
    @ManyToOne
    @JoinColumn(name = "eventId")
    private Event event;

    public Ticket(Integer price, Event event, Order order) {
        this.price = price;
        this.event = event;
        this.order = order;
    }
}
