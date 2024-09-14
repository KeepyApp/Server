package com.server.maven.analysis;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.firebase.database.DatabaseReference;
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
            mainController.printEventDetails(jsonNode);
        }
    }

    private void analyzeCryingEvent(String kindergartenName, JsonNode jsonNode, String parentId) {
        double cryingIntensity = jsonNode.get("intensity").asDouble();
        int cryingDuration = jsonNode.get("duration").asInt();

        // Define thresholds for triggering alerts
        double intensityThreshold = 0.3;
        int durationThreshold = 20 ; // 20 seconds

        if (cryingIntensity > intensityThreshold && cryingDuration > durationThreshold) {
            alertManager.processEvent(parentId, jsonNode.get("kindergarten_name").asText(), jsonNode);
            mainController.saveEventToFirebase(kindergartenName, jsonNode);
            mainController.printEventDetails(jsonNode);
        } else {
            System.out.println("Crying event detected, but not severe enough to trigger an alert.");
        }
    }

    public void processPositiveFeedback(String kindergartenName, int count) {
        // Determine the star rating (out of 5)
        int maxFeedbackForFiveStars = 50; // Define what count of feedback equals 5 stars
        int starRating = Math.min(5, (int) Math.floor((double) count / maxFeedbackForFiveStars * 5));

        // Logic to handle positive feedback
        System.out.println("Processing " + count + " positive feedback(s) for kindergarten: " + kindergartenName);
        System.out.println("Calculated star rating: " + starRating + " out of 5");

        // Save the star rating to Firebase
        DatabaseReference ref = mainController.getFirebaseDatabase().getReference("kindergartens").child(kindergartenName).child("weekly_positive_feedback");
        ref.child("star_rating").setValueAsync(starRating);

        System.out.println("Sent " + starRating + " stars to Firebase for " + kindergartenName);
    }


}