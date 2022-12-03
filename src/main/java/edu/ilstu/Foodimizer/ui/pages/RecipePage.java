package edu.ilstu.Foodimizer.ui.pages;

import edu.ilstu.Foodimizer.app.StateManager;
import edu.ilstu.Foodimizer.app.db.models.Ingredient;
import edu.ilstu.Foodimizer.app.db.models.Profile;
import edu.ilstu.Foodimizer.app.db.models.Recipe;
import edu.ilstu.Foodimizer.ui.jcomponents.RecipeActionPane;
import edu.ilstu.Foodimizer.ui.jcomponents.StarRating;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class RecipePage extends Page {
    private static RecipePage instance = null;
    private Recipe activeRecipe; // Holds the active recipe on the page

    public RecipePage() {
        init();
    }

    public static RecipePage getInstance() {
        if (instance == null)
            instance = new RecipePage();
        return instance;
    }

    protected void init() {
        setRecipe(activeRecipe);
    }

    public void setRecipe(Recipe recipe) {
        if (recipe == null) {
            return;
        }

        activeRecipe = recipe;
        // The content pane is what holds all the panels, then the contentPane is added to this.
        JPanel contentPane = new JPanel();

        /* Left Column */
        // Left column of the contentPane
        JPanel leftColumn = new JPanel();
        //TODO change to image
        JPanel recipeImage = new JPanel();
        JPanel recipeCookInfoPanel = new JPanel();
        JLabel mealType = new JLabel();
        JLabel servingSize = new JLabel();
        JLabel cookMethod = new JLabel();
        JLabel prepTime = new JLabel();
        JLabel cookTime = new JLabel();
        JLabel waitTime = new JLabel();

        /* Center Column */
        // Center column of the contentPane
        JPanel centerColumn = new JPanel();
        // Holds the title and description
        JPanel recipeHeader = new JPanel();
        JLabel recipeNameTitle = new JLabel();
        JTextArea recipeDescription = new JTextArea();
        // ?
        JPanel sideBySideIngredientsActionPanel = new JPanel();
        // TODO refactor
        JPanel ingredientsListPanel = new JPanel();
        JPanel ingredientsList = new JPanel();
        TitledBorder ingredientsBox = new TitledBorder("Ingredients");
        JLabel directionsHeader = new JLabel();
        JPanel directions = new JPanel();
        JTextPane directionsTextPane = new JTextPane();

        /* Right Column */
        // Right column of the contentPane
        JPanel rightColumn = new JPanel();

        GridBagConstraints gbc = new GridBagConstraints();

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
            recipeImage.setAlignmentX(Component.CENTER_ALIGNMENT);
            leftColumn.add(recipeImage);

            recipeCookInfoPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
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
            centerColumn.setLayout(new BorderLayout());

            recipeHeader.setLayout(new BorderLayout());
            recipeNameTitle.setText(recipe.getName());
            recipeNameTitle.setFont(new Font("Verdana", Font.PLAIN, 22));
            recipeHeader.add(recipeNameTitle, BorderLayout.NORTH);
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
            Profile profile = StateManager.getInstance().getActiveProfile();
            for (Ingredient i : recipe.getRecipeIngredients()) {
                JCheckBox jt = new JCheckBox(i.getName());
                if (profile.getPantry().contains(i)) {
                    jt.setSelected(true);
                }
                jt.setEnabled(false);
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
        JScrollPane centerScrollPane = new JScrollPane(centerColumn);
        centerScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        centerScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        centerScrollPane.setPreferredSize(new Dimension(this.getWidth() / 2, this.getHeight()));

        contentPane.add(centerScrollPane, BorderLayout.CENTER);
        /* rightColumn */
        {
            rightColumn.setLayout(new BoxLayout(rightColumn, BoxLayout.Y_AXIS));
            rightColumn.setPreferredSize(new Dimension(250, getHeight()));
            StarRating sr = new StarRating();
            RecipeActionPane rap = new RecipeActionPane();
            rightColumn.add(sr);
            rightColumn.add(rap);
        }
        contentPane.add(rightColumn, BorderLayout.EAST);
        this.add(contentPane);
    }

    public Recipe getActiveRecipe() {
        return activeRecipe;
    }

    public void setActiveRecipe(Recipe activeRecipe) {
        this.activeRecipe = activeRecipe;
        setRecipe(activeRecipe);
    }


}
