package edu.ilstu.Foodimizer.lib;

import edu.ilstu.Foodimizer.app.StateManager;
import edu.ilstu.Foodimizer.app.db.ServicesEntityManager;
import edu.ilstu.Foodimizer.app.db.models.JoinProfileRateRecipe;
import edu.ilstu.Foodimizer.app.db.models.Profile;
import edu.ilstu.Foodimizer.app.db.models.Recipe;
import edu.ilstu.Foodimizer.app.db.service.RatingService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class RecipeComparatorTest {

    private static RecipeComparator rc = null;

    @Before
    public void setUp() throws Exception {
        ServicesEntityManager sem = ServicesEntityManager.getInstance();
        sem.init("FoodimizerDB-TEST");
    }

    @Test
    public void testValidAZComparator() {
        Recipe shrimpTaco = new Recipe();
        shrimpTaco.setName("Shrimp Taco");
        Recipe beefTaco = new Recipe();
        beefTaco.setName("Beef Taco");
        Recipe porkTaco = new Recipe();
        porkTaco.setName("Pork Taco");

        ArrayList<Recipe> listOfRecipe = new ArrayList<>();
        listOfRecipe.add(beefTaco);
        listOfRecipe.add(porkTaco);
        listOfRecipe.add(shrimpTaco);
        listOfRecipe.sort(RecipeComparator.getAZComparator());

        // Beef Taco, Pork Taco, Shrimp Taco
        ArrayList<Recipe> sortedResult = new ArrayList<>();
        sortedResult.add(beefTaco);
        sortedResult.add(porkTaco);
        sortedResult.add(shrimpTaco);

        Object[] listOfRecipeArray = listOfRecipe.toArray();
        Object[] sortedResultArray = sortedResult.toArray();

        assertArrayEquals(listOfRecipeArray, sortedResultArray);
    }

    @Test
    public void testInvalidAZComparator() {
        Recipe shrimpTaco = new Recipe();
        shrimpTaco.setName("Shrimp Taco");
        Recipe beefTaco = new Recipe();
        beefTaco.setName("Beef Taco");
        Recipe porkTaco = new Recipe();
        porkTaco.setName("Pork Taco");

        ArrayList<Recipe> listOfRecipe = new ArrayList<>();
        listOfRecipe.add(beefTaco);
        listOfRecipe.add(porkTaco);
        listOfRecipe.add(shrimpTaco);
        listOfRecipe.sort(rc.getAZComparator());

        //Pork Taco, Shrimp Taco, Beef Taco
        ArrayList<Recipe> sortedResult = new ArrayList<>();
        sortedResult.add(porkTaco);
        sortedResult.add(shrimpTaco);
        sortedResult.add(beefTaco);

        Object[] listOfRecipeArray = listOfRecipe.toArray();
        Object[] sortedResultArray = sortedResult.toArray();

        assertNotEquals(listOfRecipeArray, sortedResultArray);
    }

    @After
    public void tearDown() throws Exception {
        rc = null;
    }
}