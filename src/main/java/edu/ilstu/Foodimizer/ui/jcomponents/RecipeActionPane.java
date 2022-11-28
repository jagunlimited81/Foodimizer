package edu.ilstu.Foodimizer.ui.jcomponents;

import edu.ilstu.Foodimizer.app.StateManager;
import edu.ilstu.Foodimizer.app.db.models.Profile;
import edu.ilstu.Foodimizer.app.db.models.Recipe;
import edu.ilstu.Foodimizer.app.db.service.ProfileService;
import edu.ilstu.Foodimizer.app.db.service.RecipeService;
import edu.ilstu.Foodimizer.lib.PDFFormatter;
import edu.ilstu.Foodimizer.lib.SysDialogPrinter;
import edu.ilstu.Foodimizer.ui.pages.RecipePage;

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
        addOrRemoveFromFavoritesButton = new JButton();
        printThisRecipeButton = new JButton();

        /* this */
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(BorderFactory.createLineBorder(Color.lightGray));


        addAllToGroceryListButton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        addAllToGroceryListButton.addActionListener(e -> addAllToGroceryList());
        addAllToGroceryListButton.setText("Add all to My Grocery List");

        addMissingToGroceryListButton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        addMissingToGroceryListButton.addActionListener(e -> addMissingToGroceryList());
        addMissingToGroceryListButton.setText("Add missing to My Grocery List");

        addOrRemoveFromFavoritesButton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        System.out.println("here");
//        if (StateManager.getInstance().getActiveProfile().getFavoriteRecipes().contains(RecipePage.getInstance().getActiveRecipe())) {
//            addOrRemoveFromFavoritesButton.addActionListener(e -> removeFromFavorites());
//            addOrRemoveFromFavoritesButton.setText("Remove from favorites list");
//        } else {
//            addOrRemoveFromFavoritesButton.addActionListener(e -> addToFavorites());
//            addOrRemoveFromFavoritesButton.setText("Add to favorites list");
//        }
        printThisRecipeButton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        printThisRecipeButton.addActionListener(e -> printThisRecipe());
        printThisRecipeButton.setMinimumSize(addOrRemoveFromFavoritesButton.getMaximumSize());
        printThisRecipeButton.setText("Print this recipe");

        contentPanel.add(addAllToGroceryListButton);
        contentPanel.add(addMissingToGroceryListButton);
        contentPanel.add(addOrRemoveFromFavoritesButton);
        contentPanel.add(printThisRecipeButton);

        contentPanel.setMaximumSize(contentPanel.getMinimumSize());
        this.add(contentPanel);

    }


    private void printThisRecipe() {
        SysDialogPrinter.print("Recipe", new PDFFormatter(new Recipe()));
    }

    private void addToFavorites() {
        Recipe activeRecipe = RecipePage.getInstance().getActiveRecipe();
        ProfileService profileService = new ProfileService();
        RecipeService recipeService = new RecipeService();
        StateManager sm = StateManager.getInstance();

        Profile profile = sm.getActiveProfile();

        profile.addRecipeToFavorites(activeRecipe);
        profileService.update(sm.getActiveProfile(), "");
        recipeService.update(activeRecipe, "");
        addOrRemoveFromFavoritesButton.setText("Remove from favorites list");
        addOrRemoveFromFavoritesButton.removeActionListener(addOrRemoveFromFavoritesButton.getActionListeners()[0]);
        addOrRemoveFromFavoritesButton.addActionListener(e -> removeFromFavorites());
    }

    private void removeFromFavorites() {
        Recipe activeRecipe = RecipePage.getInstance().getActiveRecipe();
        ProfileService profileService = new ProfileService();
        RecipeService recipeService = new RecipeService();
        StateManager sm = StateManager.getInstance();

        Profile profile = sm.getActiveProfile();

        profile.removeRecipeFromFavorites(activeRecipe);
        profileService.update(sm.getActiveProfile(), "");
        recipeService.update(activeRecipe, "");
        addOrRemoveFromFavoritesButton.setText("Add to favorites list");
        addOrRemoveFromFavoritesButton.removeActionListener(addOrRemoveFromFavoritesButton.getActionListeners()[0]);
        addOrRemoveFromFavoritesButton.addActionListener(e -> addToFavorites());
    }

    private void addMissingToGroceryList() {
    }

    private void addAllToGroceryList() {
    }

    private JPanel contentPanel;
    private JButton addAllToGroceryListButton;
    private JButton addMissingToGroceryListButton;

    private JButton addOrRemoveFromFavoritesButton;
    private JButton printThisRecipeButton;
}
