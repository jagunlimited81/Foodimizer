package edu.ilstu.Foodimizer.app.db.service;

import edu.ilstu.Foodimizer.app.db.models.Ingredient;
import edu.ilstu.Foodimizer.app.db.models.Recipe;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Root;
import org.hibernate.cfg.NotYetImplementedException;

import java.util.List;


public class RecipeService extends Service<Recipe> {

    public List<Recipe> getAll() {
        return super.getAll(Recipe.class);
    }

    public List<Recipe> searchLikeString(String s) {
        // TODO SQL INJECTION ATTACK MITIGATION
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Recipe> cq = cb.createQuery(Recipe.class);

        // SELECT recipe FROM Recipe WHERE LOWER(recipe) LIKE LOWER('%s%')
        Root<Recipe> rootEntity = cq.from(Recipe.class);
        CriteriaQuery<Recipe> byNameQuery = cq.select(rootEntity).where(cb.like(cb.lower(rootEntity.get("name")), cb.lower(cb.literal("%" + s + "%"))));

        TypedQuery<Recipe> byName = em.createQuery(byNameQuery);
        return byName.getResultList();
    }
    public List<Recipe> searchByIngredients(List<Ingredient> ingredientList) {
        // search for recipes that have these ingredients
        // and also recipes that have a subset of the given parameter
        // TODO SQL INJECTION ATTACK MITIGATION
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Recipe> cq = cb.createQuery(Recipe.class);

        // SELECT * FROM Recipe
        Root<Recipe> rootRecipes = cq.from(Recipe.class);
        Root<Ingredient> rootIngredients = cq.from(Ingredient.class);
        CriteriaBuilder.In<Long> inClause = cb.in(rootIngredients.get("ingredientId"));
        for (Ingredient i : ingredientList){
            inClause.value(i.getIngredientId());
        }
        CriteriaQuery<Recipe> byNameQuery = cq.select(rootRecipes);

        TypedQuery<Recipe> byName = em.createQuery(byNameQuery);
        return byName.getResultList();
    }
}
