package com.server.maven.mainController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.cloud.StorageClient;
import com.server.maven.alert.AlertManager;
import com.server.maven.analysis.AnalysisEngine;
import com.server.maven.firebase.FirebaseInitializer;
import com.server.maven.kinderGarten.Kindergarten;
import com.server.maven.kinderGarten.KindergartenManager;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class MainController {

    private final AlertManager alertManager;
    private final KindergartenManager kindergartenManager;
    private final FirebaseDatabase firebaseDatabase;
    private final AnalysisEngine analysisEngine;

    @Autowired
    public MainController(KindergartenManager kindergartenManager) {
        this.alertManager = new AlertManager();
        this.analysisEngine = new AnalysisEngine(this.alertManager ,this );
        this.kindergartenManager = kindergartenManager;
        this.firebaseDatabase = FirebaseDatabase.getInstance("https://keepyapp-e4d50-default-rtdb.europe-west1.firebasedatabase.app/");
    }

    @PostConstruct
    public void init() {
         kindergartenManager.updateKindergartenManager();
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

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(jsonData);

            printEventDetails(jsonNode);
            String kindergartenName = jsonNode.get("kindergarten_name").asText();
            kindergartenManager.updateKindergartenManager();
            Kindergarten kindergarten = kindergartenManager.findTheRelevantKinderGarten(kindergartenName);
            if (kindergarten != null) {
                // Pass data to the AnalysisEngine for processing
                analysisEngine.analyzeEvent(kindergartenName, jsonNode, kindergarten.getParentID());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveEventToFirebase(String kindergartenName, JsonNode eventData) {
        DatabaseReference ref = firebaseDatabase.getReference("kindergartens").child(kindergartenName).child("events");
        String eventId = eventData.get("id").asText();

        Map<String, Object> eventDetails = new HashMap<>();
        eventDetails.put("event", eventData.get("event").asText());
        eventDetails.put("timestamp", eventData.get("timestamp").asText());
        eventDetails.put("id", eventId);
        eventDetails.put("kindergarten_name", eventData.get("kindergarten_name").asText());
        if (eventData.has("word")) {
            eventDetails.put("word", eventData.get("word").asText());
        }
        if (eventData.has("sentence")) {
            eventDetails.put("sentence", eventData.get("sentence").asText());
        }

        ref.child(eventId).setValueAsync(eventDetails);
    }

    private static void printEventDetails(JsonNode jsonNode) {
        String event = jsonNode.get("event").asText();
        String timestamp = jsonNode.get("timestamp").asText();
        String id = jsonNode.get("id").asText();
        String kindergartenName = jsonNode.get("kindergarten_name").asText();
        String word = jsonNode.has("word") ? jsonNode.get("word").asText() : null;
        String sentence = jsonNode.has("sentence") ? jsonNode.get("sentence").asText() : null;

        System.out.println("Event: " + event);
        System.out.println("Timestamp: " + timestamp);
        System.out.println("ID: " + id);
        System.out.println("kindergarten_name: " + kindergartenName);
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
            // Save the file temporarily
            String uploadDir = System.getProperty("java.io.tmpdir") + "/uploaded_audio_files/";
            File uploadFolder = new File(uploadDir);
            if (!uploadFolder.exists()) {
                uploadFolder.mkdirs();
            }

            File uploadedFile = new File(uploadDir + file.getOriginalFilename());
            file.transferTo(uploadedFile);

            // Upload the file to Firebase Storage
            StorageClient storageClient = FirebaseInitializer.getStorageClient();
            storageClient.bucket().create(file.getOriginalFilename(), new FileInputStream(uploadedFile), file.getContentType());

            System.out.println("Audio file uploaded successfully to Firebase Storage: " + uploadedFile.getPath());
            return ResponseEntity.ok("File uploaded successfully to Firebase Storage");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file");
        }
    }
}
