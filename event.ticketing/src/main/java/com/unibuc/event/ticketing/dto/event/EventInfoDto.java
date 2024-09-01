package com.unibuc.event.ticketing.dto.event;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class EventInfoDto {
    @NotNull
    public String eventId;
    @NotNull
    private String name;
    @NotNull
    private String description;
    @NotNull
    private Integer availableSeats;
    @NotNull
    private Integer soldSeats;
    @NotNull
    private Integer ticketPrice;
    private List<String> categories;
}
