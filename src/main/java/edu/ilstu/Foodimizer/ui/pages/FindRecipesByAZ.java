package edu.ilstu.Foodimizer.ui.pages;

import edu.ilstu.Foodimizer.app.StateManager;
import edu.ilstu.Foodimizer.app.db.models.Recipe;

import javax.swing.*;
import java.awt.*;

public class FindRecipesByAZ extends Page {
    private StateManager sm;
    private Recipe recipes;
    public FindRecipesByAZ() {
        init();
    }

    private void init() {
        contentPane = new JPanel();
        recipesPane = new JPanel();
        setBackground(Color.WHITE);
        sm = StateManager.getInstance();

        /*recipes = fcm.getAllRecipes();
        for (Recipe recipe : recipes) {
            JPanel recipePane = new JPanel();

            recipesPane.add(recipePane);
        }*/

    }
    private JPanel contentPane;
    private JPanel recipesPane;

}
