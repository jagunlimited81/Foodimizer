package edu.ilstu.Foodimizer.ui.pages;

import edu.ilstu.Foodimizer.lib.Recipe;
import edu.ilstu.Foodimizer.ui.jcomponents.RecipeActionPane;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.Hashtable;

public class RecipePage extends JPanel {
    public RecipePage() {
        init();
    }

    private void init() {
        Recipe recipe = new Recipe("1234", "Boiled Chicken Heads and Jam", "Mouth watering cookies filled with delicious chocolate chunks and the crackling bite of pecans, these cookies are the perfect accompaniment of nice cup of coffee or just an evening snack.", 1234, 1234, 1234, "Brunch", 4, "1. Preheat the oven to 375 degrees.\n" +
                "\n" +
                "2. Place the butter in the bowl of an electric mixer and beat until creamy.\n" +
                "\n" +
                "3. Add the sugars and beat until light and fluffy.\n" +
                "\n" +
                "4. Add the egg and vanilla and beat well to combine.\n" +
                "\n" +
                "5. Sift together the dry ingredients and add to the batter, mixing well.\n" +
                "\n" +
                "6. Remove bowl from mixer (be sure to scrape the beater) and stir in the nuts and chocolate chunks by hand.\n" +
                "\n" +
                "7. Drop by teaspoons onto greased cookie sheets and bake 8 to 10 minutes until lightly browned.\n" +
                "\n" +
                "8. Cool 5 minutes on sheets then remove cookies with a spatula to racks to cool.\n", new Hashtable<String, Integer>());
        setRecipe(recipe);
    }

    public void setRecipe(Recipe recipe) {
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
            for (int i = 0; i < 10; i++) {
                JCheckBox jt = new JCheckBox("IngredientName");
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
