package com.unibuc.event.ticketing.controller;

import com.unibuc.event.ticketing.dto.event.CategoryInfoDto;
import com.unibuc.event.ticketing.dto.event.EventInfoDto;
import com.unibuc.event.ticketing.exception.CategoryNotFoundException;
import com.unibuc.event.ticketing.model.Category;
import com.unibuc.event.ticketing.repository.CategoryRepository;
import com.unibuc.event.ticketing.service.EventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/event")
@Tag(name = "Events", description = "Endpoints for events")
public class EventController {
    @Autowired
    private EventService eventService;
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    @Operation(summary = "Get events", description = "Get all events or events for category id")
    @ApiResponses({
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400"),
            @ApiResponse(responseCode = "500")
    })
    public ResponseEntity<List<EventInfoDto>> getEvents(@RequestParam Optional<String> categoryId)
    {
        try {
            var events = eventService.getEventsInfo(categoryId);
            return ResponseEntity.ok(events);
        }
        catch (CategoryNotFoundException ex) {
            return ResponseEntity.badRequest().build();
        }
        catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/categories")
    @Operation(summary = "Get events categories", description = "Get all events categories")
    @ApiResponses({
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "500")
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
