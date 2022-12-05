package edu.ilstu.Foodimizer.ui.jcomponents;

import edu.ilstu.Foodimizer.app.StateManager;
import edu.ilstu.Foodimizer.app.db.models.Recipe;
import edu.ilstu.Foodimizer.app.db.service.RatingService;
import edu.ilstu.Foodimizer.app.db.service.RecipeService;
import edu.ilstu.Foodimizer.lib.RecipeComparator;
import edu.ilstu.Foodimizer.ui.MainWindowContentManager;
import edu.ilstu.Foodimizer.ui.pages.RecipeSearchResultsPage;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AppBar extends JPanel {
    /* Declare variables here */
    private MainWindowContentManager contentManager;
    private ProfileCornerMenu profileCornerMenu;

    public AppBar() {
        init();
    }

    private void init() {
        contentManager = MainWindowContentManager.getInstance();
        JPanel titlePanel = new JPanel();
        JLabel title = new JLabel();
        profileCornerMenu = new ProfileCornerMenu();
        /* menubar */
        /* MenuBar */
        JMenuBar menuBar1 = new JMenuBar();
        /* FindRecipesMenu */
        JMenu findRecipesMenu = new JMenu();
        JMenuItem findRecipesByNameItem = new JMenuItem();
        JMenuItem findRecipesByIngredientsItem = new JMenuItem();
        JMenuItem findRecipesByIngredientsInPantryItem = new JMenuItem();
        JMenuItem findRecipesByFavoritesItem = new JMenuItem();
        JMenuItem findRecipesByAZItem = new JMenuItem();
        /* My Pantry */
        JMenu myPantry = new JMenu();
        JMenuItem myPantryGoToPantry = new JMenuItem();
        /* my grocery list */
        JMenu myGroceryList = new JMenu();
        JMenuItem myGroceryListGoTo = new JMenuItem();
        myGroceryListGoTo.addActionListener(e -> goToPageActionPerformed("ShoppingList"));
        /* Profiles */
        JMenu profiles = new JMenu();
        JMenuItem profilesGoTo = new JMenuItem();
        JMenuItem createProfileGoTo = new JMenuItem();
        JMenuItem editProfileGoTo = new JMenuItem();
        /* Debug/Test */
        JMenu debug = new JMenu();
        JMenuItem databaseDebugGoTo = new JMenuItem();

        JLabel description = new JLabel();
        /* this */
        this.setLayout(new BorderLayout());

        /* titlePanel*/
        {
            titlePanel.setLayout(new BorderLayout());
            title.setText("Foodimizer");
            title.setFont(new Font("Verdana", Font.PLAIN, 22));
            title.setSize(500, 500);
            titlePanel.add(title, BorderLayout.NORTH);

            description.setText("Make recipes from nothing!");
            titlePanel.add(description, BorderLayout.CENTER);

            /* This container houses the profile icon and menu */

            titlePanel.add(profileCornerMenu, BorderLayout.EAST);
        }
        this.add(titlePanel, BorderLayout.NORTH);

        /* menuBar1 */
        {
            /* Find Recipes */
            {
                findRecipesMenu.setText("Find Recipes");
                findRecipesMenu.setMnemonic('P');

                findRecipesByNameItem.setText("By Name");
                findRecipesByNameItem.setMnemonic('a');
                findRecipesByNameItem.addActionListener(e -> goToPageActionPerformed("FindRecipesByName"));
                findRecipesMenu.add(findRecipesByNameItem);

                findRecipesByIngredientsItem.setText("By Ingredients");
                findRecipesByIngredientsItem.setMnemonic('i');
                findRecipesByIngredientsItem.addActionListener(e -> goToPageActionPerformed("FindRecipesByIngredient"));
                findRecipesMenu.add(findRecipesByIngredientsItem);

                findRecipesByIngredientsInPantryItem.setText("Using ingredients in Pantry");
                findRecipesByIngredientsInPantryItem.setMnemonic('p');
                findRecipesByIngredientsInPantryItem.addActionListener(e -> pantryIngredientsActionPerformed());
                findRecipesMenu.add(findRecipesByIngredientsInPantryItem);

                findRecipesByFavoritesItem.setText("Favorite Recipes");
                findRecipesByFavoritesItem.setMnemonic('f');
                findRecipesByFavoritesItem.addActionListener(e -> favoriteRecipesActionPerformed());
                findRecipesMenu.add(findRecipesByFavoritesItem);

                findRecipesByAZItem.setText("Browse All Recipes");
                findRecipesByAZItem.setMnemonic('z');
                findRecipesByAZItem.addActionListener(e -> viewAllRecipesActionPerformed());
                findRecipesMenu.add(findRecipesByAZItem);

            }
            menuBar1.add(findRecipesMenu);
            /* My Pantry */
            {
                myPantry.setText("My Pantry");

                myPantryGoToPantry.setText("Go To Pantry");
                myPantryGoToPantry.setMnemonic('m');
                myPantryGoToPantry.addActionListener(e -> goToPageActionPerformed("MyPantry"));
                myPantry.add(myPantryGoToPantry);
            }
            menuBar1.add(myPantry);
            /* My Grocery List */
            {
                myGroceryList.setText("My Grocery List");

                myGroceryListGoTo.setText("Go To Grocery List");
                myGroceryList.add(myGroceryListGoTo);
            }
            menuBar1.add(myGroceryList);
            /* Profiles */
            {
                profiles.setText("Profiles");

                profilesGoTo.setText("Go To Profile Selector");
                profilesGoTo.addActionListener(e -> goToPageActionPerformed("ProfileSelector"));
                profiles.add(profilesGoTo);

                createProfileGoTo.setText("Create new Profile");
                createProfileGoTo.addActionListener(e -> goToPageActionPerformed("CreateProfile"));
                profiles.add(createProfileGoTo);

                editProfileGoTo.setText("Edit current Profile");
                editProfileGoTo.addActionListener(e -> goToPageActionPerformed("EditProfile"));
                profiles.add(editProfileGoTo);
            }
            menuBar1.add(profiles);
            /* Debug */
            {
                debug.setText("Debug/Test");

                databaseDebugGoTo.setText("Database Test Page");
                databaseDebugGoTo.addActionListener(e -> goToPageActionPerformed("DatabaseDebugAndTest"));
                debug.add(databaseDebugGoTo);
            }
            menuBar1.add(debug);
        }


        this.add(menuBar1, BorderLayout.SOUTH);

    }

    private void viewAllRecipesActionPerformed() {
        RecipeService rs = new RecipeService();
        ArrayList<Recipe> sorted = new ArrayList<>(rs.getAll());
        sorted.sort(RecipeComparator.getAZComparator());
        RecipeSearchResultsPage.getInstance().setActiveRecipes(sorted);
        MainWindowContentManager.getInstance().goToPage("RecipeSearchResultsPage");
    }

    private void pantryIngredientsActionPerformed() {
        RecipeService rs = new RecipeService();
        rs.searchByIngredients(new ArrayList<>(StateManager.getInstance().getActiveProfile().getPantry()));
        RecipeSearchResultsPage.getInstance().setActiveRecipes(rs.searchByIngredients(new ArrayList<>(StateManager.getInstance().getActiveProfile().getPantry())));
        MainWindowContentManager.getInstance().goToPage("RecipeSearchResultsPage");
    }

    private void favoriteRecipesActionPerformed() {
        ArrayList<Recipe> list = new ArrayList<>(StateManager.getInstance().getActiveProfile().getFavoriteRecipes());
        RatingService rs = new RatingService();
        list.sort(RecipeComparator.getRatingComparator(rs));
        RecipeSearchResultsPage.getInstance().setActiveRecipes(list);
        MainWindowContentManager.getInstance().goToPage("RecipeSearchResultsPage");
    }

    public void refreshContent() {
        this.removeAll();
        init();
        this.revalidate();
    }

    private void goToPageActionPerformed(String page) {
        contentManager.goToPage(page);
    }

    public ProfileCornerMenu getProfileCornerMenu() {
        return profileCornerMenu;
    }
}
