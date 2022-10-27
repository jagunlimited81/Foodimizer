package edu.ilstu.Foodimizer.ui.pages;

import edu.ilstu.Foodimizer.Main;
import edu.ilstu.Foodimizer.app.db.models.Recipe;
import edu.ilstu.Foodimizer.app.db.service.RecipeService;
import edu.ilstu.Foodimizer.ui.MainWindowContentManager;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class FindRecipesByName extends JPanel {
    public FindRecipesByName() {
        init();
    }

    private void init() {
        List<Recipe> recipes = new RecipeService().getAll();
        System.out.println(recipes);
        for (Recipe recipe : recipes) {
            JButton testbutton = new JButton(recipe.getName());
            testbutton.addActionListener(e->buttonPressed(recipe));
            add(testbutton);
        }
    }

    private void buttonPressed(Recipe r) {
        contentManager = MainWindowContentManager.getInstance();
        RecipePage.getInstance().setActiveRecipe(r);
        contentManager.goToPage("RecipePage");
    }

    private MainWindowContentManager contentManager;
    private RecipePage recipePage;
}
