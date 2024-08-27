package com.unibuc.event.ticketing.repository;

import com.unibuc.event.ticketing.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, String> {
}
