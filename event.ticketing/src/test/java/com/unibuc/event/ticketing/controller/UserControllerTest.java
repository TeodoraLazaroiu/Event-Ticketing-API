package com.unibuc.event.ticketing.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unibuc.event.ticketing.dto.user.CreateUserDto;
import com.unibuc.event.ticketing.dto.user.UpdateUserDto;
import com.unibuc.event.ticketing.dto.user.UserCreatedDto;
import com.unibuc.event.ticketing.dto.user.UserInfoDto;
import com.unibuc.event.ticketing.model.Account;
import com.unibuc.event.ticketing.model.User;
import com.unibuc.event.ticketing.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;
    @Autowired
    private ObjectMapper objectMapper;
    private final User user = new User("userId", "test@email.com", "password", new Account());
    private final Account account = new Account("accountId", "test", new User(), new ArrayList<>());

    @BeforeEach
    public void setup() {
        user.setAccount(account);
        account.setUser(user);
    }

    @Test
    public void getUserTest() throws Exception {
        var userInfo = new UserInfoDto(user.getEmail(), account.getName());
        when(userService.getUserInfo(user.getUserId())).thenReturn(userInfo);

        var response = mockMvc.perform(get("/api/user?userId=" + user.getUserId()));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.email", is(user.getEmail())))
                .andExpect(jsonPath("$.name", is(account.getName())));
    }

    @Test
    public void createUserTest() throws Exception {
        var createUser = new CreateUserDto(user.getEmail(), user.getPassword(), account.getName());
        when(userService.createUser(createUser)).thenReturn(new UserCreatedDto(user.getUserId()));

        var response = mockMvc.perform(post("/api/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createUser)));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.userId", is(user.getUserId())));
    }

    @Test
    public void updateUserTest() throws Exception {
        var updateUser = new UpdateUserDto(user.getUserId(), user.getEmail(), "newPassword",
                user.getPassword(), account.getName());

        var response = mockMvc.perform(put("/api/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateUser)));

        response.andExpect(status().isOk());
    }
}
