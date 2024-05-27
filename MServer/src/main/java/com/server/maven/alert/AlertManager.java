package com.server.maven.alert;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.server.maven.kinderGarten.Kindergarten;
import org.springframework.stereotype.Component;

@Component
public class AlertManager {
    public void sendAlert(String recipient, Kindergarten kindergarten, String message) {
        // Send alert to the specified recipient through appropriate channel
        System.out.println("Alert sent to " + recipient + ": " + message);

        sendNotification("FCM-Device-Token", "Alert", "There is something unusual");

    }
    private void sendNotification(String token, String title, String body) {
        Message message = Message.builder()
                //.setNotification(new Notification(title, body))
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
