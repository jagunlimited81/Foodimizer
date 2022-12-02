package edu.ilstu.Foodimizer.ui.jcomponents;

import edu.ilstu.Foodimizer.app.db.models.Recipe;

import javax.swing.*;
import java.awt.*;

public class RecipeSearchResult extends JPanel {

    private final Recipe recipe;
    public RecipeSearchResult(Recipe recipe) {
        this.recipe = recipe;
        init();
    }

    private void init() {

        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createLineBorder(Color.gray));
        /* Image */
        JPanel recipeImage = new JPanel();
        recipeImage.setBackground(Color.gray);
        recipeImage.setMaximumSize(new Dimension(100, 100));
        recipeImage.setPreferredSize(new Dimension(100, 100));
        recipeImage.setMinimumSize(new Dimension(100, 100));
        this.add(recipeImage, BorderLayout.LINE_START);
        /* Title */
        JPanel recipeTextPanel = new JPanel();
        recipeTextPanel.setLayout(new BorderLayout());
        JLabel recipeName = new JLabel(recipe.getName());
        recipeName.setFont(new Font("Verdana", Font.PLAIN, 18));
        recipeTextPanel.add(recipeName, BorderLayout.NORTH);
        /* Description */
        JLabel recipeDesc = new JLabel(recipe.getDescription());
        recipeName.setFont(new Font("Verdana", Font.PLAIN, 13));
        recipeTextPanel.add(recipeDesc, BorderLayout.CENTER);
        recipeTextPanel.add(new StarRating(recipe), BorderLayout.SOUTH);
        this.add(recipeTextPanel, BorderLayout.CENTER);
        this.setPreferredSize(new Dimension(600, 105));
        this.setMaximumSize(new Dimension(600, 105));
        this.setMinimumSize(new Dimension(600, 105));
    }
}
