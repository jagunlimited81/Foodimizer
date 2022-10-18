package edu.ilstu.Foodimizer.app.db.service;

import edu.ilstu.Foodimizer.app.db.models.Profile;
import edu.ilstu.Foodimizer.app.db.ServicesEntityManager;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class ProfileService implements Service<Profile> {
    EntityManager em;
    public ProfileService() {
        em = ServicesEntityManager.getInstance().getEntityManager();
    }

    @Override
    public Optional<Profile> get(long id) {
        return Optional.ofNullable(em.find(Profile.class, id));
    }

    @Override
    public List<Profile> getAll() {
        return em.createQuery("FROM Profile").getResultList();
    }

    @Override
    public void save(Profile profile) {
        executeInsideTransaction(em -> em.persist(profile));
    }

    @Override
    public void update(Profile profile, String params) {
        executeInsideTransaction(em -> em.merge(profile));
    }

    @Override
    public void delete(Profile profile) {
        executeInsideTransaction(em -> em.remove(profile));
    }

    private void executeInsideTransaction(Consumer<EntityManager> action) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            action.accept(em);
            tx.commit();
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        }
    }
}
