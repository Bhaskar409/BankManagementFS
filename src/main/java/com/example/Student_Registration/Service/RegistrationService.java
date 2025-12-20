package com.example.Student_Registration.Service;

import com.example.Student_Registration.Model.Event;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class RegistrationService {
    private final List<Event> events = new ArrayList<>();

    public List<Event> allStudent() {
        return events;
    }

    public Event getStudentById(int id)
    {
        return events
                .stream()       //jackson: the stream is used to convert jason to pojo
                .filter(x -> x.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public Event addEvent(Event event)
    {
        boolean exists = events.stream()
                .anyMatch(e -> e.getId() == event.getId());

        if (exists) {
            throw new IllegalStateException("Student with id " + event.getId() + " already exists");
        }
        events.add(event);
        return event;
    }

    public boolean deleteEvent(int id)
    {
        return events.remove(getStudentById(id));
    }
}