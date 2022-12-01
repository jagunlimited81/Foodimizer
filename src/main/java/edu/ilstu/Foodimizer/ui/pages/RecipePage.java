package edu.ilstu.Foodimizer.ui.pages;

import edu.ilstu.Foodimizer.app.StateManager;
import edu.ilstu.Foodimizer.app.db.models.Ingredient;
import edu.ilstu.Foodimizer.app.db.models.Recipe;
import edu.ilstu.Foodimizer.ui.jcomponents.RecipeActionPane;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;



public class RecipePage extends Page {
    public RecipePage() {
        init();
    }

    protected void init() {
    }

    public void setRecipe(Recipe recipe) {
        activeRecipe = recipe;
        contentPane = new JPanel();

        leftColumn = new JPanel();
        recipeImage = new JPanel();
        recipeInfo = new JPanel();
        mealType = new JLabel();
        servingSize = new JLabel();
        cookMethod = new JLabel();
        prepTime = new JLabel();
        cookTime = new JLabel();
        waitTime = new JLabel();

        centerColumn = new JPanel();
        recipeHeader = new JPanel();
        recipeNameHeader = new JLabel();
        recipeDescription = new JTextArea();
        sideBySideIngredientsActionPanel = new JPanel();
        ingredientsListPanel = new JPanel();
        ingredientsList = new JPanel();
        ingredientsBox = new TitledBorder("Ingredients");
        directionsHeader = new JLabel();
        directions = new JPanel();
        directionsTextPane = new JTextPane();

        rightColumn = new JPanel();

        gbc = new GridBagConstraints();

        /* contentPane */
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));

        /* leftColumn */
        {
            //leftColumn.setLayout(new BoxLayout(leftColumn, BoxLayout.Y_AXIS));
            leftColumn.setPreferredSize(new Dimension(200, getHeight()));
            recipeImage.setPreferredSize(new Dimension(200, 200));
            recipeImage.setBackground(Color.gray);
            leftColumn.add(recipeImage);

            recipeInfo.setLayout(new BoxLayout(recipeInfo, BoxLayout.Y_AXIS));

            mealType.setText("Meal Type: " + recipe.getMealType());
            recipeInfo.add(mealType);

            servingSize.setText("Serving Size: " + recipe.getServingSize());
            recipeInfo.add(servingSize);

            cookMethod.setText("Cook Method: " + recipe.getCookMethod());
            recipeInfo.add(cookMethod);

            cookTime.setText("Cook Time: " + recipe.getCookTime());
            recipeInfo.add(cookTime);

            waitTime.setText("Wait Time: " + recipe.getWaitTime() + "min");
            recipeInfo.add(waitTime);

            prepTime.setText("Prep Time: " + recipe.getPrepTime() + " min");
            recipeInfo.add(prepTime);

            //recipeInfo.setBorder(BorderFactory.createLineBorder(Color.red));

            leftColumn.add(recipeInfo);
            //leftColumn.setBorder(BorderFactory.createLineBorder(Color.red));
        }
        contentPane.add(leftColumn);

        /* centerColumn */
        {
            //centerColumn.setLayout(new BoxLayout(centerColumn, BoxLayout.PAGE_AXIS));
            centerColumn.setLayout(new BorderLayout());

            recipeHeader.setLayout(new BorderLayout());
            recipeNameHeader.setText(recipe.getName());
            recipeNameHeader.setFont(new Font("Verdana", Font.PLAIN, 22));
            recipeHeader.add(recipeNameHeader, BorderLayout.NORTH);
            //recipeHeader.setBorder(BorderFactory.createLineBorder(Color.red));
            recipeDescription.setText(recipe.getDescription());
            recipeDescription.setLineWrap(true);
            recipeDescription.setEditable(false);

            recipeHeader.add(recipeDescription, BorderLayout.SOUTH);
            recipeHeader.setMaximumSize(recipeHeader.getMinimumSize());
            centerColumn.add(recipeHeader, BorderLayout.NORTH);

            sideBySideIngredientsActionPanel.setLayout(new BoxLayout(sideBySideIngredientsActionPanel, BoxLayout.LINE_AXIS));

            ingredientsList.setBorder(ingredientsBox);
            ingredientsList.setLayout(new BoxLayout(ingredientsList, BoxLayout.Y_AXIS));
            //JCheckBox[] jt = new JCheckBox("IngredientName");
            for (Ingredient i : recipe.getRecipeIngredients()) {
                JCheckBox jt = new JCheckBox(i.getName());
                ingredientsList.add(jt);
            }

            ingredientsList.setMaximumSize(ingredientsList.getMinimumSize());

            ingredientsListPanel.add(ingredientsList);
            centerColumn.add(ingredientsListPanel, BorderLayout.WEST);

            JPanel space = new JPanel();
            space.setPreferredSize(new Dimension(1, 1));

            centerColumn.add(new RecipeActionPane(), BorderLayout.EAST);

            directions.setLayout(new BoxLayout(directions, BoxLayout.Y_AXIS));
            directionsHeader.setText("Directions:");
            // TODO align header left
            directionsHeader.setFont(new Font("Verdana", Font.PLAIN, 21));


            directionsTextPane.setText(recipe.getDirections());
            directionsTextPane.setEditable(false);
            directions.add(directionsHeader);
            directions.add(directionsTextPane);
            centerColumn.add(directions, BorderLayout.CENTER);
        }
        contentPane.add(centerColumn);
        /* rightColumn */
        {
            rightColumn.setLayout(new BoxLayout(rightColumn, BoxLayout.Y_AXIS));
            rightColumn.setPreferredSize(new Dimension(300, 300));
        }
        contentPane.add(rightColumn);
        this.setLayout(new BorderLayout());
        add(new JScrollPane(contentPane), BorderLayout.CENTER);
    }


    public static RecipePage getInstance() {
        if (instance == null)
            instance = new RecipePage();
        return instance;
    }

    private static RecipePage instance = null;

    public Recipe getActiveRecipe() {
        return activeRecipe;
    }

    public void setActiveRecipe(Recipe activeRecipe) {
        this.activeRecipe = activeRecipe;
        setRecipe(activeRecipe);
    }

    private Recipe activeRecipe;
    private StateManager fcm;
    private JPanel contentPane;
    private JPanel leftColumn;
    private JPanel recipeInfo;
    private JLabel servingSize;
    private JLabel cookMethod;
    private JLabel mealType;
    private JLabel cookTime;
    private JLabel prepTime;
    private JLabel waitTime;
    private JPanel recipeImage;
    private JPanel centerColumn;
    private JLabel recipeNameHeader;
    private JPanel recipeHeader;
    private JTextArea recipeDescription;
    private JPanel sideBySideIngredientsActionPanel;
    private TitledBorder ingredientsBox;
    private JPanel ingredientsListPanel;
    private JPanel ingredientsList;
    private JLabel directionsHeader;
    private JPanel directions;
    private JTextPane directionsTextPane;
    private JPanel rightColumn;
    private GridBagConstraints gbc;


}
