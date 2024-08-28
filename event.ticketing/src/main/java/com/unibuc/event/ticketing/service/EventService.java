package com.unibuc.event.ticketing.service;

import com.unibuc.event.ticketing.exception.EventNotFoundException;
import com.unibuc.event.ticketing.model.Event;
import com.unibuc.event.ticketing.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;
    public Event getEvent(String eventId) throws EventNotFoundException {
        var event = eventRepository.findById(eventId);
        if (event.isPresent()) return event.get();
        else throw new EventNotFoundException();
    }

    public void saveEvent (Event event) {
        eventRepository.save(event);
    }
}
