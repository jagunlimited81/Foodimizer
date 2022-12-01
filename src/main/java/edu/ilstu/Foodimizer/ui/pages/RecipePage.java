package edu.ilstu.Foodimizer.ui.pages;

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
        setRecipe(activeRecipe);
    }

    public void setRecipe(Recipe recipe) {
        if (recipe == null) {
            return;
        }

        activeRecipe = recipe;
        contentPane = new JPanel();

        /* Left Column */
        leftColumn = new JPanel();
        recipeImage = new JPanel();
        recipeCookInfoPanel = new JPanel();
        mealType = new JLabel();
        servingSize = new JLabel();
        cookMethod = new JLabel();
        prepTime = new JLabel();
        cookTime = new JLabel();
        waitTime = new JLabel();

        /* Center Column */
        centerColumn = new JPanel();
        recipeHeader = new JPanel();
        recipeNameTitle = new JLabel();
        recipeDescription = new JTextArea();
        sideBySideIngredientsActionPanel = new JPanel();
        ingredientsListPanel = new JPanel();
        ingredientsList = new JPanel();
        ingredientsBox = new TitledBorder("Ingredients");
        directionsHeader = new JLabel();
        directions = new JPanel();
        directionsTextPane = new JTextPane();

        /* Right Column */
        rightColumn = new JPanel();

        gbc = new GridBagConstraints();

        /* contentPane */
        //contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
        contentPane.setLayout(new BorderLayout());

        /* leftColumn */
        {
            leftColumn.setLayout(new BoxLayout(leftColumn, BoxLayout.Y_AXIS));
            leftColumn.setPreferredSize(new Dimension(250, getHeight()));

            recipeImage.setPreferredSize(new Dimension(200, 200));
            recipeImage.setMaximumSize(new Dimension(200, 200));
            recipeImage.setMinimumSize(new Dimension(200, 200));
            recipeImage.setBackground(Color.gray);
            recipeImage.setBorder(BorderFactory.createLineBorder(Color.red));
            recipeImage.setAlignmentX(Component.CENTER_ALIGNMENT);
            leftColumn.add(recipeImage);

            recipeCookInfoPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
            recipeCookInfoPanel.setBorder(BorderFactory.createLineBorder(Color.red));
            GroupLayout rcipLayout = new GroupLayout(recipeCookInfoPanel);
            recipeCookInfoPanel.setLayout(rcipLayout);
            rcipLayout.setAutoCreateGaps(true);
            rcipLayout.setAutoCreateContainerGaps(true);

            mealType.setText("Meal Type: ");
            servingSize.setText("Serving Size: ");
            cookMethod.setText("Cook Method: ");
            cookTime.setText("Cook Time: ");
            waitTime.setText("Wait Time: ");
            prepTime.setText("Prep Time: ");

            JLabel mealTypeData = new JLabel(recipe.getMealType());
            JLabel servingSizeData = new JLabel(recipe.getCookMethod());
            JLabel cookMethodData = new JLabel(recipe.getCookTime() + " min");
            JLabel cookTimeData = new JLabel(recipe.getMealType());
            JLabel waitTimeData = new JLabel(recipe.getWaitTime() + " min");
            JLabel prepTimeData = new JLabel(recipe.getPrepTime() + " min");
            rcipLayout.setHorizontalGroup(
                    rcipLayout.createSequentialGroup()
                            .addGroup(rcipLayout.createParallelGroup()
                                    .addComponent(mealType)
                                    .addComponent(servingSize)
                                    .addComponent(cookMethod)
                                    .addComponent(cookTime)
                                    .addComponent(waitTime)
                                    .addComponent(prepTime))
                            .addGroup(rcipLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                    .addComponent(mealTypeData)
                                    .addComponent(servingSizeData)
                                    .addComponent(cookMethodData)
                                    .addComponent(cookTimeData)
                                    .addComponent(waitTimeData)
                                    .addComponent(prepTimeData))
            );
            rcipLayout.setVerticalGroup(
                    rcipLayout.createSequentialGroup()
                            .addGroup(rcipLayout.createParallelGroup()
                                    .addComponent(mealType)
                                    .addComponent(mealTypeData))
                            .addGroup(rcipLayout.createParallelGroup()
                                    .addComponent(servingSize)
                                    .addComponent(servingSizeData))
                            .addGroup(rcipLayout.createParallelGroup()
                                    .addComponent(cookMethod)
                                    .addComponent(cookMethodData))
                            .addGroup(rcipLayout.createParallelGroup()
                                    .addComponent(cookTime)
                                    .addComponent(cookTimeData))
                            .addGroup(rcipLayout.createParallelGroup()
                                    .addComponent(waitTime)
                                    .addComponent(waitTimeData))
                            .addGroup(rcipLayout.createParallelGroup()
                                    .addComponent(prepTime)
                                    .addComponent(prepTimeData))
            );
            leftColumn.add(recipeCookInfoPanel);
        }
        contentPane.add(leftColumn, BorderLayout.WEST);

        /* centerColumn */
        {
            //centerColumn.setLayout(new BoxLayout(centerColumn, BoxLayout.PAGE_AXIS));
            centerColumn.setLayout(new BorderLayout());

            recipeHeader.setLayout(new BorderLayout());
            recipeNameTitle.setText(recipe.getName());
            recipeNameTitle.setFont(new Font("Verdana", Font.PLAIN, 22));
            recipeHeader.add(recipeNameTitle, BorderLayout.NORTH);
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
        contentPane.add(new JScrollPane(centerColumn), BorderLayout.CENTER);
        /* rightColumn */
        {
            rightColumn.add(new RecipeActionPane(), BorderLayout.EAST);
        }
        contentPane.add(rightColumn, BorderLayout.EAST);
        this.add(contentPane);
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

    private Recipe activeRecipe; // Holds the active recipe on the page
    private JPanel contentPane; // The content pane is what holds all of the jpanels, then the contentPane is added to this.
    private JPanel leftColumn; // Left column of the contentPane
    private JPanel recipeImage; //TODO change to image
    private JPanel recipeCookInfoPanel;
    private JLabel servingSize;
    private JLabel cookMethod;
    private JLabel mealType;
    private JLabel cookTime;
    private JLabel prepTime;
    private JLabel waitTime;

    private JPanel centerColumn; // Center column of the contentPane
    private JPanel recipeHeader; // Holds the title and description
    private JLabel recipeNameTitle;
    private JTextArea recipeDescription;
    private JPanel sideBySideIngredientsActionPanel; // ?
    private TitledBorder ingredientsBox;
    private JPanel ingredientsListPanel; // TODO refactor
    private JPanel ingredientsList;
    private JLabel directionsHeader;
    private JPanel directions;
    private JTextPane directionsTextPane;
    private JPanel rightColumn; // Right column of the contentPane
    private GridBagConstraints gbc;


}
