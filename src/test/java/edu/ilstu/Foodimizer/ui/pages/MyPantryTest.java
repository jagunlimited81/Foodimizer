package edu.ilstu.Foodimizer.ui.pages;

import edu.ilstu.Foodimizer.app.StateManager;
import edu.ilstu.Foodimizer.app.db.ServicesEntityManager;
import edu.ilstu.Foodimizer.app.db.models.Ingredient;
import edu.ilstu.Foodimizer.app.db.models.Profile;
import edu.ilstu.Foodimizer.app.db.service.IngredientService;
import edu.ilstu.Foodimizer.app.db.service.ProfileService;
import org.junit.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MyPantryTest {
    private static Profile prof;
    private static ProfileService ps;
    private static MyPantry myPan;
    private Ingredient ing;
    private Ingredient ing2;
    private Ingredient ing3;
    private Ingredient ing4;
    private Ingredient ing5;

    @BeforeClass
    public static void setupBeforeClass(){
        ServicesEntityManager sem = ServicesEntityManager.getInstance();
        sem.init("FoodimizerDB-TEST");
    }

    @Before
    public void setUp() throws Exception {
        prof = new Profile();
        StateManager.getInstance().setActiveProfile(prof);
        prof.setName("TestProfile");
        ps = new ProfileService();
        ps.save(prof);

        ing = new Ingredient();
        ing.setName("ham");
        ing2 = new Ingredient();
        ing2.setName("beef");
        ing3 = new Ingredient();
        ing3.setName("Noodles");
        ing4 = new Ingredient();
        ing4.setName("Yams");
        ing5 = new Ingredient();
        ing5.setName("Garri");
        IngredientService is = new IngredientService();
        is.getFromName(ing.getName().toLowerCase());
        is.getFromName(ing2.getName().toLowerCase());
        is.getFromName(ing3.getName().toLowerCase());
        is.getFromName(ing4.getName().toLowerCase());
        is.getFromName(ing5.getName().toLowerCase());
        is.save(ing);
        is.save(ing2);
        is.save(ing3);
        is.save(ing4);
        is.save(ing5);
    }

    @After
    public void tearDown() throws Exception {
        List<Profile> createdDuringTesting = ps.getAll();
        IngredientService is = new IngredientService();
        List<Ingredient> deleteAllIng = is.getAll();

        for (Profile p : createdDuringTesting) {
            ps.delete(p);
        }

        for (Ingredient i : deleteAllIng) {
            is.delete(i);
        }
    }

    @Test
    public void addToPantry() throws Exception {
        myPan = new MyPantry();
        myPan.addToPantry(ing.toString());

        assertTrue(prof.getPantry().contains(ing));
    }

    @Test
    public void addDislike() {
        ArrayList<Ingredient> list = new ArrayList<>();
        ArrayList<Ingredient> list2 = new ArrayList<>();
        list.add(ing3);
        list2.add(ing2);
        JList availableIngredientsJList = new JList<>(list.toArray());
        JList profilePantryJList = new JList<>(list2.toArray());
        availableIngredientsJList.setSelectedIndex(0);
        profilePantryJList.setSelectedIndex(0);

        myPan = new MyPantry();
        myPan.addDislike(availableIngredientsJList,profilePantryJList);

        assertTrue(prof.getDislikedIngredients().contains(ing3));
        assertTrue(prof.getDislikedIngredients().contains(ing2));
    }

    @Test
    public void removeDislike() {
        ArrayList<Ingredient> list = new ArrayList<>();
        ArrayList<Ingredient> list2 = new ArrayList<>();
        list.add(ing3);
        list2.add(ing2);
        JList availableIngredientsJList = new JList<>(list.toArray());
        JList profilePantryJList = new JList<>(list2.toArray());
        availableIngredientsJList.setSelectedIndex(0);
        profilePantryJList.setSelectedIndex(0);

        myPan = new MyPantry();
        myPan.removeDislike(availableIngredientsJList);
        myPan.removeDislike(profilePantryJList);

        assertTrue(!prof.getPantry().contains(ing2));
        assertFalse(prof.getPantry().contains(ing3));
    }

    @Test
    public void addIngredientToPantryFromList() {
        ArrayList<Ingredient> list = new ArrayList<>();
        list.add(ing5);
        JList availableIngredientsJList = new JList<>(list.toArray());
        availableIngredientsJList.setSelectedIndex(0);

        myPan = new MyPantry();
        myPan.addIngredientToPantryFromList(availableIngredientsJList);

        assertTrue(prof.getPantry().contains(ing5));
    }

    @Test
    public void removeIngredientFromPantryList() {
        ArrayList<Ingredient> list = new ArrayList<>();
        list.add(ing4);

        JList availableIngredientsJList = new JList<>(list.toArray());
        availableIngredientsJList.setSelectedIndex(0);

        myPan = new MyPantry();
        myPan.removeIngredientFromPantryList(availableIngredientsJList);

        assertFalse(prof.getPantry().contains(ing4));
    }
}