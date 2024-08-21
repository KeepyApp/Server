package com.server.maven.kinderGarten;

public class EventDetail {
    private String id;
    private String event;
    private String timestamp;
    private String kindergarten_name;
    private String word;
    private String sentence;

    // Default constructor for Firebase
    public EventDetail() {}

    // Getters and setters for all fields
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getKindergarten_name() {
        return kindergarten_name;
    }

    public void setKindergarten_name(String kindergarten_name) {
        this.kindergarten_name = kindergarten_name;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }
}

