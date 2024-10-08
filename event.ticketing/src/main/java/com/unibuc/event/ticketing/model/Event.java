package com.unibuc.event.ticketing.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "events")
@AllArgsConstructor
@NoArgsConstructor
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

    public Event(String eventId, String name, String description, Integer seats, Integer ticketPrice) {
        this.eventId = eventId;
        this.name = name;
        this.description = description;
        this.seats = seats;
        this.ticketPrice = ticketPrice;
    }
}
