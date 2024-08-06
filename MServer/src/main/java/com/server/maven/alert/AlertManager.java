package com.server.maven.alert;

import com.fasterxml.jackson.databind.JsonNode;
import com.server.maven.firebase.NotificationService;
import com.server.maven.kinderGarten.Kindergarten;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class AlertManager {

    private final NotificationService notificationService;
    private final ConcurrentHashMap<String, String> userTokens;

    public AlertManager() {
        this.notificationService = new NotificationService();
        this.userTokens = new ConcurrentHashMap<>();
    }

    public void processEvent(String parentId, String kindergartenName, JsonNode jsonNode) {
        String token = userTokens.get(parentId);
        if (token != null) {
            String event = jsonNode.get("event").asText();
            String timestamp = jsonNode.get("timestamp").asText();

            String title = "Event Type: " + event ;
            StringBuilder body = new StringBuilder();
            body.append("An unusual sound was detected in ").append(kindergartenName).append(" at ").append(timestamp).append(".\n");

            notificationService.sendNotification(token, title, body.toString());
        } else {
            System.out.println("No token found for parent ID: " + parentId);
        }
    }


    public void addToken(String parentId, String token) {
        userTokens.put(parentId, token);
    }

    public void sendNotification(String token, String title, String body) {
        notificationService.sendNotification(token, title, body);
    }
}
