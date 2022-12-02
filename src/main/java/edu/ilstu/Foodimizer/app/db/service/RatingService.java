package edu.ilstu.Foodimizer.app.db.service;

import edu.ilstu.Foodimizer.app.db.models.JoinProfileRateRecipe;
import edu.ilstu.Foodimizer.app.db.models.Profile;
import edu.ilstu.Foodimizer.app.db.models.Recipe;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;

public class RatingService extends Service<JoinProfileRateRecipe> {
    public List<JoinProfileRateRecipe> getAll() {
        return super.getAll(JoinProfileRateRecipe.class);
    }

    public JoinProfileRateRecipe getRatingObj(Profile profile, Recipe recipe) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<JoinProfileRateRecipe> cq = cb.createQuery(JoinProfileRateRecipe.class);

        // SELECT recipe FROM Recipe WHERE LOWER(recipe) LIKE LOWER('%s%')
        Root<JoinProfileRateRecipe> rootEntity = cq.from(JoinProfileRateRecipe.class);
        Root<Profile> profileRoot = cq.from(Profile.class);
        Root<Recipe> recipeRoot = cq.from(Recipe.class);
        CriteriaQuery<JoinProfileRateRecipe> byNameQuery = cq.select(rootEntity).where(cb.and(cb.equal(rootEntity.get("profile").get("profileId"), profile.getProfileId()), cb.equal(rootEntity.get("recipe").get("recipeId"), recipe.getRecipeId())));
        TypedQuery<JoinProfileRateRecipe> byName = em.createQuery(byNameQuery);
        if (byName.getResultList().isEmpty())
            return null;
        return byName.getResultList().get(0);
    }
}
