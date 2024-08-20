package com.server.maven.kinderGarten;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Kindergarten {
    private String kindergartenName;
    private String parentID;
    private String password;

    private List<String> events;

    public Kindergarten() {
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

    public List<String> getEvents() {
        return events;
    }

    public void setEvents(List<String> events) {
        this.events = events;
    }

}
