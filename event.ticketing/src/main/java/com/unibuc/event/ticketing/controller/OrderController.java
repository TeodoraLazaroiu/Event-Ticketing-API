package com.unibuc.event.ticketing.controller;

import com.unibuc.event.ticketing.dto.order.OrderPlacedDto;
import com.unibuc.event.ticketing.dto.order.PlaceOrderDto;
import com.unibuc.event.ticketing.exception.NotEnoughEventSeatsException;
import com.unibuc.event.ticketing.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
@Tag(name = "Orders", description = "Endpoints for orders")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @PostMapping
    @Operation(summary = "Place an order", description = "Create a new order")
    @ApiResponses({
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400"),
            @ApiResponse(responseCode = "500")
    })
    public ResponseEntity<OrderPlacedDto> createOrder(@Valid @RequestBody PlaceOrderDto orderDto)
    {
        try {
            var response = orderService.placeOrder(orderDto);
            return ResponseEntity.ok(response);
        }
        catch (NotEnoughEventSeatsException ex) {
            return ResponseEntity.badRequest().build();
        }
        catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
