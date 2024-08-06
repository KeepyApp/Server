package com.server.maven.kinderGarten;

import com.server.maven.eventHistory.EventsManager;
import org.springframework.stereotype.Component;

@Component
public class Kindergarten {
    private String kindergartenName;
    private String parentID;
    private String password;
    private EventsManager events;

    public Kindergarten() {
        this.events = new EventsManager();
    }

    public String getKindergartenName() {
        return kindergartenName;
    }

    public void setKindergartenName(String kindergartenName) {
        this.kindergartenName = kindergartenName;
    }

    public String getParentID() {
        return parentID;
    }

    public void setParentID(String parentID) {
        this.parentID = parentID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public EventsManager getEvents() {
        return events;
    }

    public void setEvents(EventsManager events) {
        this.events = events;
    }
}
