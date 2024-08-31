package com.unibuc.event.ticketing.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "categories")
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @Id
    private String categoryId;
    private String name;
    @ManyToMany(mappedBy = "categories")
    private List<Event> events;
}
