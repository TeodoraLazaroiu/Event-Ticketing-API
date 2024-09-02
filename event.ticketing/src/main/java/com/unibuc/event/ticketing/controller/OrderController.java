package com.unibuc.event.ticketing.controller;

import com.unibuc.event.ticketing.dto.ErrorResponseDto;
import com.unibuc.event.ticketing.dto.order.PlaceOrderDto;
import com.unibuc.event.ticketing.exception.AccountNotFoundException;
import com.unibuc.event.ticketing.exception.NotEnoughEventSeatsException;
import com.unibuc.event.ticketing.exception.UserNotFoundException;
import com.unibuc.event.ticketing.service.OrderService;
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
@RequestMapping(path = "/api/order", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Orders", description = "Endpoints for orders and tickets")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping
    @Operation(summary = "Place an order", description = "Create a new order for an event")
    @ApiResponses({
            @ApiResponse(description = "Success", responseCode = "200"),
            @ApiResponse(description = "Bad Request", responseCode = "400"),
            @ApiResponse(description = "Server error", responseCode = "500")
    })
    public ResponseEntity<Object> createOrder(@Valid @RequestBody PlaceOrderDto orderDto)
    {
        try {
            var response = orderService.placeOrder(orderDto);
            return ResponseEntity.ok(response);
        }
        catch (NotEnoughEventSeatsException ex) {
            var response = new ErrorResponseDto(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping
    @Operation(summary = "Get orders and tickets", description = "Get all orders and tickets for a user")
    @ApiResponses({
            @ApiResponse(description = "Success", responseCode = "200"),
            @ApiResponse(description = "Not Found", responseCode = "404"),
            @ApiResponse(description = "Server error", responseCode = "500")
    })
    public ResponseEntity<Object> getUserOrders(@RequestParam String userId)
    {
        try {
            var orders = orderService.getUserOrders(userId);
            return ResponseEntity.ok(orders);
        }
        catch (UserNotFoundException | AccountNotFoundException ex) {
            var response = new ErrorResponseDto(ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
