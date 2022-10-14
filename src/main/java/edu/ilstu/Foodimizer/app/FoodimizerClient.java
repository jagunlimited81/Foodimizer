package edu.ilstu.Foodimizer.app;

import edu.ilstu.Foodimizer.app.db.models.Ingredient;
import edu.ilstu.Foodimizer.app.db.models.Profile;
import edu.ilstu.Foodimizer.app.db.models.Recipe;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * The FoodimizerClient stores all application state data, and
 * communicates with the RecipeDB. Actions of the FoodimizerClient are
 * absolute and change program behavior or state.
 * <p>
 * The FoodimizerClient should have no idea what the frontend is doing
 * It should act as the "glue" that anything and everything can talk to.
 */
public interface FoodimizerClient {
    /* ----- Profiles ----- */

    /**
     * @param profile
     */
    void createProfile(Profile profile);

    /**
     *
     */
    void updateProfile(Profile profile);

    /**
     *
     */
    void removeProfile(Profile profile);

    /**
     * @return
     */
    Set<Profile> getAllProfiles();


    /**
     * Sets the user profile.
     *
     * @param profile
     */
    void setActiveProfile(Profile profile);


    /* ----- Pantry ----- */

    /**
     * @param ingredient
     */
    void addToPantry(Ingredient ingredient);

    /**
     * @param ingredients
     */
    void addToPantry(List<Ingredient> ingredients);

    /**
     * @param ingredient
     */
    void removeIngredientFromPantry(Ingredient ingredient);

    /**
     * @param ingredients
     */
    void removeIngredientsFromPantry(List<Ingredient> ingredients);

    /**
     * @return
     */
    Set<Ingredient> getPantry();


    /* ----- Grocery List ----- */

    /**
     * @param recipe
     */
    void addMissingIngredientsToGroceryList(Recipe recipe);

    /**
     * @param ingredient
     */
    void addIngredientToGroceryList(Ingredient ingredient);

    /**
     * @param ingredients
     */
    void addIngredientsToGroceryList(List<Ingredient> ingredients);

    /**
     * @param ingredient
     */
    void removeIngredientFromGroceryList(Ingredient ingredient);


    /* ----- Recipes ----- */

    /**
     * @return
     */
    Set<Recipe> getAllRecipes();

    /**
     * @param ingredients
     * @return
     */
    Set<Recipe> getRecipesFromIngredients(List<Ingredient> ingredients);

}
