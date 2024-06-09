package com.server.maven.firebase;

import com.google.firebase.FirebaseApp;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.google.firebase.messaging.FirebaseMessagingException;
import org.springframework.stereotype.Component;

@Component
public class NotificationService {
    private final FirebaseMessaging messaging;

    public NotificationService() {
        if (FirebaseApp.getApps().isEmpty()) {
            FirebaseInitializer initializer = new FirebaseInitializer();
            initializer.initialize();
        }
        messaging = FirebaseMessaging.getInstance();
    }

    public void sendNotification(String token, String title, String body) {
        Message message = Message.builder()
                .setNotification(Notification.builder()
                        .setTitle(title)
                        .setBody(body)
                        .build())
                .setToken(token)
                .build();

        try {
            String response = messaging.send(message);
            System.out.println("Successfully sent message: " + response);
        } catch (FirebaseMessagingException e) {
            e.printStackTrace();
        }
    }
}
