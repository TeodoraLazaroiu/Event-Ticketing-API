package com.unibuc.event.ticketing.dto.order;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class OrderPlacedDto {
    @NotNull
    public String orderId;
}
