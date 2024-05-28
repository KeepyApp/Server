package com.server.maven.firebase;

import java.io.IOException;
import java.io.InputStream;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.core.io.ClassPathResource;

public class FirebaseInitializer {
    public static void initialize() throws IOException {
        ClassPathResource serviceAccount = new ClassPathResource("C:\\Users\\taird\\Desktop\\Server\\MServer\\src\\main\\resources\\keppy-5ed11-firebase-adminsdk-50u8y-b7b3f50796.json");
        InputStream serviceAccountStream = serviceAccount.getInputStream();

        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccountStream))
                .build();

        FirebaseApp.initializeApp(options);
    }
}
