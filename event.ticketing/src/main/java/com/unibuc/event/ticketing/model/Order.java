package com.unibuc.event.ticketing.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Order {
    @Id
    private String id;
    @OneToMany
    private List<Ticket> tickets;
}
