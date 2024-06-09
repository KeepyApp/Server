package com.server.maven.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;

@Configuration
public class FirebaseConfig {

    /*  @Bean
    public FirebaseApp firebaseApp() throws IOException {
      FileInputStream serviceAccount = new FileInputStream("C:\\Users\\taird\\Desktop\\Server\\MServer\\src\\main\\resources\\keppy-5ed11-firebase-adminsdk-50u8y-b7b3f50796.json");

        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();

        return FirebaseApp.initializeApp(options);
    }*/
}
