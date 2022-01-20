package com.deli.config;

import org.springframework.stereotype.Component;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


@Component
public class PersistenceUnit {

    public EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("com.example_DeliApp_jar_0.0.1-SNAPSHOTPU");
    private static com.deli.config.PersistenceUnit pu;
    private PersistenceUnit() {
    }
    public static com.deli.config.PersistenceUnit getInstance(){
        if(pu == null){
            return new com.deli.config.PersistenceUnit();
        }
        return pu;
    }

}
