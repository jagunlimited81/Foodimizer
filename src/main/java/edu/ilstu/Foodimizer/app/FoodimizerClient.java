package edu.ilstu.Foodimizer.app;

import edu.ilstu.Foodimizer.lib.Recipe;

public interface FoodimizerClient {
    public void addToPantry(Recipe recipe);
    public void addToGroceryList(Recipe recipe);

}
