package edu.ilstu.Foodimizer.ui.jcomponents;

import javax.swing.*;
import java.awt.*;

public class RecipeActionPane extends JPanel {
    public RecipeActionPane() {
        init();
    }

    public void init() {
        contentPanel = new JPanel();
        addAllToGroceryListButton = new JButton();
        addMissingToGroceryListButton = new JButton();
        addToFavoritesButton = new JButton();
        printThisRecipeButton = new JButton();

        /* this */
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(BorderFactory.createLineBorder(Color.lightGray));


        addAllToGroceryListButton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        addAllToGroceryListButton.setText("Add all to My Grocery List");

        addMissingToGroceryListButton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        addMissingToGroceryListButton.setText("Add missing to My Grocery List");

        addToFavoritesButton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        addToFavoritesButton.setText("Add to favorites list");

        printThisRecipeButton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        printThisRecipeButton.setMinimumSize(addToFavoritesButton.getMaximumSize());
        printThisRecipeButton.setText("Print this recipe");

        contentPanel.add(addAllToGroceryListButton);
        contentPanel.add(addMissingToGroceryListButton);
        contentPanel.add(addToFavoritesButton);
        contentPanel.add(printThisRecipeButton);

        contentPanel.setMaximumSize(contentPanel.getMinimumSize());
        this.add(contentPanel);

    }
    private JPanel contentPanel;
    private JButton addAllToGroceryListButton;
    private JButton addMissingToGroceryListButton;

    private JButton addToFavoritesButton;
    private JButton printThisRecipeButton;
}
