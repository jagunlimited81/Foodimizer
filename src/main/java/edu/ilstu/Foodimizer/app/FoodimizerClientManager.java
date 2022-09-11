package edu.ilstu.Foodimizer.app;

import edu.ilstu.Foodimizer.lib.Recipe;
import edu.ilstu.Foodimizer.ui.MainWindowContentManager;

public class FoodimizerClientManager implements FoodimizerClient{
    public FoodimizerClientManager() {
        init();
    }

    private void init() {
    }

    public static FoodimizerClientManager getInstance() {
        if (instance == null)
            instance = new FoodimizerClientManager();
        return instance;
    }

    private static FoodimizerClientManager instance = null;

    @Override
    public void addToPantry(Recipe recipe) {

    }

    @Override
    public void addToGroceryList(Recipe recipe) {

    }
}
