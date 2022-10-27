package edu.ilstu.Foodimizer.app.db.service;

import edu.ilstu.Foodimizer.app.db.models.Recipe;
import edu.ilstu.Foodimizer.app.db.ServicesEntityManager;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;


public class RecipeService implements Service<Recipe> {
    private EntityManager em;

    public RecipeService() {
        em = ServicesEntityManager.getInstance().getEntityManager();
    }

    @Override
    public Optional<Recipe> get(long id) {
        return Optional.ofNullable(em.find(Recipe.class, id));
    }

    @Override
    public List<Recipe> getAll() {
        return em.createQuery("FROM Recipe").getResultList();
    }

    @Override
    public void save(Recipe recipe) {
        executeInsideTransaction(em -> em.persist(recipe));
    }

    @Override
    public void update(Recipe recipe, String params) {
        executeInsideTransaction(em -> em.merge(recipe));
    }

    @Override
    public void delete(Recipe recipe) {
        executeInsideTransaction(em -> em.remove(recipe));
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
