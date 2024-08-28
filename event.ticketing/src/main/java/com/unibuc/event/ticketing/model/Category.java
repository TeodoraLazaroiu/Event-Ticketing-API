package com.unibuc.event.ticketing.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @Column(insertable = false, updatable = false, columnDefinition="serial")
    private String categoryId;
    private String name;
    @ManyToMany(mappedBy = "categories")
    private List<Event> events;
}
