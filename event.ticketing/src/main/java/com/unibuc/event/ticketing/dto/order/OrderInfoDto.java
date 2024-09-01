package com.unibuc.event.ticketing.dto.order;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class OrderInfoDto {
    @NotNull
    private Integer orderNumber;
    @NotNull
    private Integer numberOfTickets;
    @NotNull
    private Integer orderPrice;
    @NotNull
    private String eventId;
    private List<String> ticketIds;

}
