package edu.ilstu.Foodimizer.app;

import edu.ilstu.Foodimizer.lib.Profile;
import edu.ilstu.Foodimizer.lib.Recipe;
import edu.ilstu.Foodimizer.app.db.RecipeDB;
import edu.ilstu.Foodimizer.ui.MainWindowContentManager;

import java.util.ArrayList;

public class FoodimizerClientManager implements FoodimizerClient {
    private RecipeDB recipeDB;

    public FoodimizerClientManager() {
        init();
    }

    private void init() {
        recipeDB = new RecipeDB();
    }

    public static FoodimizerClientManager getInstance() {
        if (instance == null)
            instance = new FoodimizerClientManager();
        return instance;
    }

    private static FoodimizerClientManager instance = null;

    @Override
    public void createProfile(Profile p) {

    }

    @Override
    public void addToPantry(Recipe recipe) {

    }

    @Override
    public void addToGroceryList(Recipe recipe) {

    }

    @Override
    public ArrayList<Profile> getAllProfiles() {
        ArrayList<Profile> profiles = new ArrayList<>();
        int numProfiles = 8;
        for (int i = 0; i < numProfiles; i++) {
            profiles.add(new Profile(i, "person"+i));
        }
        return profiles;
    }

    @Override
    public ArrayList<Recipe> getAllRecipes() {
        return null;
    }

    @Override
    public void setActiveProfile(Profile profile) {

    }
}
