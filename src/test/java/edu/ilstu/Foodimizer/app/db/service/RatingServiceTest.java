package edu.ilstu.Foodimizer.app.db.service;

import edu.ilstu.Foodimizer.app.db.ServicesEntityManager;
import edu.ilstu.Foodimizer.app.db.models.JoinProfileRateRecipe;
import edu.ilstu.Foodimizer.app.db.models.Profile;
import edu.ilstu.Foodimizer.app.db.models.Recipe;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class RatingServiceTest {
    static ProfileService ps;
    static Profile profile;

    static RecipeService rs;
    static Recipe recipe;

    static RatingService rts;


    @BeforeClass
    public static void setUp() {
        ServicesEntityManager sem = ServicesEntityManager.getInstance();
        sem.init("FoodimizerDB-TEST");

        ps = new ProfileService();
        profile = new Profile();
        profile.setName("TESTPROFILE");
        ps.save(profile);

        rs = new RecipeService();
        recipe = new Recipe();
        recipe.setName("TESTRECIPE");
        rs.save(recipe);
    }

    @AfterClass
    public static void tearDown() {

    }

    @Test
    public void testGetRatingObj() {
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