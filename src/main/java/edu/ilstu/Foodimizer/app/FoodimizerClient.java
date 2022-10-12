package edu.ilstu.Foodimizer.app;

import edu.ilstu.Foodimizer.lib.Profile;
import edu.ilstu.Foodimizer.lib.Recipe;

import java.util.ArrayList;
import java.util.List;

public interface FoodimizerClient {
    public void addToPantry(Recipe recipe);
    public void addToGroceryList(Recipe recipe);
    public ArrayList<Profile> getAllProfiles();
    public ArrayList<Recipe> getAllRecipes();

}
