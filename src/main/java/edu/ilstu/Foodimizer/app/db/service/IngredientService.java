package edu.ilstu.Foodimizer.app.db.service;

import edu.ilstu.Foodimizer.app.db.models.Ingredient;
import edu.ilstu.Foodimizer.app.db.ServicesEntityManager;
import jakarta.persistence.*;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
public class IngredientService implements Service<Ingredient> {
    private EntityManager em;
    public IngredientService() {
        em = ServicesEntityManager.getInstance().getEntityManager();
    }
    @Override
    public Optional<Ingredient> get(long id) {
        return Optional.ofNullable(em.find(Ingredient.class, id));
    }

    /**
     * Queries the database for a single ingredient with the matching name
     *
     * @param name The name of the ingredient to search for
     * @return the ingredient instance or null
     */
    public Ingredient getFromName(String name) {
        try {
            return em.createQuery("FROM Ingredient p WHERE p.name = :i", Ingredient.class).setParameter("i", name).getSingleResult();
        } catch (NoResultException noResultException) {
            return null;
        }
    }

    @Override
    public List<Ingredient> getAll() {
        return em.createQuery("FROM Ingredient").getResultList();
    }

    @Override
    public void save(Ingredient ingredient) {
        executeInsideTransaction(em -> em.persist(ingredient));
    }

    @Override
    public void update(Ingredient ingredient, String params) {
        executeInsideTransaction(em -> em.merge(ingredient));
    }

    @Override
    public void delete(Ingredient ingredient) {
        executeInsideTransaction(em -> em.remove(ingredient));
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
