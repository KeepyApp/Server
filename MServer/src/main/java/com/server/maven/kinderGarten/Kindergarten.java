package com.server.maven.kinderGarten;

import com.server.maven.eventHistory.EventHistoryManager;

public class Kindergarten {
    private String kindergartenName;
    private String parentID;
    private String password;
    private EventHistoryManager eventHistoryManager;

    public Kindergarten(String kindergartenName) {
        this.kindergartenName = kindergartenName;
        this.parentID = parentID;
        this.password = password;
        this.eventHistoryManager = eventHistoryManager;
    }

    public String getParentID() {
        return parentID;
    }

    public void setParentID(String parentID) {
        this.parentID = parentID;
    }

    public String getKindergartenName() {
        return kindergartenName;
    }

    public void setKindergartenName(String kindergartenName) {
        this.kindergartenName = kindergartenName;
    }
}
