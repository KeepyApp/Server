package com.server.maven.alert;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.server.maven.kinderGarten.Kindergarten;
import org.springframework.stereotype.Component;

@Component
public class AlertManager {
    public void sendAlert(String recipient, Kindergarten kindergarten, String message) {
        // Send alert to the specified recipient through appropriate channel
        System.out.println("Alert sent to " + recipient + ": " + message);

        // Example token, replace with actual device token
        String token = "FCM-Device-Token";
        sendNotification(token, "Alert", "There is something unusual");
    }

    public void sendNotification(String token, String title, String body) {
        Notification notification = Notification.builder()
                .setTitle(title)
                .setBody(body)
                .build();

        Message message = Message.builder()
                .setNotification(notification)
                .setToken(token)
                .build();

        try {
            String response = FirebaseMessaging.getInstance().send(message);
            System.out.println("Successfully sent message: " + response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
