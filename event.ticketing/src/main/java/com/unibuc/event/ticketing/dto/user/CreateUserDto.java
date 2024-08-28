package com.unibuc.event.ticketing.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CreateUserDto {
    @Email
    public String email;
    @NotNull
    @Size(min = 5, max = 30)
    public String password;
    @NotNull
    @Size(min = 3, max = 30)
    public String name;
}
