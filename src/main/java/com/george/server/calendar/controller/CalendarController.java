package com.george.server.calendar.controller;

import com.george.server.calendar.model.Calendar;
import com.george.server.calendar.service.CalendarService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CalendarController {

    @Autowired
    private CalendarService calendarService;

    @PostMapping("/create.calendar")
    private ResponseEntity<Calendar> createCalendar(@RequestBody Calendar calendar) {
        Calendar _calendar = calendarService.createCalendar(calendar);
        return new ResponseEntity<>(_calendar, HttpStatus.CREATED);
    }

    @GetMapping("/get.calendars")
    private ResponseEntity<List<Calendar>> getAllCalendars() {
        List<Calendar> pageCalendars = calendarService.getAllCalendars();
        return new ResponseEntity<>(pageCalendars, HttpStatus.OK);
    }

}
