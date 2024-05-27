package com.server.maven.firebase;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import org.springframework.stereotype.Component;

@Component
public class FireBaseManager {
    private FirebaseAuth firebaseAuth;
    public FireBaseManager() {
        this.firebaseAuth = FirebaseAuth.getInstance();
    }
    public boolean authenticateUser(String idToken) {
        try {
            FirebaseToken decodedToken = firebaseAuth.verifyIdToken(idToken);
            return decodedToken != null;
        } catch (FirebaseAuthException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String fetchUserInfo(String idToken) {
        try {
            FirebaseToken decodedToken = firebaseAuth.verifyIdToken(idToken);
            return decodedToken.getUid();
        } catch (FirebaseAuthException e) {
            e.printStackTrace();
            return null;
        }
    }
}
