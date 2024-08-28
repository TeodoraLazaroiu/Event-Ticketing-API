package com.unibuc.event.ticketing.dto;

import com.unibuc.event.ticketing.model.Order;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UserInfoDto {
    public String email;
    public String name;
    public List<OrderInfoDto> ordersInfo;
}
