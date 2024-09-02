package com.unibuc.event.ticketing.service;

import com.unibuc.event.ticketing.dto.event.CategoryInfoDto;
import com.unibuc.event.ticketing.dto.event.EventInfoDto;
import com.unibuc.event.ticketing.model.Category;
import com.unibuc.event.ticketing.model.Event;
import com.unibuc.event.ticketing.model.Ticket;
import com.unibuc.event.ticketing.repository.CategoryRepository;
import com.unibuc.event.ticketing.repository.EventRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EventServiceTest {
    @Mock
    private EventRepository eventRepository;
    @Mock
    private CategoryRepository categoryRepository;
    @InjectMocks
    private EventService eventService;

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

    @Test
    public void getEventTest() throws Exception {
        when(eventRepository.findById(event.getEventId())).thenReturn(Optional.of(event));

        var result = eventService.getEvent(event.getEventId());

        assertThat(result).isNotNull();
        assertThat(result).isInstanceOf(Event.class);
    }

    @Test
    public void saveEventTest() {
        eventService.saveEvent(event);
        Mockito.verify(eventRepository).save(any(Event.class));
    }

    @NullSource
    @ParameterizedTest
    @ValueSource(strings = {"categoryId"})
    public void getEventsInfoTest (String categoryId) throws Exception {
        List<EventInfoDto> result;
        if (categoryId != null)
        {
            when(categoryRepository.findAll()).thenReturn(List.of(category));
            when(eventRepository.findEventsByCategoryId(category.getCategoryId())).thenReturn(List.of(event));
            result = eventService.getEventsInfo(Optional.of((categoryId)));
        }
        else {
            when(eventRepository.findAll()).thenReturn(List.of(event));
            result = eventService.getEventsInfo(Optional.empty());
        }

        assertThat(result).isNotNull();
        assertThat(result).isInstanceOf(List.class);
        assertThat(result.get(0)).isInstanceOf(EventInfoDto.class);
    }

    @Test
    public void getCategoriesTest() {
        when(categoryRepository.findAll()).thenReturn(List.of(category));

        var result = eventService.getCategories();

        assertThat(result).isNotNull();
        assertThat(result).isInstanceOf(List.class);
        assertThat(result.get(0)).isInstanceOf(CategoryInfoDto.class);
    }
}
