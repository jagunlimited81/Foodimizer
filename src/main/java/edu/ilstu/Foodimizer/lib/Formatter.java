package edu.ilstu.Foodimizer.lib;

import edu.ilstu.Foodimizer.app.db.models.Ingredient;
import edu.ilstu.Foodimizer.app.db.models.Recipe;

import java.util.List;

public abstract class Formatter {

    public abstract void formatRecipe(Recipe recipe);

    public abstract void formatShoppingList(List<Ingredient> shoppingList);
}
