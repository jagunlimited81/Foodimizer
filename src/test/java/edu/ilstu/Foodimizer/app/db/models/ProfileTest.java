package edu.ilstu.Foodimizer.app.db.models;

import edu.ilstu.Foodimizer.app.db.ServicesEntityManager;
import edu.ilstu.Foodimizer.app.db.service.ProfileService;
import edu.ilstu.Foodimizer.lib.ByteTools;
import org.junit.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

@Ignore
public class ProfileTest {
    private static Profile prof;
    private static ProfileService ps;


    @BeforeClass
    public static void setUp() throws Exception {
        ServicesEntityManager sem = ServicesEntityManager.getInstance();
        sem.init("FoodimizerDB-TEST");
    }

    @Before
    public void beforeTests() {
        prof = new Profile();
        prof.setName("TestProfile");
        ps = new ProfileService();
    }

    @After
    public void tearDown() throws Exception {
        List<Profile> createdDuringTesting = ps.getAll();
        for (Profile p : createdDuringTesting) {
            ps.delete(p);
        }
    }

    @Test
    public void testAddDislike() {
        Ingredient ing = new Ingredient();
        ing.setName("Ham");

        prof.addIngredientToDisliked(ing);

        Assert.assertTrue(ing.getProfilesThatDislikeThisIngredient().contains(prof));
        Assert.assertTrue(prof.getDislikedIngredients().contains(ing));
    }

    @Test
    public void testRemoveDislike() {
        Ingredient ing = new Ingredient();

        prof.addIngredientToDisliked(ing);

        prof.removeIngredientFromDisliked(ing);
        Assert.assertFalse(ing.getProfilesThatDislikeThisIngredient().contains(prof));
        Assert.assertFalse(prof.getDislikedIngredients().contains(ing));
    }

    @Test
    public void testAddIngredientToShoppingList() {
        Ingredient ing = new Ingredient();

        prof.addIngredientToShoppingList(ing);

        Assert.assertTrue(ing.getProfilesShoppingListsThatContainThisIngredient().contains(prof));
        Assert.assertTrue(prof.getShoppingList().contains(ing));
    }

    @Test
    public void testRemoveIngredientFromShoppingList() {
        Ingredient ing = new Ingredient();

        prof.addIngredientToShoppingList(ing);

        prof.removeIngredientFromShoppingList(ing);

        Assert.assertFalse(ing.getProfilesShoppingListsThatContainThisIngredient().contains(prof));
        Assert.assertFalse(prof.getShoppingList().contains(ing));
    }

    @Test
    public void testClearShoppingList() {
        Ingredient ing1 = new Ingredient();
        ing1.setName("Coffee");
        Ingredient ing2 = new Ingredient();
        ing2.setName("Cheetahs");
        Ingredient ing3 = new Ingredient();
        ing3.setName("Chicken");

        prof.addIngredientToShoppingList(ing1);
        prof.addIngredientToShoppingList(ing2);
        prof.addIngredientToShoppingList(ing3);

        prof.clearShoppingList();

        Assert.assertTrue(prof.getShoppingList().isEmpty());
        Assert.assertFalse(ing1.getProfilesShoppingListsThatContainThisIngredient().contains(prof));
        Assert.assertFalse(ing2.getProfilesShoppingListsThatContainThisIngredient().contains(prof));
        Assert.assertFalse(ing3.getProfilesShoppingListsThatContainThisIngredient().contains(prof));
    }

    @Test
    public void testAddIngredientToPantry() {
        Ingredient ing = new Ingredient();

        prof.addIngredientToPantry(ing);

        Assert.assertTrue(prof.getPantry().contains(ing));
        Assert.assertTrue(ing.getProfilesPantriesThatContainThisIngredient().contains(prof));
    }

    @Test
    public void testRemoveIngredientFromPantry() {
        Ingredient ing = new Ingredient();

        prof.addIngredientToPantry(ing);

        prof.removeIngredientFromPantry(ing);

        Assert.assertFalse(prof.getPantry().contains(ing));
        Assert.assertFalse(ing.getProfilesPantriesThatContainThisIngredient().contains(prof));
    }

    @Test
    public void testAddRecipeToFavorites() {
        Recipe rec = new Recipe();

        prof.addRecipeToFavorites(rec);

        Assert.assertTrue(prof.getFavoriteRecipes().contains(rec));
        Assert.assertTrue(rec.getProfilesThatFavoriteThisRecipe().contains(prof));
    }

    @Test
    public void testRemoveRecipeFromFavorites() {
        Recipe rec = new Recipe();

        prof.addRecipeToFavorites(rec);

        prof.removeRecipeFromFavorites(rec);

        Assert.assertFalse(prof.getFavoriteRecipes().contains(rec));
        Assert.assertFalse(rec.getProfilesThatFavoriteThisRecipe().contains(prof));
    }

    @Test
    public void testTestToString() {
    }

    @Test
    public void testSetProfilePic() {
        BufferedImage image;
        byte[] bytes;

        try {
            image = ImageIO.read(this.getClass().getResource("/images/nopfp0.png"));
            bytes = ByteTools.toByteArray(image, "png");
            prof.setProfilePic(bytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Assert.assertSame(bytes, prof.getProfilePic());
    }
}