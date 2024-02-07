package com.george.server.calendar.service;

import com.george.server.calendar.model.Calendar;
import com.george.server.calendar.model.Event;
import com.george.server.calendar.repository.CalendarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class CalendarService {

    @Autowired
    private CalendarRepository calendarRepository;

    public Calendar createCalendar(Calendar calendar) {
        Set<Event> events = calendar.getEvents();
        if (!events.isEmpty()) {
            for (Event event : events) {
                calendar.addEvent(event);
            }
        }

        return calendarRepository.save(calendar);
    }

    public void deleteCalendar(long calendarId) {
        calendarRepository.deleteById(calendarId);
    }

    public void updateCalendar(String nameCalendar, long idCalendar) {
        int code = calendarRepository.updateNameById(nameCalendar, idCalendar);
    }

    public List<Calendar> getAllCalendars() {
        return calendarRepository.findAll();
    }

}