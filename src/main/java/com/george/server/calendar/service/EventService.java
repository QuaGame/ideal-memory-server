package com.george.server.calendar.service;

import com.george.server.calendar.model.Event;
import com.george.server.calendar.repository.EventRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    public List<Event> getAllEventsBuId() {
        return null;
    }

}
