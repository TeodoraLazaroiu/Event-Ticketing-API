package com.unibuc.event.ticketing.service;

import com.unibuc.event.ticketing.dto.event.CategoryInfoDto;
import com.unibuc.event.ticketing.dto.event.EventInfoDto;
import com.unibuc.event.ticketing.dto.order.OrderInfoDto;
import com.unibuc.event.ticketing.exception.CategoryNotFoundException;
import com.unibuc.event.ticketing.exception.EventNotFoundException;
import com.unibuc.event.ticketing.model.Category;
import com.unibuc.event.ticketing.model.Event;
import com.unibuc.event.ticketing.model.Order;
import com.unibuc.event.ticketing.model.Ticket;
import com.unibuc.event.ticketing.repository.CategoryRepository;
import com.unibuc.event.ticketing.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    public Event getEvent(String eventId) throws EventNotFoundException {
        var event = eventRepository.findById(eventId);
        if (event.isPresent()) return event.get();
        else throw new EventNotFoundException();
    }

    public void saveEvent (Event event) {
        eventRepository.save(event);
    }

    public List<EventInfoDto> getEventsInfo(Optional<String> categoryId) throws CategoryNotFoundException {
        List<Event> events;
        if (categoryId.isPresent()) {
            var category = categoryId.get();
            var ids = categoryRepository.findAll().stream().map(Category::getCategoryId).toList();
            if (!ids.contains(category)) throw new CategoryNotFoundException();
            events = eventRepository.findEventsByCategoryId(category);
        }
        else {
            events = eventRepository.findAll();
        }

        var eventsInfo = new ArrayList<EventInfoDto>();

        for (Event event: events) {
            var soldTickets = event.getTickets().size();
            var categories = event.getCategories().stream().map(Category::getName).toList();

            var info = new EventInfoDto(event.getName(), event.getDescription(),
                    event.getSeats(), soldTickets, event.getTicketPrice(), categories);

            eventsInfo.add(info);
        }

        return eventsInfo;
    }

    public List<CategoryInfoDto> getCategories() {
        var categories = categoryRepository.findAll();
        return categories.stream().map(c -> new CategoryInfoDto(c.getCategoryId(), c.getName())).toList();
    }
}
