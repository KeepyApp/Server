//package com.server.maven.firebase;
//
//import com.google.firebase.FirebaseApp;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseAuthException;
//import com.google.firebase.auth.FirebaseToken;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
///**
// * Manages Firebase authentication operations.
// */
//@Component
//public class FirebaseAuthManager {
//
//    private final FirebaseAuth firebaseAuth;
//
//    /**
//     * Constructs a new FirebaseAuthManager with the provided FirebaseApp.
//     *
//     * @param firebaseApp The FirebaseApp instance to use for authentication.
//     */
//    @Autowired
//    public FirebaseAuthManager(FirebaseApp firebaseApp) {
//        this.firebaseAuth = FirebaseAuth.getInstance(firebaseApp);
//    }
//
//    /**
//     * Authenticates a user with the given ID token.
//     *
//     * @param idToken The ID token of the user to authenticate.
//     * @return true if the user is authenticated, false otherwise.
//     */
//    public boolean authenticateUser(String idToken) {
//        try {
//            FirebaseToken decodedToken = firebaseAuth.verifyIdToken(idToken);
//            return decodedToken != null;
//        } catch (FirebaseAuthException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//    /**
//     * Fetches user information for the given ID token.
//     *
//     * @param idToken The ID token of the user.
//     * @return The UID (user ID) of the authenticated user, or null if authentication fails.
//     */
//    public String fetchUserInfo(String idToken) {
//        try {
//            FirebaseToken decodedToken = firebaseAuth.verifyIdToken(idToken);
//            return decodedToken.getUid();
//        } catch (FirebaseAuthException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//}
