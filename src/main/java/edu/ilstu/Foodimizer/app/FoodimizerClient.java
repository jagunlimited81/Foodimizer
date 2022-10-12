package edu.ilstu.Foodimizer.app;

import edu.ilstu.Foodimizer.lib.Profile;
import edu.ilstu.Foodimizer.lib.Recipe;

import java.util.ArrayList;

/**
 * The FoodimizerClient stores all application state data, and
 * communicates with the RecipeDB. Actions of the FoodimizerClient are
 * absolute and change program behavior or state.
 * <p>
 * The FoodimizerClient should have no idea what the frontend is doing
 * It should act as the "glue" that anything and everything can talk to.
 *
 */
public interface FoodimizerClient {
    /**
     * @param p
     */
    void createProfile(Profile p);

    /**
     * @param recipe
     */
    void addToPantry(Recipe recipe);

    /**
     * @param recipe
     */
    void addToGroceryList(Recipe recipe);

    /**
     * @return
     */
    ArrayList<Profile> getAllProfiles();

    /**
     * @return
     */
    ArrayList<Recipe> getAllRecipes();

    /**
     * Sets the user profile.
     * @param profile
     */
    void setActiveProfile(Profile profile);
}
