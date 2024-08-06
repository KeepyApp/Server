package com.server.maven.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.cloud.StorageClient;
import com.google.firebase.database.*;
import com.google.firebase.messaging.FirebaseMessaging;
import com.server.maven.kinderGarten.Kindergarten;
import com.server.maven.kinderGarten.KindergartenManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Component
public class FirebaseInitializer {

    private static final Logger logger = LoggerFactory.getLogger(FirebaseInitializer.class);
    private static final String DATABASE_URL = "https://keppy-5ed11.firebaseio.com/";
    @PostConstruct
    public void initialize() {
        try (InputStream serviceAccount = getClass().getClassLoader().getResourceAsStream("serviceAccountKey.json")) {
            if (serviceAccount == null) {
                throw new IllegalStateException("Service account key file not found");
            }

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setStorageBucket("keppy-5ed11.appspot.com")  // Add your bucket name here
                    .build();

            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp app = FirebaseApp.initializeApp(options);
                if (app == null) {
                    throw new RuntimeException("Failed to initialize FirebaseApp");
                }
            }

        } catch (IOException e) {
            throw new RuntimeException("Failed to initialize Firebase", e);
        }
    }

    public static Firestore getFirestore() {
        return FirestoreClient.getFirestore();
    }

    public static FirebaseMessaging getFirebaseMessaging() {
        return FirebaseMessaging.getInstance();
    }

    public static StorageClient getStorageClient() {
        return StorageClient.getInstance();
    }

    public static DatabaseReference getRealtimeDatabase() {
        return FirebaseDatabase.getInstance(DATABASE_URL).getReference();
    }

}
