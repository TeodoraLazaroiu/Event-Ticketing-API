package com.unibuc.event.ticketing.controller;

import com.unibuc.event.ticketing.dto.ErrorResponseDto;
import com.unibuc.event.ticketing.dto.event.CategoryInfoDto;
import com.unibuc.event.ticketing.exception.CategoryNotFoundException;
import com.unibuc.event.ticketing.service.EventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/event", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Events", description = "Endpoints for events and categories")
public class EventController {
    @Autowired
    private EventService eventService;

    @GetMapping
    @Operation(summary = "Get all events or by category", description = "Get all events or events for a category")
    @ApiResponses({
            @ApiResponse(description = "Success", responseCode = "200"),
            @ApiResponse(description = "Bad Request", responseCode = "400"),
            @ApiResponse(description = "Server error", responseCode = "500")
    })
    public ResponseEntity<Object> getEvents(@RequestParam Optional<String> categoryId)
    {
        try {
            var events = eventService.getEventsInfo(categoryId);
            return ResponseEntity.ok(events);
        }
        catch (CategoryNotFoundException ex) {
            var response = new ErrorResponseDto(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/categories")
    @Operation(summary = "Get events categories", description = "Get all events categories")
    @ApiResponses({
            @ApiResponse(description = "Success", responseCode = "200"),
            @ApiResponse(description = "Server error", responseCode = "500")
    })
    public ResponseEntity<List<CategoryInfoDto>> getEventsCategories()
    {
        try {
            var categories = eventService.getCategories();
            return ResponseEntity.ok(categories);
        }
        catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
