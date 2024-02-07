package com.george.server.calendar.service;

import com.george.server.calendar.dto.ObjectMessageResponse;
import com.george.server.calendar.model.Calendar;
import com.george.server.calendar.model.Event;
import com.george.server.calendar.repository.CalendarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Set;

@Service
public class CalendarService {

    @Autowired
    private CalendarRepository calendarRepository;

    public ObjectMessageResponse<?> createCalendar(Calendar calendar, Principal principal) {

        if (principal == null) {
            return new ObjectMessageResponse<Void>("Unauthorized", null);
        }

        String usernamePrincipal = principal.getName();
        calendar.setUsername(usernamePrincipal);

        Set<Event> events = calendar.getEvents();
        if (!events.isEmpty()) {
            for (Event event : events) {
                calendar.addEvent(event);
            }
        }
        Calendar _calendar = calendarRepository.save(calendar);

        return new ObjectMessageResponse<>("create ok", _calendar);
    }

    public void deleteCalendar(long calendarId) {
        calendarRepository.deleteById(calendarId);
    }

    public void updateCalendar(String nameCalendar, long idCalendar) {
        int code = calendarRepository.updateNameById(nameCalendar, idCalendar);
    }

    public List<Calendar> getAllCalendars(Principal principal) {
        return calendarRepository.findByUsername(principal.getName());
    }

}