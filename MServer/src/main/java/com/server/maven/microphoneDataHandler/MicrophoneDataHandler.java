package com.server.maven.microphoneDataHandler;

import com.server.maven.analysis.AnalysisEngine;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MicrophoneDataHandler {
    private AnalysisEngine analysisEngine;

//    public MicrophoneDataHandler(AnalysisEngine analysisEngine) {
//        this.analysisEngine = analysisEngine;
//    }

    @PostMapping("/process-data")
    public void processData(@RequestBody String jsonData) {
        // Process the received JSON data here
        System.out.println("Received data from Python script: " + jsonData);

        // Perform any necessary processing
    }
    public void receiveAudioData(byte[] audioData) {
        // Receive raw audio data from Python component
        // Process the audio data
        String processedData = processAudioData(audioData);

        // Forward processed data to the AnalysisEngine
        analysisEngine.analyzeData(processedData);
    }

    private String processAudioData(byte[] audioData) {
        // Process audio data and extract relevant information
        String processedData = ""; // Processed data containing event information
        return processedData;
    }
}
