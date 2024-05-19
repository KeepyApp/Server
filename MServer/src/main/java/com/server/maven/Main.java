package com.server.maven;

import com.google.gson.Gson;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
        Gson gson = new Gson();
        System.out.println(gson.toJson("Hello Json"));
        System.out.println(gson.toJson("ck"));
    }

}