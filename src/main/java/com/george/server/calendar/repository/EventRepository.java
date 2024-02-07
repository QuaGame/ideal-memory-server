package com.george.server.calendar.repository;

import com.george.server.calendar.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findByCalendar_IdAndCalendar_Username(Long id, String username);
}