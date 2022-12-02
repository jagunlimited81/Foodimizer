package edu.ilstu.Foodimizer.ui.pages;

import edu.ilstu.Foodimizer.app.StateManager;
import edu.ilstu.Foodimizer.app.db.models.Ingredient;
import edu.ilstu.Foodimizer.app.db.models.Recipe;
import edu.ilstu.Foodimizer.app.db.service.ProfileService;
import edu.ilstu.Foodimizer.app.db.service.RecipeService;
import edu.ilstu.Foodimizer.ui.MainWindowContentManager;
import edu.ilstu.Foodimizer.ui.jcomponents.StarRating;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class RecipeSearchResultsPage extends Page {

    public RecipeSearchResultsPage() {
        init();
    }

    public static RecipeSearchResultsPage getInstance() {
        if (instance == null)
            instance = new RecipeSearchResultsPage();
        return instance;
    }

    @Override
    protected void init() {
        if (StateManager.getInstance().getActiveProfile() == null)
            return;

        contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        RecipeService rs = new RecipeService();

        ArrayList<Recipe> recipesOnPage = new ArrayList<>(
                rs.searchByIngredients(
                        new ArrayList<>(
                                StateManager.getInstance().getActiveProfile().getPantry())));
        for (Recipe recipe : recipesOnPage) {
            JPanel recipeResult = new JPanel();
            recipeResult.setLayout(new BorderLayout());
            recipeResult.setBorder(BorderFactory.createLineBorder(Color.gray));

            JPanel recipeImage = new JPanel();
            recipeImage.setBackground(Color.gray);
            recipeImage.setMaximumSize(new Dimension(100, 100));
            recipeImage.setPreferredSize(new Dimension(100, 100));
            recipeImage.setMinimumSize(new Dimension(100, 100));
            recipeResult.add(recipeImage, BorderLayout.LINE_START);

            JPanel RecipeInfoPanel = new JPanel();
            JLabel recipeName = new JLabel(recipe.getName());
            recipeName.setFont(new Font("Verdana", Font.PLAIN, 18));
            RecipeInfoPanel.add(recipeName, BorderLayout.NORTH);

            JLabel recipeDesc = new JLabel(recipe.getDescription());
            recipeName.setFont(new Font("Verdana", Font.PLAIN, 13));
            RecipeInfoPanel.add(recipeDesc, BorderLayout.CENTER);
            recipeResult.add(RecipeInfoPanel, BorderLayout.CENTER);

            recipeResult.add(new StarRating(recipe), BorderLayout.SOUTH);

            recipeResult.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    RecipePage.getInstance().setRecipe(recipe);
                    MainWindowContentManager.getInstance().goToPage("RecipePage");
                }
            });

            contentPanel.add(recipeResult);
        }
        this.add(contentPanel);
    }


    JPanel contentPanel;
    private static RecipeSearchResultsPage instance = null;
}
