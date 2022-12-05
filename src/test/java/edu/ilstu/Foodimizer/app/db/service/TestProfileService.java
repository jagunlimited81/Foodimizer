package edu.ilstu.Foodimizer.app.db.service;

import edu.ilstu.Foodimizer.app.db.ServicesEntityManager;
import edu.ilstu.Foodimizer.app.db.models.Ingredient;
import edu.ilstu.Foodimizer.app.db.models.Profile;
import edu.ilstu.Foodimizer.app.db.service.IngredientService;
import edu.ilstu.Foodimizer.app.db.service.ProfileService;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestProfileService {
    @BeforeClass
    public static void setUp() {
        ServicesEntityManager sem = ServicesEntityManager.getInstance();
        sem.init("FoodimizerDB-TEST");

    }

    @AfterClass
    public static void tearDown() {

    }

    @Test
    public void TestAddToFavorites() {
    }
}
