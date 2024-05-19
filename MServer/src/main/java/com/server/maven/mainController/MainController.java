package com.server.maven.mainController;


import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    @RequestMapping(
            path = { "/hello" }, method = { RequestMethod.GET }, produces = {
            MediaType.APPLICATION_JSON_VALUE })
    public String hello(String data) {
        return "Tal Hamalka";
    }

    public void communicateWithPython(String data) {
        // Send data to Python component via HTTP requests
    }

    public void interactWithDatabase(String query) {
        // Interact with the database for data storage and retrieval
    }
}