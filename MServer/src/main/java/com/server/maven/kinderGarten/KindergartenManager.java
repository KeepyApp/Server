package com.server.maven.kinderGarten;

import java.util.Map;

public class KindergartenManager {
    private Map<String, Kindergarten> kindergartens;

    public KindergartenManager(Map<String, Kindergarten> kindergartens) {
        this.kindergartens = kindergartens;
    }

    public Kindergarten findTheRelevantKinderGarten(String jsonData) {
        return new Kindergarten("Shoshana");
    }

    public Map<String, Kindergarten> getKindergartens() {
        return kindergartens;
    }

    public void setKindergartens(Map<String, Kindergarten> kindergartens) {
        this.kindergartens = kindergartens;
    }
}

