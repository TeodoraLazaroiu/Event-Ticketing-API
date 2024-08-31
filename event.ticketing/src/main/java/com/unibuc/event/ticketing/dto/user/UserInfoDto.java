package com.unibuc.event.ticketing.dto.user;

import com.unibuc.event.ticketing.dto.order.OrderInfoDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UserInfoDto {
    public String email;
    public String name;
}
