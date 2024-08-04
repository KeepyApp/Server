package com.server.maven.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.StorageClient;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.IOException;

public class FirebaseConfig {

   /* @PostConstruct
    public void initialize() {
        try {
            FileInputStream serviceAccount = new FileInputStream("serviceAccountKey.json");

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setStorageBucket("keppy.appspot.com")
                    .build();

            FirebaseApp.initializeApp(options);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
}
