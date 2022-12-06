package edu.ilstu.Foodimizer.app.db.service;


import edu.ilstu.Foodimizer.app.db.ServicesEntityManager;
import edu.ilstu.Foodimizer.app.db.models.Ingredient;
import edu.ilstu.Foodimizer.app.db.models.Ingredient;
import edu.ilstu.Foodimizer.app.db.service.IngredientService;
import org.junit.*;

import java.util.List;

public class TestIngredientService {

    private IngredientService is;

    @BeforeClass
    public static void beforeClass()
    {
        ServicesEntityManager sem = ServicesEntityManager.getInstance();
        sem.init("FoodimizerDB-TEST");
    }

    @Before
    public void before() {
        is = new IngredientService();

    }

    @After
    public void after() {
        List<Ingredient> createdDuringTesting = is.getAll();
        for (Ingredient r : createdDuringTesting) {
            is.delete(r);
        }

    }


    //getAll
    @Test
    public void testGetAllSuccessful() {
        Ingredient ing = new Ingredient();
        ing.setName("test1");
        is.save(ing);

        Ingredient ing2 = new Ingredient();
        ing2.setName("test3");
        is.save(ing2);

        Ingredient ing3 = new Ingredient();
        ing3.setName("test2");
        is.save(ing3);

        Assert.assertTrue(is.getAll().size() == 3);
        Assert.assertTrue(is.getAll().contains(ing));
        Assert.assertTrue(is.getAll().contains(ing2));
        Assert.assertTrue(is.getAll().contains(ing3));
    }

    //save
    @Test
    public void testSaveSuccessful() {
        Ingredient ing = new Ingredient();
        ing.setName("test");

        is.save(ing);

        Assert.assertTrue(is.getAll().size() == 1);
        Assert.assertTrue(is.getAll().get(0).getName().equals("test"));
    }

    //update
    @Test
    public void testUpdateSuccessful() {
        Ingredient ing = new Ingredient();
        ing.setName("test");
        is.save(ing);

        ing.setName("test2");
        is.update(ing, "");

        Assert.assertTrue(is.getAll().size() == 1);
        Assert.assertTrue(is.getAll().get(0).getName().equals("test2"));
    }

    //delete
    @Test
    public void testDeleteSuccessful() {
        Ingredient ing = new Ingredient();
        ing.setName("test");
        is.save(ing);

        is.delete(ing);

        Assert.assertTrue(is.getAll().isEmpty());
    }
}
