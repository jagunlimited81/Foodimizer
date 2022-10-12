package edu.ilstu.Foodimizer.ui.jcomponents;

import edu.ilstu.Foodimizer.ui.MainWindowContentManager;

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
        /* my grocery list */
        myGroceryList = new JMenu();
        myGroceryListGoTo = new JMenuItem();
        /* profiles */
        profiles = new JMenu();
        profilesGoTo = new JMenuItem();

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
            }
            menuBar1.add(profiles);
        }


        this.add(menuBar1, BorderLayout.SOUTH);

    }

    private void goToPageActionPerformed(String page) {
        contentManager.goToPage(page);
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
    private JMenu myGroceryList;
    private JMenuItem myGroceryListGoTo;
    private JMenu profiles;
    private JMenuItem profilesGoTo;


    private JLabel title;
    private JPanel titlePanel;
    private JLabel description;

}
