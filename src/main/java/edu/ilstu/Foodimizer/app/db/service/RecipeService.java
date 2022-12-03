package edu.ilstu.Foodimizer.app.db.service;

import edu.ilstu.Foodimizer.app.db.models.Ingredient;
import edu.ilstu.Foodimizer.app.db.models.Recipe;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


public class RecipeService extends Service<Recipe> {

    public List<Recipe> getAll() {
        return super.getAll(Recipe.class);
    }

    /**
     * @param ingredientList a list of ingredients taken from the database.
     * @return a list of recipes that are compatible with the given ingredients
     */
    public List<Recipe> searchByIngredients(List<Ingredient> ingredientList) {
        ArrayList<Recipe> returnRecipes = new ArrayList<>();
        for (Ingredient ing : ingredientList) {
            ArrayList<Recipe> recipes = new ArrayList<>(ing.getRecipesThatContainThisIngredient());
            for (Recipe r : recipes) {
                if (new HashSet<>(ingredientList).containsAll(r.getRecipeIngredients())) {
                    if (!returnRecipes.contains(r)) {
                        returnRecipes.add(r);
                    }
                }
            }
        }
        return returnRecipes;
    }
}
