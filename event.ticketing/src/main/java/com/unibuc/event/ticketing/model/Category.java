package com.unibuc.event.ticketing.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "categories")
public class Category {
    @Id
    private String categoryId;
    private String name;
    @ManyToMany(mappedBy = "categories")
    private List<Event> events;
}
