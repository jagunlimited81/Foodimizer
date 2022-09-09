package edu.ilstu.Foodimizer.ui.pages;

import edu.ilstu.Foodimizer.lib.Recipe;
import edu.ilstu.Foodimizer.ui.MainWindowContentManager;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class RecipePage extends JPanel {
    public RecipePage() {
        init();
    }

    private void init() {
        leftColumn = new JPanel();
        leftGBLayout = new GridBagLayout();

        centerColumn = new JPanel();
        centerGBLayout = new GridBagLayout();
        recipeNameHeader = new JLabel();
        ingredientsBox = new TitledBorder("Ingredients");
        ingredientsList = new JPanel();

        rightColumn = new JPanel();
        rightGBLayout = new GridBagLayout();

        /* this */
        setLayout(new BorderLayout());
        /* leftColumn */
        {
            leftColumn.setLayout(leftGBLayout);
        }
        add(leftColumn, BorderLayout.WEST);
        /* centerColumn */
        {
            centerColumn.setLayout(centerGBLayout);
            recipeNameHeader.setText("TEMPLATE");
            centerColumn.add(recipeNameHeader);

            ingredientsBox.setTitle("Ingredients");
            ingredientsList.setBorder(ingredientsBox);
            centerColumn.add(ingredientsList);
        }
        add(centerColumn, BorderLayout.CENTER);
        /* rightColumn */
        {
            rightColumn.setLayout(rightGBLayout);
        }
        add(rightColumn, BorderLayout.EAST);
    }

    public void setRecipe(Recipe recipe) {
    }

    public static RecipePage getInstance() {
        if (instance == null)
            instance = new RecipePage();
        return instance;
    }

    private static RecipePage instance = null;
    private JPanel leftColumn;
    private GridBagLayout leftGBLayout;
    private JPanel centerColumn;
    private GridBagLayout centerGBLayout;
    private JPanel rightColumn;
    private GridBagLayout rightGBLayout;


    private JLabel recipeNameHeader;
    private TitledBorder ingredientsBox;
    private JPanel ingredientsList;

}
