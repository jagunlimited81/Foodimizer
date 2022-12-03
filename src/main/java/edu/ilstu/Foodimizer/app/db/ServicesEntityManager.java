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

    }

    /**
     * @return Singleton Instance of FoodimizerEntityManager
     */
    public static ServicesEntityManager getInstance() {
        if (instance == null)
            instance = new ServicesEntityManager();
        return instance;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        ServicesEntityManager.em = em;
    }

    /**
     * @return EntityManager
     */
    public EntityManager getEntityManager() {
        return em;
    }

    public void init(String persistenceUnitName) {
        em = Persistence.createEntityManagerFactory(persistenceUnitName).createEntityManager();
    }
}
