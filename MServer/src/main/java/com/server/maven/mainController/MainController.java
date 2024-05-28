package com.server.maven.mainController;

import com.server.maven.alert.AlertManager;
import com.server.maven.database.DatabaseManager;
//import com.server.maven.firebase.FirebaseAuthManager;
import com.server.maven.kinderGarten.Kindergarten;
import com.server.maven.kinderGarten.KindergartenManager;
import org.springframework.web.bind.annotation.*;

@RestController
public class MainController {
    private final DatabaseManager databaseManager;
    //private final FirebaseAuthManager firebaseAuthManager;
    private final AlertManager alertManager;
    private final KindergartenManager kindergartenManager;

//    public MainController(DatabaseManager databaseManager, FirebaseAuthManager firebaseAuthManager,
//                          AlertManager alertManager, KindergartenManager kindergartenManager) {
//        this.databaseManager = databaseManager;
//        this.firebaseAuthManager = firebaseAuthManager;
//        this.alertManager = alertManager;
//        this.kindergartenManager = kindergartenManager;
//    }
    public MainController(DatabaseManager databaseManager,
                          AlertManager alertManager, KindergartenManager kindergartenManager) {
        this.databaseManager = databaseManager;
        this.alertManager = alertManager;
        this.kindergartenManager = kindergartenManager;
    }

    @PostMapping("/process-data")
    public void processData(@RequestBody String jsonData) {
        System.out.println("Received data from Python script: " + jsonData);
        //String idToken = ""; // TODO: Get the ID token from the request

//        if (!firebaseAuthManager.authenticateUser(idToken)) {
//            System.out.println("Authentication failed. Invalid ID token.");
//            return;
//        }

        //String userId = firebaseAuthManager.fetchUserInfo(idToken);
        //System.out.println("User ID: " + userId);

        Kindergarten kindergarten = kindergartenManager.findTheRelevantKinderGarten(jsonData);
        String parentId = kindergarten.getParentID();

        alertManager.sendAlert(parentId, kindergarten, jsonData);
    }
}
