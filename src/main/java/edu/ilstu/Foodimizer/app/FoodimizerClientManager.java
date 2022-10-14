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
    public void createProfile(Profile profile) {

    }

    @Override
    public void updateProfile(Profile profile) {

    }

    @Override
    public void removeProfile(Profile profile) {

    }


    @Override
    public Set<Profile> getAllProfiles() {
        List<Profile> profiles = recipeDB.getAllProfilesFromDB();

        return new HashSet<>(profiles);
    }

    @Override
    public Set<Recipe> getAllRecipes() {
        List<Recipe> Recipe = recipeDB.getAllRecipesFromDB();

        return new HashSet<>(Recipe);
    }

    @Override
    public Set<Recipe> getRecipesFromIngredients(List<Ingredient> ingredients) {
        return null;
    }


    @Override
    public void setActiveProfile(Profile profile) {

    }

    @Override
    public void addToPantry(Ingredient ingredient) {

    }

    @Override
    public void addToPantry(List<Ingredient> ingredients) {

    }

    @Override
    public void removeIngredientFromPantry(Ingredient ingredient) {

    }

    @Override
    public void removeIngredientsFromPantry(List<Ingredient> ingredients) {

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
    public void addIngredientsToGroceryList(List<Ingredient> ingredients) {

    }

    @Override
    public void removeIngredientFromGroceryList(Ingredient ingredient) {

    }
}
