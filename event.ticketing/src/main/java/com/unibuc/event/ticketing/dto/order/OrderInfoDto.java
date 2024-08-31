package com.unibuc.event.ticketing.dto.order;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class OrderInfoDto {
    private Integer orderNumber;
    private Integer numberOfTickets;
    private Integer orderPrice;
    private String eventId;
    private List<String> ticketIds;

}
