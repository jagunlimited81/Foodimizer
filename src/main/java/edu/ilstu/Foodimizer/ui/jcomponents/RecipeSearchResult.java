package edu.ilstu.Foodimizer.ui.jcomponents;

import edu.ilstu.Foodimizer.app.StateManager;
import edu.ilstu.Foodimizer.app.db.models.Ingredient;
import edu.ilstu.Foodimizer.app.db.models.Recipe;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class RecipeSearchResult extends JPanel {

    private final Recipe recipe;

    public RecipeSearchResult(Recipe recipe) {
        this.recipe = recipe;
        init();
    }

    private void init() {

        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createLineBorder(Color.gray));

        /* Left */
        /* Image */
        JPanel recipeImage = new JPanel();
        recipeImage.setBackground(Color.gray);
        recipeImage.setMaximumSize(new Dimension(100, 100));
        recipeImage.setPreferredSize(new Dimension(100, 100));
        recipeImage.setMinimumSize(new Dimension(100, 100));
        this.add(recipeImage, BorderLayout.LINE_START);

        /* Center */
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
        this.add(recipeTextPanel, BorderLayout.CENTER);

        /* Right */
        JPanel matchPanel = new JPanel();
        GroupLayout matchLayout = new GroupLayout(matchPanel);
        matchPanel.setLayout(matchLayout);
        JLabel matchPercent = new JLabel("Match Percentage");
        JLabel recipeCountIngredients = new JLabel("<html>You have " + getCountMatching(new ArrayList<>(recipe.getRecipeIngredients()), new ArrayList<>(StateManager.getInstance().getActiveProfile().getPantry())) + " ingredients<br/>out of " + recipe.getRecipeIngredients().size() + " in the recipe.</html>");
        JLabel rating = new JLabel("Rating");
        StarRating sr = new StarRating(recipe);

        matchLayout.setHorizontalGroup(
                matchLayout.createSequentialGroup()
                        .addGroup(matchLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(matchPercent)
                                .addComponent(recipeCountIngredients)
                                .addComponent(rating)
                                .addComponent(sr))

        );
        matchLayout.setVerticalGroup(
                matchLayout.createSequentialGroup()
                        .addComponent(matchPercent)
                        .addComponent(recipeCountIngredients)
                        .addComponent(rating)
                        .addComponent(sr)
        );
        this.add(matchPanel, BorderLayout.LINE_END);
        this.setPreferredSize(new Dimension(600, 105));
        this.setMaximumSize(new Dimension(600, 105));
        this.setMinimumSize(new Dimension(600, 105));
    }

    private int getCountMatching(ArrayList<Ingredient> l1, ArrayList<Ingredient> l2) {
        int count = 0;
        for (Ingredient i : l1) {
            if (l2.contains(i)) {
                count++;
            }
        }
        return count;
    }
}
