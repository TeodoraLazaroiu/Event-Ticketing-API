package com.unibuc.event.ticketing.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderInfoDto {
    private Integer orderNumber;
    private Integer numberOfTickets;
    private Integer orderPrice;
    private String eventId;

}
