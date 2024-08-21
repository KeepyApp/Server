package com.server.maven.analysis;

import com.fasterxml.jackson.databind.JsonNode;
import com.server.maven.alert.AlertManager;
import com.server.maven.mainController.MainController;
import org.springframework.stereotype.Component;

@Component
public class AnalysisEngine {

    private final AlertManager alertManager;
    private final MainController mainController;

    public AnalysisEngine(AlertManager alertManager, MainController mainController) {
        this.alertManager = alertManager;
        this.mainController = mainController;
    }

    public void analyzeEvent(String kindergartenName, JsonNode jsonNode, String parentId) {
        String eventType = jsonNode.get("event").asText();

        if (eventType.equals("crying detected")) {
            analyzeCryingEvent(kindergartenName, jsonNode, parentId);
        } else if (eventType.equals("curse word detected") || eventType.equals("inappropriate sentence detected")) {
            alertManager.processEvent(parentId, jsonNode.get("kindergarten_name").asText(), jsonNode);
            mainController.saveEventToFirebase(kindergartenName, jsonNode);
        }
    }

    private void analyzeCryingEvent(String kindergartenName, JsonNode jsonNode, String parentId) {
        int cryingIntensity = jsonNode.get("intensity").asInt();
        int cryingDuration = jsonNode.get("duration").asInt();

        // Define thresholds for triggering alerts
        int intensityThreshold = 1;
        int durationThreshold = 2; // 20 seconds

        if (cryingIntensity >= intensityThreshold && cryingDuration > durationThreshold) {
            alertManager.processEvent(parentId, jsonNode.get("kindergarten_name").asText(), jsonNode);
            mainController.saveEventToFirebase(kindergartenName, jsonNode);
        } else {
            System.out.println("Crying event detected, but not severe enough to trigger an alert.");
        }
    }
}