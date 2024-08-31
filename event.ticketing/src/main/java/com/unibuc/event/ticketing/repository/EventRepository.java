package com.unibuc.event.ticketing.repository;

import com.unibuc.event.ticketing.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, String> {
    @Query("SELECT e FROM Event e JOIN e.categories c WHERE c.categoryId = :categoryId")
    List<Event> findEventsByCategoryId(@Param("categoryId") String categoryId);
}
