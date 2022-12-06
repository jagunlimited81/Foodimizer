package edu.ilstu.Foodimizer.Controller;

import edu.ilstu.Foodimizer.app.db.ServicesEntityManager;
import edu.ilstu.Foodimizer.app.db.models.Ingredient;
import edu.ilstu.Foodimizer.app.db.models.Recipe;
import edu.ilstu.Foodimizer.app.db.service.IngredientService;
import edu.ilstu.Foodimizer.app.db.service.RecipeService;
import org.junit.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class IngredientSearchControllerTest {

    private static RecipeService rs;
    private static IngredientService is;

    //Using before instead of beforeclass because different cases rely on different states of the database
    @Before
    public void setUp() throws Exception {
        ServicesEntityManager sem = ServicesEntityManager.getInstance();
        sem.init("FoodimizerDB-TEST");

        rs = new RecipeService();
        is = new IngredientService();
    }

    @After
    public void tearDown() throws Exception {
        List<Recipe> recipesCreatedDuringTesting = rs.getAll();
        for (Recipe r : recipesCreatedDuringTesting) {
            rs.delete(r);
        }
        List<Ingredient> ingredientsCreatedDuringTesting = is.getAll();
        for (Ingredient i : ingredientsCreatedDuringTesting) {
            is.delete(i);
        }
    }

    @Test
    public void testGetRecipesWithIngredientsMatchesFound() {
        //create recipes and ingredients
        String recipeName = "testRecipe";
        String[] ingredientsToAdd = new String[]{"egg", "peanut butter", "rocks"};
        Recipe rec = new Recipe();
        for (String i : ingredientsToAdd) {
            Ingredient existingIngredient = is.getFromName(i);
            if (existingIngredient == null) {
                Ingredient newIngredient = new Ingredient();
                newIngredient.setName(i);
                is.save(newIngredient);
                rec.addIngredient(newIngredient);
            } else {
                rec.addIngredient(existingIngredient);
            }
        }
        rs.save(rec);


        IngredientSearchController testISC = new IngredientSearchController(new JTextField());
        ArrayList<Recipe> foundRecipes = testISC.getRecipesWithIngredients(new String[]{"egg"});

        Assert.assertTrue(foundRecipes.contains(rec));
    }

    @Test
    public void testGetRecipesWithIngredientsPartialMatchFound() {

        //create recipes and ingredients
        String recipeName = "testRecipe";
        String[] ingredientsToAdd = new String[]{"egg", "peanut butter", "bread"};
        Recipe rec = new Recipe();
        for (String i : ingredientsToAdd) {
            Ingredient existingIngredient = is.getFromName(i);
            if (existingIngredient == null) {
                Ingredient newIngredient = new Ingredient();
                newIngredient.setName(i);
                is.save(newIngredient);
                rec.addIngredient(newIngredient);
            } else {
                rec.addIngredient(existingIngredient);
            }
        }
        rs.save(rec);


        IngredientSearchController testISC = new IngredientSearchController(new JTextField());
        ArrayList<Recipe> foundRecipes = testISC.getRecipesWithIngredients(new String[]{"egg", "sugar"});

        Assert.assertTrue(foundRecipes.contains(rec));
    }

    @Test
    public void testGetRecipesWithIngredientsNoMatchesFound() {

        //create recipes and ingredients
        String recipeName = "testRecipe";
        String[] ingredientsToAdd = new String[]{"egg", "peanut butter", "bread"};
        Recipe rec = new Recipe();
        for (String i : ingredientsToAdd) {
            Ingredient existingIngredient = is.getFromName(i);
            if (existingIngredient == null) {
                Ingredient newIngredient = new Ingredient();
                newIngredient.setName(i);
                is.save(newIngredient);
                rec.addIngredient(newIngredient);
            } else {
                rec.addIngredient(existingIngredient);
            }
        }
        rs.save(rec);


        IngredientSearchController testISC = new IngredientSearchController(new JTextField());
        ArrayList<Recipe> foundRecipes = testISC.getRecipesWithIngredients(new String[]{"sugar"});

        Assert.assertFalse(foundRecipes.contains(rec));
    }

}