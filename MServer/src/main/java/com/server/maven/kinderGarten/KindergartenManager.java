package com.server.maven.kinderGarten;

import com.google.firebase.database.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class KindergartenManager {
    private Map<String, Kindergarten> kindergartens;
    private static final Logger logger = LoggerFactory.getLogger(KindergartenManager.class);

    public KindergartenManager() {
        kindergartens = new HashMap<>();
    }

    public KindergartenManager(Map<String, Kindergarten> kindergartens) {
        this.kindergartens = kindergartens;
    }

    public Kindergarten findTheRelevantKinderGarten(String kindergartenName) {
        return kindergartens.get(kindergartenName);
    }

    public Map<String, Kindergarten> getKindergartens() {
        return kindergartens;
    }

    public void setKindergartens(Map<String, Kindergarten> kindergartens) {
        this.kindergartens = kindergartens;
    }

    public void updateKindergartenManager() {
        DatabaseReference ref = FirebaseDatabase.getInstance("https://keepyapp-e4d50-default-rtdb.europe-west1.firebasedatabase.app/").getReference("kindergartens");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map<String, Kindergarten> updatedKindergartens = new HashMap<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    try {
                        Kindergarten kindergarten = snapshot.getValue(Kindergarten.class);

                        if (kindergarten != null) {
                            updatedKindergartens.put(kindergarten.getKindergartenName(), kindergarten);
                        }
                    } catch (Exception e) {
                        logger.error("Failed to load kindergarten: " + snapshot.getKey());
                        e.printStackTrace();
                    }
                }
                kindergartens.putAll(updatedKindergartens);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                logger.error("DatabaseError: " + databaseError.getMessage());
            }
        });


    }
}
