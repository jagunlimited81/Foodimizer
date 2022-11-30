package edu.ilstu.Foodimizer.Controller;

import javax.swing.*;

public class SearchFunction {
    RecipeSearchController recipeSearchController;
    IngredientSearchController ingredientSearchController;

    public SearchFunction(RecipeSearchController r) {
        this.recipeSearchController = r;
    }

    public SearchFunction(IngredientSearchController i) {
        this.ingredientSearchController = i;
    }
    public void searchRecipe(JButton jButton) {
        jButton.addActionListener(recipeSearchController);
    }

    public void searchIngredient(JButton jButton) {
        jButton.addActionListener(ingredientSearchController);
    }
}
