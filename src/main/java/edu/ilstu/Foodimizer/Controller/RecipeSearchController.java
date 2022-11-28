package edu.ilstu.Foodimizer.Controller;

import edu.ilstu.Foodimizer.app.db.models.Recipe;
import edu.ilstu.Foodimizer.app.db.service.RecipeService;
import edu.ilstu.Foodimizer.ui.MainWindowContentManager;
import edu.ilstu.Foodimizer.ui.pages.RecipePage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class RecipeSearchController implements ActionListener {

    JTextField searchText;

    public RecipeSearchController(JTextField jTextField) {
        searchText = jTextField;
    }

    public RecipeSearchController(JComboBox jComboBox) {
        searchText = new JTextField(String.valueOf(jComboBox.getSelectedItem()));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        List<Recipe> recipes = new RecipeService().getAll();
        String recipe_name = searchText.getText();
        boolean notFoundMessage = true;
        for (Recipe recipe: recipes) {
            if (recipe_name.equals(recipe.getName())) {
                buttonPressed(recipe);
                notFoundMessage = false;
            }
        }
            if (notFoundMessage)
                JOptionPane.showMessageDialog(new JFrame(), "No Recipe Found", "Dialog",
                        JOptionPane.ERROR_MESSAGE);
    }

    private void buttonPressed(Recipe r) {
        MainWindowContentManager contentManager = MainWindowContentManager.getInstance();
        RecipePage.getInstance().setActiveRecipe(r);
        contentManager.goToPage("RecipePage");
    }
}


