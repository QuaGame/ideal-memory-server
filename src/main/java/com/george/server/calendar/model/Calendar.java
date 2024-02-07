package com.george.server.calendar.model;

import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "calendars")
@Getter
@Setter
@NoArgsConstructor
public class Calendar {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;
    private String username;

    @OneToMany(mappedBy = "calendar",cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Event> events = new LinkedHashSet<>();

    public void addEvent(Event event) {
        events.add(event);
        event.setCalendar(this);
    }

    public void removeEvent(Event event) {
        events.remove(event);
        event.setCalendar(null);
    }

}
