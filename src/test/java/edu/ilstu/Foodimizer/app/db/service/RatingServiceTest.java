package edu.ilstu.Foodimizer.app.db.service;

import edu.ilstu.Foodimizer.app.db.ServicesEntityManager;
import edu.ilstu.Foodimizer.app.db.models.Ingredient;
import edu.ilstu.Foodimizer.app.db.models.JoinProfileRateRecipe;
import edu.ilstu.Foodimizer.app.db.models.Profile;
import edu.ilstu.Foodimizer.app.db.models.Recipe;
import org.junit.*;

import java.util.List;

public class RatingServiceTest {
    private ProfileService ps;
    private Profile profile;

    private RecipeService rs;
    private Recipe recipe;

    private RatingService rts;


    @BeforeClass
    public static void setUp() {
        ServicesEntityManager sem = ServicesEntityManager.getInstance();
        sem.init("FoodimizerDB-TEST");
    }

    @Before
    public void before() {
        ps = new ProfileService();
        rs = new RecipeService();
    }

    @After
    public void tearDown() {
        List<JoinProfileRateRecipe> createdDuringTesting = rts.getAll();
        for (JoinProfileRateRecipe jprr : createdDuringTesting) {
            rts.delete(jprr);
        }
    }

    @Test
    public void testGetRatingObj() {
        profile = new Profile();
        profile.setName("TESTPROFILE");
        ps.save(profile);

        recipe = new Recipe();
        recipe.setName("TESTRECIPE");
        rs.save(recipe);

        rts = new RatingService();
        JoinProfileRateRecipe jprr = new JoinProfileRateRecipe();
        jprr.setRecipe(recipe);
        jprr.setProfile(profile);
        byte rating = 5;
        jprr.setRating(rating);

        rts.save(jprr);

        JoinProfileRateRecipe newJPRR = rts.getRatingObj(profile, recipe);

        Assert.assertSame(newJPRR, jprr);
    }
}