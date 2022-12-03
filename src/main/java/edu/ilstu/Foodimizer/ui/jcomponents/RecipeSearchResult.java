package edu.ilstu.Foodimizer.ui.jcomponents;

import edu.ilstu.Foodimizer.app.StateManager;
import edu.ilstu.Foodimizer.app.db.models.Ingredient;
import edu.ilstu.Foodimizer.app.db.models.Recipe;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
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
        matchPercent.setFont(new Font(matchPercent.getFont().getName(), Font.BOLD, matchPercent.getFont().getSize()));
        double numMatch = getCountMatching(new ArrayList<>(recipe.getRecipeIngredients()), new ArrayList<>(StateManager.getInstance().getActiveProfile().getPantry()));
        double numTotal = recipe.getRecipeIngredients().size();
        JLabel percent = new JLabel(new DecimalFormat("#").format((numMatch / numTotal) * 100) + "%");
        JLabel recipeCountIngredients = new JLabel("<html>You have " + (int) numMatch + " ingredients<br/>out of " + (int) numTotal + " in the recipe.</html>");
        JLabel rating = new JLabel("Rating");
        rating.setFont(new Font(rating.getFont().getName(), Font.BOLD, rating.getFont().getSize()));
        StarRating sr = new StarRating(recipe);

        matchLayout.setHorizontalGroup(
                matchLayout.createSequentialGroup()
                        .addGroup(matchLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(matchPercent)
                                .addComponent(percent)
                                .addComponent(recipeCountIngredients)
                                .addComponent(rating)
                                .addComponent(sr))

        );
        matchLayout.setVerticalGroup(
                matchLayout.createSequentialGroup()
                        .addComponent(matchPercent)
                        .addComponent(percent)
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
