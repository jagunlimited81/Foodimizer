package edu.ilstu.Foodimizer.app;

import edu.ilstu.Foodimizer.app.db.models.Ingredient;
import edu.ilstu.Foodimizer.app.db.models.Profile;
import edu.ilstu.Foodimizer.app.db.models.Recipe;

import java.util.ArrayList;

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
     * @param p
     */
    void createProfile(Profile p);

    /**
     *
     */
    void updateProfile();

    /**
     *
     */
    void removeProfile();

    /**
     * @return
     */
    ArrayList<Profile> getAllProfiles();


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
     * @param ingredient
     */
    void removeIngredientFromPantry(Ingredient ingredient);

    /**
     * @return
     */
    ArrayList<Ingredient> getPantry();


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
     * @param ingredient
     */
    void removeIngredientFromGroceryList(Ingredient ingredient);


    /* ----- Recipes ----- */

    /**
     * @return
     */
    ArrayList<Recipe> getAllRecipes();

    /**
     * @param ingredients
     * @return
     */
    Recipe getRecipesFromIngredients(ArrayList<Ingredient> ingredients);

}
