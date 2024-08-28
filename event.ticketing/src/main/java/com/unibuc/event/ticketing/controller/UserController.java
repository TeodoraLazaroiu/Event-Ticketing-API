package com.unibuc.event.ticketing.controller;

import com.unibuc.event.ticketing.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@Tag(name = "Users", description = "Endpoint for user and account")
public class UserController {
    @Autowired
    private UserService userService;
}
