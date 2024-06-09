package com.server.maven.mainController;


import com.server.maven.alert.AlertManager;
import com.server.maven.kinderGarten.Kindergarten;
import com.server.maven.kinderGarten.KindergartenManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MainController {
    //private final FirebaseAuthManager firebaseAuthManager;
    private final AlertManager alertManager;
    private final KindergartenManager kindergartenManager;

    public MainController() {

        //this.firebaseAuthManager = firebaseAuthManager;
        this.alertManager = new AlertManager();
        this.kindergartenManager = new KindergartenManager();
    }


    public static class TokenRequest {
        private String parentId;
        private String token;

        public String getParentId() {
            return parentId;
        }

        public void setParentId(String parentId) {
            this.parentId = parentId;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
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


         //alertManager.processEvent(parentId, kindergarten.getKindergartenName());
        alertManager.processEvent("0000000000", "oren");
    }


   // @PostMapping("/process-token")
   // public void processToken(@RequestBody TokenRequest tokenRequest) {
   //     System.out.println("Received token from client: " + tokenRequest.getToken());
        // Your logic to handle the token
   //     alertManager.addToken(tokenRequest.getParentId(), tokenRequest.getToken());
  //  }

    @PostMapping("/process-token")
    public ResponseEntity<Void> processToken(@RequestBody TokenRequest tokenRequest) {
        try {
            System.out.println("Received token from client: " + tokenRequest.getToken());
            // Your logic to handle the token
            alertManager.addToken(tokenRequest.getParentId(), tokenRequest.getToken());
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();  // Log the exception details
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }



}
