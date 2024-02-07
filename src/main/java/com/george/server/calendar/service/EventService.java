package com.george.server.calendar.service;

import com.george.server.calendar.model.Calendar;
import com.george.server.calendar.model.Event;
import com.george.server.calendar.repository.CalendarRepository;
import com.george.server.calendar.repository.EventRepository;
import com.george.server.calendar.util.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private CalendarRepository calendarRepository;

    public Event createEvent(Event event, Long calendarId, Principal principal) {
        Calendar calendar = calendarRepository
                .findByIdAndUsername(calendarId, principal.getName())
                .orElseThrow(() ->
                        new ResourceNotFoundException("no found!")
                );

        return eventRepository.save(new Event(event.getName(), calendar));
    }

    public List<Event> getAllEventsByCalendarId(Long calendarId, Principal principal) {
        return eventRepository.findByCalendar_IdAndCalendar_Username(calendarId, principal.getName());
    }

}
