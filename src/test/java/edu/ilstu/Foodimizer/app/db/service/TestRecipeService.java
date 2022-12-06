package edu.ilstu.Foodimizer.app.db.service;

import edu.ilstu.Foodimizer.app.db.ServicesEntityManager;
import edu.ilstu.Foodimizer.app.db.models.Ingredient;
import edu.ilstu.Foodimizer.app.db.models.Recipe;
import jakarta.persistence.PersistenceException;
import org.junit.*;

import java.util.ArrayList;
import java.util.List;

public class TestRecipeService {

    private RecipeService rs;
    IngredientService is;

    @BeforeClass
    public static void beforeClass()
    {
        ServicesEntityManager sem = ServicesEntityManager.getInstance();
        sem.init("FoodimizerDB-TEST");
    }

    @Before
    public void before() {
        rs = new RecipeService();
        is = new IngredientService();
    }

    @After
    public void after() {
        List<Recipe> createdDuringTesting = rs.getAll();
        for (Recipe r : createdDuringTesting) {
            rs.delete(r);
        }
        List<Ingredient> ingsCreatedDuringTesting = is.getAll();
        for (Ingredient i : ingsCreatedDuringTesting) {
            is.delete(i);
        }
    }


    //getAll
    @Test
    public void testGetAllSuccessful() {
        Recipe rec = new Recipe();
        rec.setName("test1");
        rs.save(rec);

        Recipe rec2 = new Recipe();
        rec2.setName("test3");
        rs.save(rec2);

        Recipe rec3 = new Recipe();
        rec3.setName("test2");
        rs.save(rec3);

        Assert.assertTrue(rs.getAll().size() == 3);
        Assert.assertTrue(rs.getAll().contains(rec));
        Assert.assertTrue(rs.getAll().contains(rec2));
        Assert.assertTrue(rs.getAll().contains(rec3));
    }

    //save
    @Test
    public void testSaveSuccessful() {
        Recipe rec = new Recipe();
        rec.setName("test");

        rs.save(rec);

        Assert.assertTrue(rs.getAll().size() == 1);
        Assert.assertTrue(rs.getAll().get(0).getName().equals("test"));
    }

    //update
    @Test
    public void testUpdateSuccessful() {
        Recipe rec = new Recipe();
        rec.setName("test");
        rs.save(rec);

        rec.setName("test2");
        rs.update(rec, "");

        Assert.assertTrue(rs.getAll().size() == 1);
        Assert.assertTrue(rs.getAll().get(0).getName().equals("test2"));
    }

    //delete
    @Test
    public void testDeleteSuccessful() {
        Recipe rec = new Recipe();
        rec.setName("test");
        rs.save(rec);

        rs.delete(rec);

        Assert.assertTrue(rs.getAll().isEmpty());
    }

    @Test
    public void testSearchByIngredientsMatchFound() {
        Recipe rec = new Recipe();

        Ingredient ing1 = new Ingredient();
        ing1.setName("ing1");
        is.save(ing1);
        rec.addIngredient(ing1);

        Ingredient ing2 = new Ingredient();
        ing2.setName("ing2");
        is.save(ing2);
        rec.addIngredient(ing2);

        Ingredient ing3 = new Ingredient();
        ing3.setName("ing3");
        is.save(ing3);
        rec.addIngredient(ing3);

        rs.save(rec);

        List<Ingredient> ingredients = new ArrayList<Ingredient>();
        ingredients.add(ing1);
        ingredients.add(ing2);
        ingredients.add(ing3);

        List<Recipe> results = rs.searchByIngredients(ingredients);

        Assert.assertTrue(results.size() == 1);
        Assert.assertEquals(rec.getRecipeId(), results.get(0).getRecipeId());
    }

    @Test
    public void testSearchByIngredientsNoMatchFound() {
        Recipe rec = new Recipe();

        Ingredient ing1 = new Ingredient();
        ing1.setName("ing1");
        is.save(ing1);

        Ingredient ing2 = new Ingredient();
        ing2.setName("ing2");
        is.save(ing2);

        rs.save(rec);

        List<Ingredient> ingredients = new ArrayList<Ingredient>();
        ingredients.add(ing1);
        ingredients.add(ing2);

        List<Recipe> results = rs.searchByIngredients(ingredients);

        Assert.assertTrue(results.size() == 0);
    }
}
