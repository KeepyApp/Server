package com.server.maven.alert;

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

    public void sendAlert(String recipient, Kindergarten kindergarten, String message) {
        System.out.println("Alert sent to " + recipient + ": " + message);

        String token = userTokens.get(recipient);
        if (token != null) {
            sendNotification(token, "Alert", "There is something unusual");
        } else {
            System.out.println("No token found for recipient: " + recipient);
        }
    }

//    public void processEvent(String parentId, String kindergartenName) {
//        // Fetch the parent's FCM token from Firestore
//        Firestore db = FirebaseInitializer.getFirestore();
//        db.collection("users").document(parentId).get().addOnSuccessListener(documentSnapshot -> {
//            if (documentSnapshot.exists()) {
//                String token = documentSnapshot.getString("deviceToken");
//                String title = "Unusual Sound Detected";
//                String body = "An unusual sound was detected in " + kindergartenName;
//                notificationService.sendNotification(token, title, body);
//            }
//        }).addOnFailureListener(e -> {
//            e.printStackTrace();
// });
//}


    public void processEvent(String parentId, String kindergartenName, String jsonData) {
        String token = userTokens.get(parentId);
        if (token != null) {
            String title = "Unusual Sound Detected";
            String body = "An unusual sound was detected in " + kindergartenName;
                   // + "\nThe unusual word said in kindergarten is " + jsonData;
            notificationService.sendNotification(token, title, body);
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
