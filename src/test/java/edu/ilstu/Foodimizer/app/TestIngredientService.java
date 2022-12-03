package edu.ilstu.Foodimizer.app;


import edu.ilstu.Foodimizer.app.db.ServicesEntityManager;
import edu.ilstu.Foodimizer.app.db.models.Ingredient;
import edu.ilstu.Foodimizer.app.db.service.IngredientService;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class TestIngredientService {

    @BeforeClass
    public static void setUp() {
        ServicesEntityManager sem = ServicesEntityManager.getInstance();
        sem.init("FoodimizerDB-TEST");
    }

    @AfterClass
    public static void tearDown() {

    }

    @Test
    public void TestInsertOne() {
        IngredientService is = new IngredientService();
        Ingredient ingredient = new Ingredient();
        ingredient.setName("spinach");
        is.save(ingredient);
    }

    @Test
    @Ignore
    public void TestRetrieveOne() {
        IngredientService is = new IngredientService();
        Ingredient ingredient = is.getFromName("spinach");

    }

    @Test
    @Ignore
    public void TestUpdateOne() {
        IngredientService is = new IngredientService();
        Ingredient ingredient = is.getFromName("spinach");
        ingredient.setName("dogs");
        is.save(ingredient);
    }

    @Test
    public void TestDeleteOne() {
        IngredientService is = new IngredientService();
        Ingredient ingredient = is.getFromName("spinach");
        is.delete(ingredient);
        // assert is empty
    }
}
