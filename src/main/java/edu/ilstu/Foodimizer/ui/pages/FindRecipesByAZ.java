package edu.ilstu.Foodimizer.ui.pages;

import edu.ilstu.Foodimizer.app.FoodimizerClientManager;
import edu.ilstu.Foodimizer.lib.Recipe;

import javax.swing.*;
import java.awt.*;

public class FindRecipesByAZ extends JPanel {
    private FoodimizerClientManager fcm;
    private Recipe recipes;
    public FindRecipesByAZ() {
        init();
    }

    private void init() {
        contentPane = new JPanel();
        recipesPane = new JPanel();
        setBackground(Color.WHITE);
        fcm = FoodimizerClientManager.getInstance();

        /*recipes = fcm.getAllRecipes();
        for (Recipe recipe : recipes) {
            JPanel recipePane = new JPanel();

            recipesPane.add(recipePane);
        }*/

    }
    private JPanel contentPane;
    private JPanel recipesPane;

}
