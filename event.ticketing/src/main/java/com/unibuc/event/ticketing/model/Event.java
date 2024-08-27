package com.unibuc.event.ticketing.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Event {
    @Id
    private String id;
    private String name;
    private String description;
    @ManyToMany
    private List<Category> categories;
}
