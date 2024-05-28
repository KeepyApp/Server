package com.server.maven.kinderGarten;

import com.server.maven.eventHistory.EventHistoryManager;
import org.springframework.stereotype.Component;

@Component
public class Kindergarten {
    private String kindergartenName;
    private String parentID;
    private String password;
    private EventHistoryManager eventHistoryManager;

//    public Kindergarten(String kindergartenName, String parentID, String password) {
//        this.kindergartenName = kindergartenName;
//        this.parentID = parentID;
//        this.password = password;
//    }
    public Kindergarten() {
        this.kindergartenName = "Ruth";
        this.parentID = "11123";
        this.password = "555555";
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
