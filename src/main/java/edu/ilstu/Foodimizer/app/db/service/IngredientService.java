package edu.ilstu.Foodimizer.app.db.service;

import edu.ilstu.Foodimizer.app.db.models.Ingredient;
import jakarta.persistence.NoResultException;

import java.util.List;


public class IngredientService extends Service<Ingredient> {

    public List<Ingredient> getAll() {
        return super.getAll(Ingredient.class);
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


}
