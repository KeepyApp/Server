package com.server.maven.mainController;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.server.maven.alert.AlertManager;
import com.server.maven.kinderGarten.Kindergarten;
import com.server.maven.kinderGarten.KindergartenManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
public class MainController {

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

    /*@PostMapping("/process-data")
    public void processData(@RequestBody String jsonData) {
        System.out.println("Received data from Python script: " + jsonData);
        //String idToken = ""; // TODO: Get the ID token from the request

//        if (!firebaseAuthManager.authenticateUser(idToken)) {
//            System.out.println("Authentication failed. Invalid ID token.");
//            return;
//        }

        //String userId = firebaseAuthManager.fetchUserInfo(idToken);
        //System.out.println("User ID: " + userId);

        Kindergarten kindergarten = kindergartenManager.findTheRelevantKindergarten(jsonData);
        String parentId = kindergarten.getParentID();

        alertManager.processEvent("0525867338", "tali", jsonData);
    }
*/
    @PostMapping("/process-data")
    public void processData(@RequestBody String jsonData) {
        System.out.println("Received data from Python script: " + jsonData);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(jsonData);

            printEventDetails(jsonNode);

            // עיבוד נוסף כמו אחזור גן ילדים ושליחת התראה
          // Kindergarten kindergarten = kindergartenManager.findTheRelevantKinderGarten(jsonData);
           // String parentId = kindergarten.getParentID();

           alertManager.processEvent("0525867338", "tali", jsonNode);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void printEventDetails(JsonNode jsonNode) {
        String event = jsonNode.get("event").asText();
        String timestamp = jsonNode.get("timestamp").asText();
        String word = jsonNode.has("word") ? jsonNode.get("word").asText() : null;
        String sentence = jsonNode.has("sentence") ? jsonNode.get("sentence").asText() : null;

        System.out.println("Event: " + event);
        System.out.println("Timestamp: " + timestamp);
        if (word != null) {
            System.out.println("Word: " + word);
        }
        if (sentence != null) {
            System.out.println("Sentence: " + sentence);
        }
    }

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

    @PostMapping("/upload-audio")
    public ResponseEntity<String> uploadAudio(@RequestParam("file") MultipartFile file) {
        try {
            // שמירת הקובץ בתיקייה מקומית
            String uploadDir = System.getProperty("java.io.tmpdir") + "/uploaded_audio_files/";
            File uploadFolder = new File(uploadDir);
            if (!uploadFolder.exists()) {
                uploadFolder.mkdirs();
            }

            File uploadedFile = new File(uploadDir + file.getOriginalFilename());
            file.transferTo(uploadedFile);

            System.out.println("Audio file uploaded successfully: " + uploadedFile.getPath());
            return ResponseEntity.ok("File uploaded successfully");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file");
        }
    }


}
