package com.george.server.calendar.controller;

import com.george.server.calendar.model.Event;
import com.george.server.calendar.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping("/create.event")
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
        Event _event = eventService.createEvent(event);
        return new ResponseEntity<>(_event, HttpStatus.CREATED);
    }


}
