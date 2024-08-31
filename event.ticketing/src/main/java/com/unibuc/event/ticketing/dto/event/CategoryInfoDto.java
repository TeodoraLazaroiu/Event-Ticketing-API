package com.unibuc.event.ticketing.dto.event;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CategoryInfoDto {
    @NotNull
    private String categoryId;
    @NotNull
    private String name;
}
