package com.unibuc.event.ticketing.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unibuc.event.ticketing.dto.event.CategoryInfoDto;
import com.unibuc.event.ticketing.dto.event.EventInfoDto;
import com.unibuc.event.ticketing.model.Category;
import com.unibuc.event.ticketing.model.Event;
import com.unibuc.event.ticketing.model.Ticket;
import com.unibuc.event.ticketing.service.EventService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EventController.class)
public class EventControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private EventService eventService;
    @Autowired
    private ObjectMapper objectMapper;
    private final Event event = new Event("eventId", "test", "", 100, 50);
    private final Category category = new Category("categoryId", "test");
    @BeforeEach
    public void setup() {
        var ticket1 = new Ticket("ticketId1", event.getTicketPrice());
        var ticket2 = new Ticket("ticketId2", event.getTicketPrice());
        event.setTickets(List.of(ticket1, ticket2));
        event.setCategories(List.of(category));
        category.setEvents(List.of(event));
    }

    @NullSource
    @ParameterizedTest
    @ValueSource(strings = {"categoryId"})
    public void getEventsTest(String categoryId) throws Exception {
        var eventInfo = new EventInfoDto(event.getEventId(), event.getName(), event.getDescription(),
                event.getSeats(), event.getTickets().size(), event.getTicketPrice(), List.of(category.getName()));

        when(eventService.getEventsInfo(Optional.empty())).thenReturn(List.of(eventInfo));
        when(eventService.getEventsInfo(Optional.ofNullable(categoryId))).thenReturn(List.of(eventInfo));

        var path = "/api/event";
        if (categoryId != null) path = path + "?categoryId=" + categoryId;
        var response = mockMvc.perform(get(path));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].eventId", is(eventInfo.getEventId())))
                .andExpect(jsonPath("$.[0].name", is(eventInfo.getName())))
                .andExpect(jsonPath("$.[0].description", is(eventInfo.getDescription())))
                .andExpect(jsonPath("$.[0].availableSeats", is(eventInfo.getAvailableSeats())))
                .andExpect(jsonPath("$.[0].soldSeats", is(eventInfo.getSoldSeats())))
                .andExpect(jsonPath("$.[0].ticketPrice", is(eventInfo.getTicketPrice())))
                .andExpect(jsonPath("$.[0].categories[0]", is(eventInfo.getCategories().get(0))));
    }

    @Test
    public void getCategoriesTest() throws Exception {
        var categoryInfo = new CategoryInfoDto(category.getCategoryId(), category.getName());
        when(eventService.getCategories()).thenReturn(List.of(categoryInfo));

        var response = mockMvc.perform(get("/api/event/categories"));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].categoryId", is(categoryInfo.getCategoryId())))
                .andExpect(jsonPath("$.[0].name", is(categoryInfo.getName())));
    }
}
