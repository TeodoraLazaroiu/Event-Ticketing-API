package com.unibuc.event.ticketing.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    private String orderId;
    @ManyToOne
    @JoinColumn(name = "accountId")
    private Account account;
    @OneToMany(mappedBy = "order")
    private List<Ticket> tickets;
}
