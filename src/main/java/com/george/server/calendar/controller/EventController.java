package com.george.server.calendar.controller;

import com.george.server.calendar.model.Event;
import com.george.server.calendar.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/event")
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping("/create")
    public ResponseEntity<Event> createEvent(@RequestBody Event event,
                                             @RequestParam(value = "calendarId") Long calendarId,
                                             Principal principal) {
        Event _event = eventService.createEvent(event, calendarId, principal);
        return new ResponseEntity<>(_event, HttpStatus.CREATED);
    }

    @GetMapping("/getByCalendarId")
    public ResponseEntity<?> getAllEventsByCalendarId(@RequestParam(value = "calendarId") Long calendarId,
                                                      Principal principal) {
        List<Event> eventList = eventService.getAllEventsByCalendarId(calendarId, principal);
        return new ResponseEntity<>(eventList, HttpStatus.OK);
    }

}
