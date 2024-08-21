package com.server.maven.kinderGarten;

import java.util.Map;

public class Kindergarten {
    private String kindergartenName;
    private String parentID;
    private String password;
    private Map<String, EventDetail> events; // מפת אירועים

    public Kindergarten() {}

    // Getters and setters
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

    public Map<String, EventDetail> getEvents() {
        return events;
    }

    public void setEvents(Map<String, EventDetail> events) {
        this.events = events;
    }
}
