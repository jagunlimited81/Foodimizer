package edu.ilstu.Foodimizer.Controller;

import edu.ilstu.Foodimizer.app.db.ServicesEntityManager;
import org.junit.*;

import javax.swing.*;

import static org.junit.Assert.*;

public class RecipeSearchControllerTest {

    private static RecipeSearchController rsc = null;
    private JTextField recipeInPantry = new JTextField("Bacon Cheeseburger");
    private JTextField recipeNotInPantry = new JTextField("Cheeseburger");
    private JButton jButton;

    @BeforeClass
    public static void setUp() throws Exception {
        ServicesEntityManager sem = ServicesEntityManager.getInstance();
        sem.init("FoodimizerDB-TEST");
    }

    @Test
    public void checkRecipeInPantry() {
        rsc = new RecipeSearchController(recipeInPantry, jButton);
        String recipeName = "Bacon Cheeseburger";
        boolean result = rsc.checkRecipe(recipeName);

        assertTrue(result);
    }

    @Test
    public void checkRecipeNotInPantry() {
        rsc = new RecipeSearchController(recipeNotInPantry, jButton);
        String recipeName = "Tequila Shot";
        boolean result = rsc.checkRecipe(recipeName);

        assertTrue(result);
    }

    @Test
    public void searchRecipeByName() {

    }
    @AfterClass
    public static void tearDown() throws Exception {
        rsc = null;
    }

}
