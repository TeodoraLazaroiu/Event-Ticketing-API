package com.unibuc.event.ticketing.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String eventId;
    private String name;
    private String description;
    private Integer seats;
    private Integer ticketPrice;
    @OneToMany(mappedBy = "event")
    private List<Ticket> tickets;
    @ManyToMany
    @JoinTable(name = "events_categories",
    joinColumns = @JoinColumn(name = "event_id"),
    inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categories;
}
