package edu.ilstu.Foodimizer.Controller;

import edu.ilstu.Foodimizer.app.db.models.Recipe;
import edu.ilstu.Foodimizer.app.db.service.RatingService;
import edu.ilstu.Foodimizer.app.db.service.RecipeService;
import edu.ilstu.Foodimizer.lib.RecipeComparator;
import edu.ilstu.Foodimizer.ui.MainWindowContentManager;
import edu.ilstu.Foodimizer.ui.pages.RecipePage;
import edu.ilstu.Foodimizer.ui.pages.RecipeSearchResultsPage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class RecipeSearchController implements ActionListener {

    JTextField searchText;
    JComboBox comboBox;
    JButton jButton;

    public RecipeSearchController(JTextField jTextField, JButton jButton) {
        searchText = jTextField;
        this.jButton = jButton;
    }

    public RecipeSearchController(JComboBox jComboBox) {
        comboBox = jComboBox;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        searchRecipeByName();
    }

    private void searchRecipeByName() {
        if (comboBox != null)
            searchText = new JTextField(String.valueOf(comboBox.getSelectedItem()));
        RecipeService rs = new RecipeService();
        ArrayList<Recipe> foundRecipes = new ArrayList<>(rs.searchLikeString(searchText.getText(), Recipe.class));
        if (checkRecipe(searchText.getText())) {
            JOptionPane.showMessageDialog(new JFrame(), "No Recipe Found", "Dialog",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        RatingService rates = new RatingService();
        foundRecipes.sort(RecipeComparator.getRatingComparator(rates).thenComparing(Recipe::getName));
        RecipeSearchResultsPage.getInstance().setActiveRecipes(foundRecipes);
        MainWindowContentManager.getInstance().goToPage("RecipeSearchResultsPage");
    }

    // Check if the recipe is in the pantry or not
    public boolean checkRecipe(String recipeName) {
        RecipeService rs = new RecipeService();
        ArrayList<Recipe> foundRecipes = new ArrayList<>(rs.searchLikeString(recipeName, Recipe.class));

        return foundRecipes.isEmpty();
    }

    private void buttonPressed(Recipe r) {
        MainWindowContentManager contentManager = MainWindowContentManager.getInstance();
        RecipePage.getInstance().setActiveRecipe(r);
        contentManager.goToPage("RecipePage");
    }
}


