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
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

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
                image = ImageIO.read(Objects.requireNonNull(this.getClass().getResource("/images/nopfp" + rand.nextInt(4) + ".png")));
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
        recipe.setDirections("""
                1. Fold egg in half
                2. veat egg
                3. cook the cheese on the muffin""");
        recipe.setMealType("Breakfast");
        recipe.setPrepTime(0);
        recipe.setCookTime(10);
        recipe.setWaitTime(5);
        recipeService.save(recipe);

        recipeName = "PBJ";
        ingredients = new String[]{"bread", "peanut butter", "grape jelly", "strawberry jelly"};
        recipe = createRecipeFromString(recipeName, ingredients);
        recipe.setDescription("A staple of American childhood foods, peanut butter and jelly sandwich or PBJ");
        recipe.setDirections("""
                1. Spread the peanut butter on one piece of bread.
                2. Spread the jelly on the other side.
                3. Put the two pieces of bread together to form a sandwich.
                4. Toddler adaptation: cut off crusts before serving.""");
        recipe.setMealType("Snack");
        recipe.setPrepTime(0);
        recipe.setCookTime(5);
        recipe.setWaitTime(0);
        recipeService.save(recipe);

        recipeName = "Black Beans Burger";
        ingredients = new String[]{"black beans", "hamburger bun", "olive oil", "bell pepper", "yellow onion", "garlic cloves", "ground cumin", "chilli powder", "garlic powder", "paprika", "bread crumbs", "feta cheese", "egg", "salt", "pepper", "ketchup"};
        recipe = createRecipeFromString(recipeName, ingredients);
        recipe.setCookMethod("Oven");
        recipe.setServingSize(1);
        recipe.setDescription("Between homemade, restaurant, and store-bought, these are the best black bean burgers you will ever made.");
        recipe.setDirections("""
                1. Preheat oven to 325°F (163°C).\s
                2. Spread beans evenly onto a lined baking sheet and bake for 15 minutes.
                3. Meanwhile, sauté olive oil, chopped pepper, onion, and garlic over medium heat until peppers and onions are soft, about 5-6 minutes.
                4. Form into patties– about 1/3 cup of mixture in each.
                5. Place patties on a parchment paper lined baking sheet and bake at 375°F (191°C) for 10 minutes on each side, 20 minutes total""");
        recipe.setMealType("Lunch and Dinner");
        recipe.setPrepTime(10);
        recipe.setCookTime(15);
        recipe.setWaitTime(20);
        recipeService.save(recipe);


        recipeName = "Classic Burger";
        ingredients = new String[]{"beef", "hamburger bun", "egg", "onion", "dried bread crumbs", "worcestershire", "garlic", "salt", "pepper", "mayonnaise", "ketchup", "lettuce", "tomato", "red onion"};
        recipe = createRecipeFromString(recipeName, ingredients);
        recipe.setCookMethod("Pan");
        recipe.setServingSize(1);
        recipe.setDescription("Who wouldn't love to make their own classic hamburger at home?");
        recipe.setDirections("""
                1. In a bowl, mix ground beef, egg, onion, bread crumbs, Worcestershire, garlic, 1/2 teaspoon salt, and 1/4 teaspoon pepper until well blended.\s
                2. Divide mixture into four equal portions and shape each into a patty about 4 inches wide.
                3. Lay burgers on an oiled barbecue grill over a solid bed of hot coals or high heat on a gas grill (you can hold your hand at grill level only 2 to 3 seconds); close lid on gas grill.\s
                4. Cook burgers, turning once, until browned on both sides and no longer pink inside (cut to test), 7 to 8 minutes total. Remove from grill.
                5. Lay buns, cut side down, on grill and cook until lightly toasted, 30 seconds to 1 minute.
                6. Spread mayonnaise and ketchup on bun bottoms. Add lettuce, tomato, burger, onion, and salt and pepper to taste. Set bun tops in place""");
        recipe.setPrepTime(10);
        recipe.setCookTime(10);
        recipe.setWaitTime(10);
        recipeService.save(recipe);

        recipeName = "Chicken Sandwich";
        ingredients = new String[]{"hamburger bun", "pickles", "lettuce", "tomato", "onion", "cheese", "mayonnaise"};
        recipe = createRecipeFromString(recipeName, ingredients);
        recipe.setCookMethod("Pan");
        recipe.setServingSize(1);
        recipe.setDescription("A homemade chicken sandwich tastes fresher because you aren’t re-using the same oil all day long");
        recipe.setDirections("""
                1.Pound chicken cutlets between plastic wrap to an even 1/2” thickness for even frying.
                2.Marinate chicken – In a large mixing bowl, combine marinade ingredients, add chicken and stir to coat.\s
                3.Cover and refrigerate 2-4 hours or overnight.
                4.Dredge Chicken – In a separate shallow bowl, whisk together: flour, baking powder, salt, pepper, paprika, onion powder, and garlic powder.\s
                5.Lift a piece of marinated chicken, letting excess marinade drip off, and dip chicken into the flour mixture, pressing it on with your hands to coat well.\s
                6.Transfer to a wire rack. Keep the flour mixture for step 5.
                7.Heat 1-inch of oil in a soup pot or dutch oven to 350˚F. See our favorite oils for frying below.
                8.Re-dip the chicken into the flour mixture just before frying. Add chicken 2 pieces at a time so you don’t crowd.""");
        recipe.setMealType("Lunch and Dinner");
        recipe.setPrepTime(4);
        recipe.setCookTime(10);
        recipe.setWaitTime(10);
        recipeService.save(recipe);

        recipeName = "Horseshoe Burger";
        ingredients = new String[]{"fries", "ground beef", "bread", "butter", "flour", "shredded cheese", "salt", "ground pepper", "hot sauce"};
        recipe = createRecipeFromString(recipeName, ingredients);
        recipe.setCookMethod("Oven and Skillet");
        recipe.setServingSize(1);
        recipe.setDescription("""
                Every time we visit Springfield, Illinois we have to eat one of their famous horseshoes.\s
                The cheese sauce is a little different depending on where you stop to eat,\s
                seems everyone has their own way of making it.""");
        recipe.setDirections("""
                1. Preheat the oven to 400 degrees F (200 degrees C).\s
                2. Spread french fries out on a baking sheet. Bake for 20 minutes in the preheated oven, or until golden brown.\s
                3. Meanwhile, divide the ground beef into four equal parts and form into patties.\s
                4. Fry the patties in a large skillet over medium-high heat until well done, about 4 minutes per side.\s
                5. Melt the butter in a saucepan over medium heat and whisk in the flour.\s
                6. Cook while stirring constantly for 4 minutes to cook out the flavor of raw flour.
                7. Gradually whisk in the half-and-half so that no lumps form.\s
                8. Bring to a simmer then remove from the heat and add the cheese; stir until melted. Season with salt, pepper, and hot sauce to taste.\s
                9. Place 2 slices of toasted bread onto 2 dinner plates and top each slice with a hamburger patty.
                10. Top each patty with cooked french fries. Pour the cheese sauce on top of everything and serve immediately.\s""");
        recipe.setMealType("Lunch and Dinner");
        recipe.setPrepTime(0);
        recipe.setCookTime(24);
        recipe.setWaitTime(30);
        recipeService.save(recipe);

        recipeName = "Bacon Cheeseburger";
        ingredients = new String[]{"bacon", "ground beef", "hamburger bun", "cheese", "lettuce", "tomato", "salt", "pepper", "pickles", "ketchup"};
        recipe = createRecipeFromString(recipeName, ingredients);
        recipe.setDescription("The ultimate bacon cheeseburger with beef cooked in bacon fat, a bacon fat mayonnaise, onions caramelized in bacon fat, buns toasted in bacon fat, and a crisp bacon weave topping. ");
        recipeService.save(recipe);

        recipeName = "Fries Burger";
        ingredients = new String[]{"hamburger bun", "fries", "cheese", "lettuce", "oil", "tomato", "pickle", "mayonnaise"};
        recipe = createRecipeFromString(recipeName, ingredients);
        recipe.setCookMethod("Pan");
        recipe.setServingSize(1);
        recipe.setDescription("Just a hamburger bun with fries can make you thirsty.");
        recipe.setDirections("""
                1. Put your hamburger ban in your toaster
                2. Heat your frying pan then add your oil to the pan. Throw your fries into the frying pan.
                3. After the fries are cooked, add them to your hamburger bun with and top it off with the melted cheese""");
        recipe.setMealType("Lunch and Dinner");
        recipe.setPrepTime(0);
        recipe.setCookTime(5);
        recipe.setWaitTime(5);
        recipeService.save(recipe);

        recipeName = "Ham and Cheese";
        ingredients = new String[]{"ham", "cheese", "bread"};
        recipe = createRecipeFromString(recipeName, ingredients);
        recipe.setDescription("Classic delicious ham and cheese");
        recipeService.save(recipe);

        recipeName = "Beef Taco";
        ingredients = new String[]{"ground beef", "tortilla", "lettuce", "sour cream", "tomato", "taco seasoning", "salt", "cumin", "lime"};
        recipe = createRecipeFromString(recipeName, ingredients);
        recipe.setDescription("Class mexican beef taco");
        recipeService.save(recipe);

        recipeName = "Pork Taco";
        ingredients = new String[]{"pork taco", "tortilla", "lettuce", "sour cream", "tomato", "taco seasoning", "salt", "cumin", "lime"};
        recipe = createRecipeFromString(recipeName, ingredients);
        recipe.setDescription("Class mexican pork taco");
        recipeService.save(recipe);

        recipeName = "Lime Margarita";
        ingredients = new String[]{"lime", "tequila", "triple sec", "salt"};
        recipe = createRecipeFromString(recipeName, ingredients);
        recipe.setDescription("Who doesn't want lime margarita in their recipe cook book?");
        recipeService.save(recipe);

        recipeName = "Chicken Parmesan";
        ingredients = new String[]{"chicken", "salt", "pepper", "egg", "bread crumbs", "parmesan cheese", "olive oil", "basil", "tomato sauce"};
        recipe = createRecipeFromString(recipeName, ingredients);
        recipe.setDescription("Classic Italian Chicken Parmesan made with linguini");
        recipeService.save(recipe);

        recipeName = "Spaghetti Bolognese";
        ingredients = new String[]{"olive oil", "meat", "onion", "carrot", "celery sticks", "garlic cloves", "rosemary", "tomato sauce", "basil", "dried oregano", "fresh bay leaves"};
        recipe = createRecipeFromString(recipeName, ingredients);
        recipe.setDescription("Long strings of pasta with an Italian ragù made with minced beef, bacon and tomatoes, served with Parmesan cheese");
        recipeService.save(recipe);

        recipeName = "Fried Rice";
        ingredients = new String[]{"chilled rice", "egg", "carrot", "onion", "green onion", "peas", "garlic", "soy sauce", "oyster sauce", "toasted sesame oil", "butter"};
        recipe = createRecipeFromString(recipeName, ingredients);
        recipe.setDescription("Basic Chinese Fried Rice");
        recipeService.save(recipe);

        recipeName = "Meat Pizza";
        ingredients = new String[]{"water", "dry yeast", "granulated sugar", "all purpose flour", "salt", "pizza sauce", "mozzarella", "parmesan cheese", "bacon", "ham", "sausage", "ground black pepper", "pepperoni slices"};
        recipe = createRecipeFromString(recipeName, ingredients);
        recipe.setDescription("Freshly made home made meat pizza better than Domino");
        recipeService.save(recipe);

        recipeName = "Pad Thai";
        ingredients = new String[]{"flat rice noodles", "Tablespoons oil", "garlic", "egg", "bean sprout", "red bell pepper", "green onions", "peanut", "lime", "cilantro", "fish sauce", "soy sauce", "brown sugar", "rice vinegar", "sriracha hot sauce", "creamy peanut butter"};
        recipe = createRecipeFromString(recipeName, ingredients);
        recipe.setDescription("Thai Classic Noodle");
        recipeService.save(recipe);

        recipeName = "Steak Quesadillas";
        ingredients = new String[]{"tortillas", "cheese", "steak", "onion"};
        recipe = createRecipeFromString(recipeName, ingredients);
        recipe.setDescription("Your favorite steak quesadillas");
        recipeService.save(recipe);

        recipeName = "Chicken Quesadillas";
        ingredients = new String[]{"tortillas", "cheese", "chicken", "onion"};
        recipe = createRecipeFromString(recipeName, ingredients);
        recipe.setDescription("Your favorite chicken quesadillas");
        recipeService.save(recipe);

        recipeName = "Jollof Rice";
        ingredients = new String[]{"vegetable oil", "fresh plum", "red onions", "hot pepper", "tomato paste", "curry powder", "dried thyme", "dried bay leaves", "chicken stock", "unsalted butter", "rice", "salt", "black pepper", "white pepper", "tomatoes"};
        recipe = createRecipeFromString(recipeName, ingredients);
        recipe.setDescription("Class African Fried Rice");
        recipeService.save(recipe);

        recipeName = "Chicken Taco";
        ingredients = new String[]{"chicken", "tortilla", "lettuce", "sour cream", "tomato", "taco seasoning", "salt", "cumin", "lime"};
        recipe = createRecipeFromString(recipeName, ingredients);
        recipe.setDescription("Classic mexican chicken taco");
        recipeService.save(recipe);

        recipeName = "Shrimp Taco";
        ingredients = new String[]{"shrimp", "tortilla", "lettuce", "sour cream", "tomato", "taco seasoning", "salt", "cumin", "lime"};
        recipe = createRecipeFromString(recipeName, ingredients);
        recipe.setDescription("Class mexican shrimp taco");
        recipeService.save(recipe);

        recipeName = "Cambodia Pork Rice";
        ingredients = new String[]{"garlic", "soy sauce", "palm sugar", "coconut milk", "fish sauce", "lime"};
        recipe = createRecipeFromString(recipeName, ingredients);
        recipe.setDescription("Cambodia classic breakfast meal");
        recipeService.save(recipe);

        recipeName = "Orange Chicken";
        ingredients = new String[]{"chicken", "egg", "cornstarch", "flour", "oil", "orange juice", "sugar", "rice vinegar", "soy sauce", "ginger", "garlic powder", "red chilli flakes", "orange zest"};
        recipe = createRecipeFromString(recipeName, ingredients);
        recipe.setDescription("Panda Express Orange Chicken");
        recipeService.save(recipe);

        recipeName = "Kung Pao Chicken";
        ingredients = new String[]{"chicken", "chinese wine", "chinese black vinegar", "hoisin sauce", "dark soy sauce", "light soy sauce", "chicken broth"};
        recipe = createRecipeFromString(recipeName, ingredients);
        recipe.setDescription("Panda Express Kung Pao Chicken");
        recipeService.save(recipe);

        recipeName = "Spaghetti Meatball";
        ingredients = new String[]{"spaghetti", "ground beef", "bread crumbs", "parsley", "parmesan", "egg", "garlic", "red pepper flakes", "olive oil", "onion", "crush tomatoes", "bay leaf", "black pepper"};
        recipe = createRecipeFromString(recipeName, ingredients);
        recipe.setDescription("Classic Spaghetti Meatball");
        recipeService.save(recipe);

        recipeName = "Spaghetti Carbonara";
        ingredients = new String[]{"spaghetti", "egg", "parmesan", "black pepper", "olive oil", "slab guanciate", "bacon"};
        recipe = createRecipeFromString(recipeName, ingredients);
        recipe.setDescription("Classic Spaghetti Carbonara");
        recipeService.save(recipe);

        recipeName = "Mutton Biryani";
        ingredients = new String[]{"basmati rice", "mutton", "red chilli powder", "garama masala powder", "turemic powder", "water", "onion", "chilli", "salt", "garam masala", "mint leaves", "oil", "strands mace", "black cumin", "green cardamon", "black cardamon", "star anise", "pepper corn", "cinnamon", "clove"};
        recipe = createRecipeFromString(recipeName, ingredients);
        recipe.setDescription("India Fried Rice with Mutton");
        recipeService.save(recipe);

        recipeName = "Pineapple Fried Rice";
        ingredients = new String[]{"coconut oil", "egg", "pineapple", "red bell pepper", "green onion", "garlic", "cashew", "soy sauce", "rice", "salt", "lime", "sriracha", "cilantro"};
        recipe = createRecipeFromString(recipeName, ingredients);
        recipe.setDescription("Thai Fried Rice serve with pineapple.");
        recipeService.save(recipe);

        recipeName = "Lasagna";
        ingredients = new String[]{"olive oil", "ground beef", "onion", "red bell pepper", "garlic", "tomato sauce", "tomato paste", "crushed tomato", "oregano", "parsley", "italian seasoning", "garlic salt", "wine vinegar", "salt", "sugar"};
        recipe = createRecipeFromString(recipeName, ingredients);
        recipe.setDescription("Italian Lasagna");
        recipeService.save(recipe);

        recipeName = "Chicken Caesar Salad";
        ingredients = new String[]{"chicken", "olive oil", "cajun seasoning", "lettuce", "croutons", "parmesan cheese", "mayonnaise", "lemon juice", "anchovy paste", "worcestershire sauce", "dijon mustard", "garlic"};
        recipe = createRecipeFromString(recipeName, ingredients);
        recipe.setDescription("Classic Chicken Caeser Salad");
        recipeService.save(recipe);

        recipeName = "Chicken Caesar Wrap";
        ingredients = new String[]{"chicken", "tomato wrap", "olive oil", "cajun seasoning", "lettuce", "croutons", "parmesan cheese", "mayonnaise", "lemon juice", "anchovy paste", "worcestershire sauce", "dijon mustard", "garlic"};
        recipe = createRecipeFromString(recipeName, ingredients);
        recipe.setDescription("Classic Chicken Caeser Wrap");
        recipeService.save(recipe);

        recipeName = "Chicken Noodle Soup";
        ingredients = new String[]{"chicken", "pasta", "butter", "celery", "carrot", "basil", "oregano", "salt", "black pepper", "onion", "chicken broth"};
        recipe = createRecipeFromString(recipeName, ingredients);
        recipe.setDescription("FNot a can chicken noodle soup. A better one.");
        recipeService.save(recipe);

        recipeName = "Beef Noodle Soup";
        ingredients = new String[]{"beef", "pasta", "butter", "celery", "carrot", "basil", "oregano", "salt", "black pepper", "beef broth", "onion"};
        recipe = createRecipeFromString(recipeName, ingredients);
        recipe.setDescription("Not a can beef noodle soup.");
        recipeService.save(recipe);

        recipeName = "Turkey Sandwich";
        ingredients = new String[]{"white bread", "cheese", "lettuce", "tomato", "cucumber", "turkey", "mayonnaise"};
        recipe = createRecipeFromString(recipeName, ingredients);
        recipe.setDescription("Favorite Breakfast Turkey Sandwich");
        recipeService.save(recipe);

        recipeName = "Ham Sandwich";
        ingredients = new String[]{"white bread", "cheese", "lettuce", "tomato", "cucumber", "ham", "mayonnaise"};
        recipe = createRecipeFromString(recipeName, ingredients);
        recipe.setDescription("Favorite Breakfast Ham Sandwich");
        recipeService.save(recipe);

        recipeName = "Roast Beef Sandwich";
        ingredients = new String[]{"white bread", "cheese", "lettuce", "tomato", "cucumber", "roast beef", "mayonnaise"};
        recipe = createRecipeFromString(recipeName, ingredients);
        recipe.setDescription("Favorite Breakfast Roast Beef Sandwich");
        recipeService.save(recipe);
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
        return recipe;
    }
}
