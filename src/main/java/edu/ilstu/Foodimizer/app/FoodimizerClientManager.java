package edu.ilstu.Foodimizer.app;

import edu.ilstu.Foodimizer.app.db.models.Ingredient;
import edu.ilstu.Foodimizer.app.db.models.Profile;
import edu.ilstu.Foodimizer.app.db.models.Recipe;
import edu.ilstu.Foodimizer.app.db.DatabaseProcessor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FoodimizerClientManager implements FoodimizerClient {
    private DatabaseProcessor recipeDB;

    public FoodimizerClientManager() {
        init();
    }

    private void init() {
        recipeDB = new DatabaseProcessor();
        recipeDB.createProfiles();
        recipeDB.createIngredients();
        recipeDB.createRecipes();
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
    public void updateProfile() {

    }

    @Override
    public void removeProfile() {

    }


    @Override
    public Set<Profile> getAllProfiles() {
        List<Profile> profiles = recipeDB.getAllProfilesFromDB();

        return new HashSet<>(profiles);
    }

    @Override
    public Set<Recipe> getAllRecipes() {
        return null;
    }

    @Override
    public Recipe getRecipesFromIngredients(ArrayList<Ingredient> ingredients) {
        return null;
    }

    /**
     * @param profile
     * @inheritDoc
     */
    @Override
    public void setActiveProfile(Profile profile) {

    }

    @Override
    public void addToPantry(Ingredient ingredient) {

    }

    @Override
    public void removeIngredientFromPantry(Ingredient ingredient) {

    }

    @Override
    public Set<Ingredient> getPantry() {
        return null;
    }

    @Override
    public void addMissingIngredientsToGroceryList(Recipe recipe) {

    }

    @Override
    public void addIngredientToGroceryList(Ingredient ingredient) {

    }

    @Override
    public void removeIngredientFromGroceryList(Ingredient ingredient) {

    }
}
