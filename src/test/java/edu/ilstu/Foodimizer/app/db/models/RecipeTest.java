package edu.ilstu.Foodimizer.app.db.models;

import edu.ilstu.Foodimizer.app.db.ServicesEntityManager;
import edu.ilstu.Foodimizer.app.db.service.ProfileService;
import edu.ilstu.Foodimizer.app.db.service.RecipeService;
import org.junit.*;

import java.util.List;

import static org.junit.Assert.*;

public class RecipeTest {

    private static Recipe rec;
    private static RecipeService rs;


    @BeforeClass
    public static void setUp() throws Exception {
        ServicesEntityManager sem = ServicesEntityManager.getInstance();
        sem.init("FoodimizerDB-TEST");

        rec = new Recipe();
        rec.setName("TestProfile");
        rs = new RecipeService();
    }

    @AfterClass
    public static void tearDown() throws Exception {
        List<Recipe> createdDuringTesting = rs.getAll();
        for (Recipe r : createdDuringTesting) {
            rs.delete(r);
        }
    }

    @Test
    public void addIngredient() {
        Ingredient ing = new Ingredient();

        rec.addIngredient(ing);

        Assert.assertTrue(rec.getRecipeIngredients().contains(ing));
        Assert.assertTrue(ing.getRecipesThatContainThisIngredient().contains(rec));
    }

    @Test
    public void removeIngredient() {
        Ingredient ing = new Ingredient();

        rec.addIngredient(ing);
        rec.removeIngredient(ing);

        Assert.assertFalse(rec.getRecipeIngredients().contains(ing));
        Assert.assertFalse(ing.getRecipesThatContainThisIngredient().contains(rec));
    }
}