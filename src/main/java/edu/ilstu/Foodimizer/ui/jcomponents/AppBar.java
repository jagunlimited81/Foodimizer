package edu.ilstu.Foodimizer.ui.jcomponents;

import edu.ilstu.Foodimizer.ui.MainWindowContentManager;
import edu.ilstu.Foodimizer.app.db.models.Profile;

import javax.swing.*;
import java.awt.*;

public class AppBar extends JPanel {
    public AppBar() {
        init();
    }

    private void init() {
        contentManager = MainWindowContentManager.getInstance();
        titlePanel = new JPanel();
        title = new JLabel();
        profileCornerMenu = new ProfileCornerMenu();
        /* menubar */
        menuBar1 = new JMenuBar();
        /* FindRecipesMenu */
        findRecipesMenu = new JMenu();
        findRecipesByNameItem = new JMenuItem();
        findRecipesByIngredientsItem = new JMenuItem();
        findRecipesByAZItem = new JMenuItem();
        /* my pantry */
        myPantry = new JMenu();
        myPantryGoToPantry = new JMenuItem();
        /* my Shopping list */
        myShoppingList = new JMenu();
        myShoppingListGoTo = new JMenuItem();
        /* profiles */
        profiles = new JMenu();
        profilesGoTo = new JMenuItem();
        createProfileGoTo = new JMenuItem();
        editProfileGoTo = new JMenuItem();
        /* Debug */
        debug = new JMenu();
        databaseDebugGoTo = new JMenuItem();

        description = new JLabel();
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

                findRecipesByAZItem.setText("A to Z");
                findRecipesByAZItem.setMnemonic('z');
                findRecipesByAZItem.addActionListener(e -> goToPageActionPerformed("FindRecipesByAZ"));
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
            /* My Shopping List */
            {
                myShoppingList.setText("My Shopping List");

                myShoppingListGoTo.setText("Go To Shopping List");
                myShoppingListGoTo.addActionListener(e -> goToPageActionPerformed("ShoppingList"));
                myShoppingList.add(myShoppingListGoTo);
            }
            menuBar1.add(myShoppingList);
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

    /* Declare variables here */
    private MainWindowContentManager contentManager;
    /* MenuBar */
    private JMenuBar menuBar1;

    /* FindRecipesMenu */
    private JMenu findRecipesMenu;
    private JMenuItem findRecipesByNameItem;
    private JMenuItem findRecipesByIngredientsItem;
    private JMenuItem findRecipesByAZItem;
    /* My Pantry */
    private JMenu myPantry;
    private JMenuItem myPantryGoToPantry;
    private JMenu myShoppingList;
    private JMenuItem myShoppingListGoTo;
    /* Profiles */
    private JMenu profiles;
    private JMenuItem profilesGoTo;
    private JMenuItem createProfileGoTo;
    private JMenuItem editProfileGoTo;
    /* Debug/Test */
    private JMenu debug;
    private JMenuItem databaseDebugGoTo;


    private JLabel title;
    private JPanel titlePanel;
    private JLabel description;
    private ProfileCornerMenu profileCornerMenu;
}
