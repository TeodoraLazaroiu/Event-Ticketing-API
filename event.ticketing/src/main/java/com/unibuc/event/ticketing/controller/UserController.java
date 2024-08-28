package com.unibuc.event.ticketing.controller;

import com.unibuc.event.ticketing.dto.user.CreateUserDto;
import com.unibuc.event.ticketing.dto.user.UserCreatedDto;
import com.unibuc.event.ticketing.dto.user.UserInfoDto;
import com.unibuc.event.ticketing.exception.AccountNotFoundException;
import com.unibuc.event.ticketing.exception.UserNotFoundException;
import com.unibuc.event.ticketing.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@Tag(name = "Users", description = "Endpoints for user and account")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(path = "/{userId}")
    @Operation(summary = "Get user info", description = "Get a user and an account details")
    @ApiResponses({
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "404"),
            @ApiResponse(responseCode = "500")
    })
    public ResponseEntity<UserInfoDto> getUserInfo(@PathVariable String userId)
    {
        try {
            var userInfo = userService.getUserInfo(userId);
            return ResponseEntity.ok(userInfo);
        }
        catch (UserNotFoundException | AccountNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
        catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    @Operation(summary = "Create a user", description = "Create a new user and an account")
    @ApiResponses({
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "404"),
            @ApiResponse(responseCode = "500")
    })
    public ResponseEntity<UserCreatedDto> createUser(@Valid @RequestBody CreateUserDto userDto)
    {
        try {
            var user = userService.createUser(userDto);
            return ResponseEntity.ok(user);
        }
        catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
