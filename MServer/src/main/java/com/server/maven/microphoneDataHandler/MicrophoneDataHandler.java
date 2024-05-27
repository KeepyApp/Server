//package com.server.maven.microphoneDataHandler;
//
//import com.google.firebase.messaging.FirebaseMessaging;
//import com.google.firebase.messaging.Message;
//import com.server.maven.alert.AlertManager;
//import com.server.maven.analysis.AnalysisEngine;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class MicrophoneDataHandler {
//    private AnalysisEngine analysisEngine;
//    private AlertManager alertManager;
//
//    public MicrophoneDataHandler(AnalysisEngine analysisEngine, AlertManager alertManager) {
//        this.analysisEngine = analysisEngine;
//        this.alertManager = alertManager;
//    }
//
//    @PostMapping("/process-data")
//    public void processData(@RequestBody String jsonData) {
//        // Process the received JSON data here
//        System.out.println("Received data from Python script: " + jsonData);
//
//        // Send notification
//        sendNotification("FCM-Device-Token", "Alert", "There is something unusual");
//
//        alertManager.sendAlert("Taird", kindergarten, "There is something unusual");
//    }
//    public void receiveAudioData(byte[] audioData) {
//        // Receive raw audio data from Python component
//        // Process the audio data
//        String processedData = processAudioData(audioData);
//
//        // Forward processed data to the AnalysisEngine
//        analysisEngine.analyzeData(processedData);
//    }
//
//    private String processAudioData(byte[] audioData) {
//        // Process audio data and extract relevant information
//        String processedData = ""; // Processed data containing event information
//        return processedData;
//    }
//
//    private void sendNotification(String token, String title, String body) {
//        Message message = Message.builder()
//                //.setNotification(new Notification(title, body))
//                .setToken(token)
//                .build();
//
//        try {
//            String response = FirebaseMessaging.getInstance().send(message);
//            System.out.println("Successfully sent message: " + response);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
