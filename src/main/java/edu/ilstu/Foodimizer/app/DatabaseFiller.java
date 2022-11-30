package edu.ilstu.Foodimizer.app;

import edu.ilstu.Foodimizer.app.db.models.Ingredient;
import edu.ilstu.Foodimizer.app.db.models.Profile;
import edu.ilstu.Foodimizer.app.db.models.Recipe;
import edu.ilstu.Foodimizer.app.db.service.IngredientService;
import edu.ilstu.Foodimizer.app.db.service.ProfileService;
import edu.ilstu.Foodimizer.app.db.service.RecipeService;
import edu.ilstu.Foodimizer.lib.ByteTools;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;

import static edu.ilstu.Foodimizer.lib.ByteTools.toByteArray;

public class DatabaseFiller {
    IngredientService ingredientService;
    ProfileService profileService;
    RecipeService recipeService;

    public DatabaseFiller() {
        init();
    }

    private void init() {
        ingredientService = new IngredientService();
        profileService = new ProfileService();
        recipeService = new RecipeService();
    }


    /**
     * Creates some test profiles
     */
    public void createTestProfiles() {
        for (int i = 0; i < 5; i++) {
            Profile profile = new Profile();
            BufferedImage image;
            profile.setName("Profile " + i);

            try {
                Random rand = new Random();
                image = ImageIO.read(this.getClass().getResource("/images/nopfp" + rand.nextInt(4) + ".png"));
                byte[] bytes = ByteTools.toByteArray(image, "png");
                profile.setProfilePic(bytes);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            profileService.save(profile);
        }
    }

    /**
     * Creates some ingredients in the database
     */
    public void createTestIngredients() {
        String[] ingredientsList = new String[]{"egg", "cheese", "sausage", "bread", "english muffin"};
        for (String s : ingredientsList) {
            Ingredient ingredient = new Ingredient();
            ingredient.setName(s);
            ingredientService.save(ingredient);
        }
    }

    /**
     * Creates some recipes in the database
     */
    public void createTestRecipes() {
        String recipeName;
        String[] ingredients;
        Recipe recipe;

        recipeName = "English Muffin";
        ingredients = new String[]{"bacon", "egg", "cheese", "muffin"};
        recipe = createRecipeFromString(recipeName, ingredients);
        recipe.setCookMethod("Stove");
        recipe.setServingSize(1);
        recipe.setDescription("A warm english meal");
        recipe.setDirections("1. Fold egg in half\n2.veat egg\n3. cook the cheese on the muffin");
        recipe.setMealType("Breakfast");
        recipe.setPrepTime(0);
        recipe.setCookTime(10);
        recipe.setWaitTime(5);
        recipeService.save(recipe);

        recipeName = "Bacon Cheeseburger";
        ingredients = new String[]{"bacon", "ground beef", "hamburger bun", "cheese", "lettuce", "tomato", "salt", "pepper", "pickles", "ketchup"};
        createRecipeFromString(recipeName, ingredients);

        recipeName = "Ham and Cheese";
        ingredients = new String[]{"ham", "cheese", "bread"};
        createRecipeFromString(recipeName, ingredients);

        recipeName = "Beef Taco";
        ingredients = new String[]{"ground beef", "tortilla", "lettuce", "sour cream", "tomato", "taco seasoning", "salt", "cumin", "lime"};
        createRecipeFromString(recipeName, ingredients);

        recipeName = "Lime Margarita";
        ingredients = new String[]{"lime", "tequila", "triple sec", "salt"};
        createRecipeFromString(recipeName, ingredients);

        recipeName = "Chicken Parmesan";
        ingredients = new String[]{"chicken", "salt", "pepper", "egg", "bread crumbs", "parmesan cheese", "olive oil", "basil", "tomato sauce"};
        createRecipeFromString(recipeName, ingredients);

        recipeName = "Spaghetti Bolognese";
        ingredients = new String[]{"olive oil", "meat", "onion", "carrot", "celery sticks", "garlic cloves", "rosemary", "tomato sauce", "basil", "dried oregano", "fresh bay leaves"};
        createRecipeFromString(recipeName, ingredients);
    }

    /**
     * Creates a recipe using the name and ingredients provided. Saves entry to the database.
     *
     * @param recipeName  The name of the recipe to be created
     * @param ingredients The ingredients of the recipe to be created as a String[]
     * @return A partial Recipe object
     */
    private Recipe createRecipeFromString(String recipeName, String[] ingredients) {
        Recipe recipe = new Recipe();
        recipe.setName(recipeName);

        for (String i : ingredients) {
            Ingredient existingIngredient = ingredientService.getFromName(i);
            if (existingIngredient == null) {
                Ingredient newIngredient = new Ingredient();
                newIngredient.setName(i);
                ingredientService.save(newIngredient);
                recipe.addIngredient(newIngredient);
            } else {
                recipe.addIngredient(existingIngredient);
            }
        }
        recipeService.save(recipe);
        return recipe;
    }
}
