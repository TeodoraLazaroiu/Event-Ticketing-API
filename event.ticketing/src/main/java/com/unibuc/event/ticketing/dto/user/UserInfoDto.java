package com.unibuc.event.ticketing.dto.user;

import com.unibuc.event.ticketing.dto.order.OrderInfoDto;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UserInfoDto {
    @NotNull
    public String email;
    @NotNull
    public String name;
}
