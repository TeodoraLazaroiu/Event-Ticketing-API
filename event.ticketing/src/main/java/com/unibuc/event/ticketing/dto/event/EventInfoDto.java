package com.unibuc.event.ticketing.dto.event;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class EventInfoDto {
    public String eventId;
    private String name;
    private String description;
    private Integer availableSeats;
    private Integer soldSeats;
    private Integer ticketPrice;
    private List<String> categories;
}
