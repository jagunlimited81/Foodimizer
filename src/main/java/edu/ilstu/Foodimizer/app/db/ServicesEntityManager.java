package edu.ilstu.Foodimizer.app.db;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

/**
 * This Singleton class provides the entity manager for Foodimizer
 */
public class ServicesEntityManager {
    private static ServicesEntityManager instance;
    private static EntityManager em;

    private ServicesEntityManager() {
        em = Persistence.createEntityManagerFactory("FoodimizerDB").createEntityManager();
    }


    /**
     * @return EntityManager
     */
    public EntityManager getEntityManager() {
        return em;
    }

    /**
     * @return Singleton Instance of FoodimizerEntityManager
     */
    public static ServicesEntityManager getInstance() {
        if (instance == null)
            instance = new ServicesEntityManager();
        return instance;
    }
}
