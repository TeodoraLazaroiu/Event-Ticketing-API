package com.unibuc.event.ticketing.controller;

import com.unibuc.event.ticketing.dto.ErrorResponseDto;
import com.unibuc.event.ticketing.dto.user.CreateUserDto;
import com.unibuc.event.ticketing.dto.user.UpdateUserDto;
import com.unibuc.event.ticketing.dto.user.UserCreatedDto;
import com.unibuc.event.ticketing.exception.AccountNotFoundException;
import com.unibuc.event.ticketing.exception.InvalidPasswordException;
import com.unibuc.event.ticketing.exception.UserNotFoundException;
import com.unibuc.event.ticketing.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/user", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Users", description = "Endpoints for user and account")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping()
    @Operation(summary = "Get user information", description = "Get user and an account details")
    @ApiResponses({
            @ApiResponse(description = "Success", responseCode = "200"),
            @ApiResponse(description = "Not Found", responseCode = "404"),
            @ApiResponse(description = "Server error", responseCode = "500")
    })
    public ResponseEntity<Object> getUserInfo(@RequestParam String userId)
    {
        try {
            var userInfo = userService.getUserInfo(userId);
            return ResponseEntity.ok(userInfo);
        }
        catch (UserNotFoundException | AccountNotFoundException ex) {
            var response = new ErrorResponseDto(ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    @Operation(summary = "Create user and account", description = "Create a new user and an account")
    @ApiResponses({
            @ApiResponse(description = "Success", responseCode = "200"),
            @ApiResponse(description = "Server error", responseCode = "500")
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

    @PutMapping
    @Operation(summary = "Update user and account", description = "Update a user and an account")
    @ApiResponses({
            @ApiResponse(description = "Success", responseCode = "200"),
            @ApiResponse(description = "Bad Request", responseCode = "400"),
            @ApiResponse(description = "Not Found", responseCode = "404"),
            @ApiResponse(description = "Server error", responseCode = "500")
    })
    public ResponseEntity<Object> updateUser(@Valid @RequestBody UpdateUserDto userDto)
    {
        try {
            userService.updateUser(userDto);
            return ResponseEntity.ok().build();
        }
        catch (UserNotFoundException | AccountNotFoundException ex) {
            var response = new ErrorResponseDto(ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        catch (InvalidPasswordException ex) {
            var response = new ErrorResponseDto(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
