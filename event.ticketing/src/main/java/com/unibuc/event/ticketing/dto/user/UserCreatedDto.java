package com.unibuc.event.ticketing.dto.user;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserCreatedDto {
    @NotNull
    private String userId;
}
