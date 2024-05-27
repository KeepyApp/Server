package com.server.maven.mainController;


import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.server.maven.alert.AlertManager;
import com.server.maven.database.DatabaseManager;
import com.server.maven.firebase.FireBaseManager;
import com.server.maven.kinderGarten.Kindergarten;
import com.server.maven.kinderGarten.KindergartenManager;
import org.springframework.web.bind.annotation.*;

@RestController
public class MainController {
    private DatabaseManager databaseManager;
    private FireBaseManager firebaseManager;
    private AlertManager alertManager;
    private KindergartenManager kindergartenManager;

    public MainController(DatabaseManager databaseManager, FireBaseManager firebaseManager, AlertManager alertManager) {
        this.databaseManager = databaseManager;
        this.firebaseManager = firebaseManager;
        this.alertManager = alertManager;
    }

    public void interactWithDatabase(String query) {
        // Interact with the database for data storage and retrieval
    }

    @PostMapping("/process-data")
    public void processData(@RequestBody String jsonData) {
        System.out.println("Received data from Python script: " + jsonData);
        String idToken = "";//TODO///////////////////////////////////////
        if (!firebaseManager.authenticateUser(idToken)) {
            System.out.println("Authentication failed. Invalid ID token.");
            return;
        }

        String userId = firebaseManager.fetchUserInfo(idToken);
        System.out.println("User ID: " + userId);

        Kindergarten kindergarten = kindergartenManager.findTheRelevantKinderGarten(jsonData);
        String parentID = kindergarten.getParentID();

        alertManager.sendAlert(parentID, kindergarten, jsonData);
    }
}