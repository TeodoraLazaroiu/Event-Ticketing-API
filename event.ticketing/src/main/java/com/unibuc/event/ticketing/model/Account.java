package com.unibuc.event.ticketing.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "accounts")
public class Account {
    @Id
    private String accountId;
    private String name;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId")
    private User user;
    @OneToMany(mappedBy = "account")
    private List<Order> orders;
}
