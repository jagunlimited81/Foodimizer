package edu.ilstu.Foodimizer.Controller;

import edu.ilstu.Foodimizer.app.db.models.Ingredient;
import edu.ilstu.Foodimizer.app.db.models.Recipe;
import edu.ilstu.Foodimizer.app.db.service.IngredientService;
import edu.ilstu.Foodimizer.app.db.service.RatingService;
import edu.ilstu.Foodimizer.lib.RecipeComparator;
import edu.ilstu.Foodimizer.ui.MainWindowContentManager;
import edu.ilstu.Foodimizer.ui.pages.RecipePage;
import edu.ilstu.Foodimizer.ui.pages.RecipeSearchResultsPage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class IngredientSearchController implements ActionListener {

    JTextField searchText;

    public IngredientSearchController(JTextField jTextField) {
        searchText = jTextField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        searchRecipesByIngredients();
    }

    private void searchRecipesByIngredients() {
        String ingredientName = searchText.getText();
        String[] ingredientsAsString = searchText.getText().split(",\\s*");
        ArrayList<Recipe> recipes = getRecipesWithIngredients(ingredientsAsString);
        if (recipes.isEmpty()) {
            System.out.println("no recipes");
        } else {
            RatingService rs = new RatingService();
            recipes.sort(RecipeComparator.getRatingComparator(rs).thenComparing(Recipe::getName));
            RecipeSearchResultsPage.getInstance().setActiveRecipes(recipes);
            MainWindowContentManager.getInstance().goToPage("RecipeSearchResultsPage");
        }
    }

    public ArrayList<Recipe> getRecipesWithIngredients(String[] ingredientsAsString)
    {
        IngredientService is = new IngredientService();

        ArrayList<Recipe> recipesFound = new ArrayList<Recipe>();
        for (String s : ingredientsAsString) {
            Ingredient ing = is.getFromName(s);
            if (ing != null) {
                for (Recipe r : ing.getRecipesThatContainThisIngredient()) {
                    if (!recipesFound.contains(r)) {
                        recipesFound.add(r);
                    }
                }
            }
        }

        return recipesFound;
    }
        /*
        boolean notFoundMessage = true;
        Ingredient ingredientInRecipe = new Ingredient();
        //List<Recipe> recipes = new RecipeService().getAll();

        for (Recipe recipe : recipes) {
            Set<Ingredient> listOfIngredient = recipe.getRecipeIngredients();
            for (Ingredient ingredient : listOfIngredient) {
                if (ingredient.getName().equals(ingredientName)) {
                    ingredientInRecipe = ingredient;
                    notFoundMessage = false;
                }
            }
        }

        // If the ingredient is found in a recipe,
        // recipeThatContainInputIngredient will contain the list of recipe
        Set<Recipe> recipeThatContainInputIngredient = ingredientInRecipe.getRecipesThatContainThisIngredient();
        StringBuilder text_recipe = new StringBuilder("Below are the list of recipe for " + ingredientName + ": \n");
        for (Recipe r : recipeThatContainInputIngredient) {
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

         */


    private void buttonPressed(Recipe r) {
        MainWindowContentManager contentManager = MainWindowContentManager.getInstance();
        RecipePage.getInstance().setActiveRecipe(r);
        contentManager.goToPage("RecipePage");
    }
}
