package com.unibuc.event.ticketing.dto.order;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PlaceOrderDto {
    @NotNull
    private String userId;
    @NotNull
    private String eventId;
    @NotNull
    private Integer numberOfTickets;
}
