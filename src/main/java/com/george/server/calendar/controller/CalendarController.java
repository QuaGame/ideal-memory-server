package com.george.server.calendar.controller;

import com.george.server.calendar.dto.ObjectMessageResponse;
import com.george.server.calendar.model.Calendar;
import com.george.server.calendar.service.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/calendar")
public class CalendarController {

    @Autowired
    private CalendarService calendarService;

    @PostMapping("/create")
    private ResponseEntity<?> createCalendar(@RequestBody Calendar calendar, Principal principal) {
        ObjectMessageResponse<?> objectMessageResponse = calendarService.createCalendar(calendar, principal);
        return new ResponseEntity<>(objectMessageResponse, HttpStatus.CREATED);
    }

    @GetMapping("/get")
    private ResponseEntity<List<Calendar>> getAllCalendars(Principal principal) {
        List<Calendar> pageCalendars = calendarService.getAllCalendars(principal);
        return new ResponseEntity<>(pageCalendars, HttpStatus.OK);
    }

}
