package edu.ilstu.Foodimizer.app.db.models;

import edu.ilstu.Foodimizer.app.db.service.ProfileService;
import edu.ilstu.Foodimizer.lib.ByteTools;
import junit.framework.TestCase;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

public class ProfileTest extends TestCase {
    private Profile prof;
    private ProfileService ps;

    public void setUp() throws Exception {
        prof = new Profile();
        prof.setName("TestProfile");
        ps = new ProfileService();
    }

    public void tearDown() throws Exception {
        List<Profile> createdDuringTesting = ps.getAll();
        for (Profile p : createdDuringTesting)
        {
            ps.delete(p);
        }
    }

    public void testAddDislike() {
        Ingredient ing = new Ingredient();
        ing.setName("Ham");

        prof.addDislike(ing);

        assertTrue(ing.getProfilesThatDislikeThisIngredient().contains(prof));
        assertTrue(prof.getDislikedIngredients().contains(ing));
    }

    public void testRemoveDislike() {
        Ingredient ing = new Ingredient();

        prof.addDislike(ing);

        prof.removeDislike(ing);
        assertFalse(ing.getProfilesThatDislikeThisIngredient().contains(prof));
        assertFalse(prof.getDislikedIngredients().contains(ing));
    }

    public void testAddIngredientToShoppingList() {
        Ingredient ing = new Ingredient();

        prof.addIngredientToShoppingList(ing);

        assertTrue(ing.getProfilesShoppingListsThatContainThisIngredient().contains(prof));
        assertTrue(prof.getShoppingList().contains(ing));
    }

    public void testRemoveIngredientFromShoppingList() {
        Ingredient ing = new Ingredient();

        prof.addIngredientToShoppingList(ing);

        prof.removeIngredientFromShoppingList(ing);

        assertFalse(ing.getProfilesShoppingListsThatContainThisIngredient().contains(prof));
        assertFalse(prof.getShoppingList().contains(ing));
    }

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

        assertTrue(prof.getShoppingList().isEmpty());
        assertFalse(ing1.getProfilesShoppingListsThatContainThisIngredient().contains(prof));
        assertFalse(ing2.getProfilesShoppingListsThatContainThisIngredient().contains(prof));
        assertFalse(ing3.getProfilesShoppingListsThatContainThisIngredient().contains(prof));
    }

    public void testAddIngredientToPantry() {
        Ingredient ing = new Ingredient();

        prof.addIngredientToPantry(ing);

        assertTrue(prof.getPantry().contains(ing));
        assertTrue(ing.getProfilesPantriesThatContainThisIngredient().contains(prof));
    }

    public void testRemoveIngredientFromPantry() {
        Ingredient ing = new Ingredient();

        prof.addIngredientToPantry(ing);

        prof.removeIngredientFromPantry(ing);

        assertFalse(prof.getPantry().contains(ing));
        assertFalse(ing.getProfilesPantriesThatContainThisIngredient().contains(prof));
    }

    public void testAddRecipeToFavorites() {
        Recipe rec = new Recipe();

        prof.addRecipeToFavorites(rec);

        assertTrue(prof.getFavoriteRecipes().contains(rec));
        assertTrue(rec.getProfilesThatFavoriteThisRecipe().contains(prof));
    }

    public void testRemoveRecipeFromFavorites() {
        Recipe rec = new Recipe();

        prof.addRecipeToFavorites(rec);

        prof.removeRecipeFromFavorites(rec);

        assertFalse(prof.getFavoriteRecipes().contains(rec));
        assertFalse(rec.getProfilesThatFavoriteThisRecipe().contains(prof));
    }

    public void testTestToString() {
    }

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

        assertSame(bytes, prof.getProfilePic());
    }

}