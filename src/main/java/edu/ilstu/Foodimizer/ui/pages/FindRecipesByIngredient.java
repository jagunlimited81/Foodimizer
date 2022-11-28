package edu.ilstu.Foodimizer.ui.pages;

import edu.ilstu.Foodimizer.app.db.models.Ingredient;
import edu.ilstu.Foodimizer.app.db.models.Recipe;
import edu.ilstu.Foodimizer.app.db.service.RecipeService;
import edu.ilstu.Foodimizer.ui.MainWindowContentManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Set;

// Search for Recipe that have any ingredients in the pantry
public class FindRecipesByIngredient extends JPanel {
    public FindRecipesByIngredient() {
        init();
    }

    private void init() {
        List<Recipe> recipes = new RecipeService().getAll();
        setBackground(Color.white);

        contentPane = new JPanel();
        contentPane.setBackground(Color.white);
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        contentPane.setPreferredSize(new Dimension(1000,1000));
        contentPane.add(Box.createRigidArea(new Dimension(50,50)));

        /* Logo */
        {
            logo_panel = new JPanel();
            logo_panel.setPreferredSize(new Dimension(300,300));
            logo_panel.setBackground(Color.white);

            foodimizer_logo = new ImageIcon("logo.png");
            logo = new JLabel(foodimizer_logo);

            logo_panel.add(logo);
        }
        contentPane.add(logo_panel);

        /* Search Pane */
        {
            search_panel = new JPanel();
            search_panel.setPreferredSize(new Dimension(500,500));
            search_panel.setBackground(Color.white);

            searchText = new JTextField(35);
            searchText.setFont(new Font("Helvetica Neue", Font.PLAIN, 20));

            searchPrompt = new JLabel("Search Ingredient: ");
            searchPrompt.setFont(new Font("Helvetica Neue", Font.PLAIN, 20));

            searchButton = new JButton("Search");
            searchButton.setFont(new Font("Helvetica Neue", Font.PLAIN, 20));

            search_panel.add(searchPrompt, BorderLayout.WEST);
            search_panel.add(searchText,BorderLayout.CENTER);
            search_panel.add(searchButton, BorderLayout.EAST);
        }
        contentPane.add(search_panel);

        add(contentPane);

        searchButton.addActionListener(e -> {
            String ingredientInputName = searchText.getText();
            boolean notFoundMessage = true;
            Ingredient ingredientInRecipe = new Ingredient();

            // Go through all of the recipes and check if any recipe has the ingredients
            for (Recipe recipe : recipes) {
                Set<Ingredient> listOfIngredient = recipe.getRecipeIngredients();
                for (Ingredient ingredient : listOfIngredient) {
                    if (ingredient.getName().equals(ingredientInputName)) {
                        ingredientInRecipe = ingredient;
                        notFoundMessage = false;
                    }
                }
            }

            // If the ingredient is found in a recipe,
            // recipeThatContainInputIngredient will contain the list of recipe
            Set<Recipe> recipeThatContainInputIngredient = ingredientInRecipe.getRecipesThatContainThisIngredient();
            StringBuilder text_recipe = new StringBuilder("Below are the list of recipe for " + ingredientInputName + ": \n");
            for (Recipe r: recipeThatContainInputIngredient) {
                text_recipe.append(r.getName()).append("\n");
            }
            text_recipe.append("Enter the recipe you want to go to: ");

            if (notFoundMessage)
                JOptionPane.showMessageDialog(new JFrame(), "No Ingredient Found In Pantry", "Dialog",
                        JOptionPane.ERROR_MESSAGE);
            else {
                String recipe_name = JOptionPane.showInputDialog(new JFrame(),
                        text_recipe.toString(), "Recipe List", JOptionPane.INFORMATION_MESSAGE);
                boolean notFoundMessage2 = true;
                for (Recipe recipe : recipes) {
                    if (recipe_name.equals(recipe.getName())) {
                        buttonPressed(recipe);
                        notFoundMessage2 = false;
                    }
                }
                if (notFoundMessage2)
                    JOptionPane.showMessageDialog(new JFrame(), "No Recipe Found",
                            "Dialog", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
    private void buttonPressed(Recipe r) {
        contentManager = MainWindowContentManager.getInstance();
        RecipePage.getInstance().setActiveRecipe(r);
        contentManager.goToPage("RecipePage");
    }

    MainWindowContentManager contentManager;
    JPanel contentPane;
    JPanel logo_panel;
    JPanel search_panel;

    JLabel logo;
    JLabel searchPrompt;

    JTextField searchText;
    JButton searchButton;
    ImageIcon foodimizer_logo;
}
