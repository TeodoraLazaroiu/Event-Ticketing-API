package com.unibuc.event.ticketing.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Category {
    @Id
    private String id;
    private String name;
    @ManyToMany
    private List<Event> events;
}
