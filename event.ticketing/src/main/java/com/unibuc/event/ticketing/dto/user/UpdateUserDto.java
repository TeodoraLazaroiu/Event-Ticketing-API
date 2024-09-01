package com.unibuc.event.ticketing.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateUserDto {
    @NotNull
    public String userId;
    @Email
    public String email;
    @NotNull
    @Size(min = 5, max = 30)
    public String newPassword;
    @NotNull
    @Size(min = 5, max = 30)
    public String oldPassword;
    @NotNull
    @Size(min = 3, max = 30)
    public String name;
}
