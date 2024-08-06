package com.server.maven.eventHistory;

import com.server.maven.mainController.Event;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class EventsManager {

    private ArrayList<Event> events;
    public EventsManager() {
        this.events = new ArrayList<>();
    }
    public ArrayList<Event> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<Event> events) {
        this.events = events;
    }

    public void addEvent(Event newEvent)
    {
        events.add(newEvent);
    }

}
