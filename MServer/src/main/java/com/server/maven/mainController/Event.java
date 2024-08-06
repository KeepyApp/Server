package com.server.maven.mainController;

public class Event {
    private EventType type; // Enum representing the type of event (e.g., CRYING, SHOUTING, CURSING)
    private String timestamp; // Timestamp indicating when the event occurred
    private String details; // Additional details about the event
    private String id;
    private String kindergartenName;

    // Constructor
    public Event(EventType type, String timestamp, String details, String id, String kindergartenName) {
        this.type = type;
        this.timestamp = timestamp;
        this.details = details;
        this.id = id;
        this.kindergartenName = kindergartenName;
    }

    // Getters and setters
    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getKindergartenName() {
        return kindergartenName;
    }

    public void setKindergartenName(String kindergartenName) {
        this.kindergartenName = kindergartenName;
    }

}
