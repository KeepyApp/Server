package com.server.maven.kinderGarten;

import org.springframework.stereotype.Service;
import java.util.Map;

@Service
public class KindergartenManager {
    private Map<String, Kindergarten> kindergartens;

    public KindergartenManager() {
        kindergartens = null;
    }
    public KindergartenManager(Map<String, Kindergarten> kindergartens) {
        this.kindergartens = kindergartens;
    }

    public Kindergarten findTheRelevantKinderGarten(String jsonData) {
        return new Kindergarten();
    }

    public Map<String, Kindergarten> getKindergartens() {
        return kindergartens;
    }

    public void setKindergartens(Map<String, Kindergarten> kindergartens) {
        this.kindergartens = kindergartens;
    }
}
