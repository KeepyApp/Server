package com.server.maven.alert;
import org.springframework.stereotype.Component;

@Component
public class AlertManager {
    public void sendAlert(String recipient, String message) {
        // Send alert to the specified recipient through appropriate channel
        System.out.println("Alert sent to " + recipient + ": " + message);
    }
}
