package com.unibuc.event.ticketing.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String orderId;
    @Column(insertable = false, updatable = false, columnDefinition="serial")
    private Integer orderNumber;
    @ManyToOne
    @JoinColumn(name = "accountId")
    private Account account;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order", fetch = FetchType.EAGER)
    private List<Ticket> tickets;

    public Order(String orderId, Integer orderNumber) {
        this.orderId = orderId;
        this.orderNumber = orderNumber;
    }
}
