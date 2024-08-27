package com.unibuc.event.ticketing.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Account {
    @Id
    private String id;
    private String name;
    @OneToOne
    private User user;
    @OneToMany
    private List<Order> orders;
}
