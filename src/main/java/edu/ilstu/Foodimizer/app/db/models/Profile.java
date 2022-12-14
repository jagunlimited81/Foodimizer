package edu.ilstu.Foodimizer.app.db.models;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

/**
 * The Profile class is what holds profile data such as disliked ingredients,
 * the shopping list, the pantry, and more. It does not interact with the database,
 * so be sure to save the entities.
 */
@Entity
@Table(name = "PROFILES")
public class Profile {
    @ManyToMany
    @JoinTable(
            name = "PROFILE_DISLIKES",
            joinColumns = @JoinColumn(name = "profileId"),
            inverseJoinColumns = @JoinColumn(name = "ingredientId")
    )
    private final Set<Ingredient> dislikedIngredients = new HashSet<>();
    @ManyToMany
    @JoinTable(
            name = "PROFILE_FAVORITES",
            joinColumns = @JoinColumn(name = "profileId"),
            inverseJoinColumns = @JoinColumn(name = "recipeId")
    )
    private final Set<Recipe> favoriteRecipes = new HashSet<>();
    @ManyToMany
    @JoinTable(
            name = "PROFILE_SHOPPINGLIST",
            joinColumns = @JoinColumn(name = "profileId"),
            inverseJoinColumns = @JoinColumn(name = "ingredientId")
    )
    private final Set<Ingredient> shoppingList = new HashSet<>();
    @ManyToMany
    @JoinTable(
            name = "PROFILE_PANTRY",
            joinColumns = @JoinColumn(name = "profileId"),
            inverseJoinColumns = @JoinColumn(name = "ingredientId")
    )
    private final Set<Ingredient> pantry = new HashSet<>();
    @OneToMany(mappedBy = "profile")
    private final Set<JoinProfileRateRecipe> RecipeRatings = new HashSet<>();
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profileId", nullable = false, unique = true)
    private long profileId;
    @Column(name = "name", length = 100, nullable = false, unique = true)
    private String name;
    @Column(name = "profilePic", nullable = true)
    @Lob
    private byte[] profilePic;

    public Profile() {
    }

    public Set<JoinProfileRateRecipe> getRecipeRatings() {
        return RecipeRatings;
    }

    public long getProfileId() {
        return profileId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Ingredient> getDislikedIngredients() {
        return dislikedIngredients;
    }

    /**
     * Links the ingredient to dislikedIngredients.
     *
     * @param ingredient The ingredient to add to dislikedIngredients
     */
    public void addIngredientToDisliked(Ingredient ingredient) {
        this.dislikedIngredients.add(ingredient);
        ingredient.getProfilesThatDislikeThisIngredient().add(this);
    }

    /**
     * Unlinks the ingredient from dislikedIngredients
     *
     * @param ingredient The ingredient to remove from dislikedIngredients
     */
    public void removeIngredientFromDisliked(Ingredient ingredient) {
        this.dislikedIngredients.remove(ingredient);
        ingredient.getProfilesThatDislikeThisIngredient().remove(this);
    }

    public Set<Ingredient> getShoppingList() {
        return shoppingList;
    }

    /**
     * Links the ingredient to shoppingList.
     *
     * @param ingredient The ingredient to add to shoppingList
     */
    public void addIngredientToShoppingList(Ingredient ingredient) {
        this.shoppingList.add(ingredient);
        ingredient.getProfilesShoppingListsThatContainThisIngredient().add(this);
    }

    /**
     * Unlinks the ingredient from shoppingList
     *
     * @param ingredient The ingredient to remove from shoppingList
     */
    public void removeIngredientFromShoppingList(Ingredient ingredient) {
        this.shoppingList.remove(ingredient);
        ingredient.getProfilesShoppingListsThatContainThisIngredient().remove(this);
    }

    public void clearShoppingList() {
        for (Ingredient ing : this.shoppingList)
        {
            ing.getProfilesShoppingListsThatContainThisIngredient().remove(this);
        }
        this.getShoppingList().removeAll(this.getShoppingList());
    }

    public Set<Ingredient> getPantry() {
        return pantry;
    }

    /**
     * Links the ingredient to pantry.
     *
     * @param ingredient The ingredient to add to pantry
     */
    public void addIngredientToPantry(Ingredient ingredient) {
        this.pantry.add(ingredient);
        ingredient.getProfilesPantriesThatContainThisIngredient().add(this);
    }

    /**
     * Unlinks the ingredient from pantry
     *
     * @param ingredient The ingredient to remove from pantry
     */
    public void removeIngredientFromPantry(Ingredient ingredient) {
        this.pantry.remove(ingredient);
        ingredient.getProfilesPantriesThatContainThisIngredient().remove(this);
    }

    public Set<Recipe> getFavoriteRecipes() {
        return favoriteRecipes;
    }

    /**
     * Links the recipe to favoriteRecipes.
     *
     * @param recipe The recipe to add to favoriteRecipes
     */
    public void addRecipeToFavorites(Recipe recipe) {
        this.favoriteRecipes.add(recipe);
        recipe.getProfilesThatFavoriteThisRecipe().add(this);
    }

    /**
     * Unlinks the recipe from favoriteRecipes
     *
     * @param recipe The recipe to remove from favoriteRecipes
     */
    public void removeRecipeFromFavorites(Recipe recipe) {
        this.favoriteRecipes.remove(recipe);
        recipe.getProfilesThatFavoriteThisRecipe().remove(this);
    }

    public String toString() {
        StringBuilder returnString = new StringBuilder(name + "\n\tFavoriteRecipes: [");
        for (Recipe r : favoriteRecipes)
            returnString.append(r.getName()).append(", ");
        returnString.append("]\n\tShopping List: [");
        for (Ingredient i : shoppingList)
            returnString.append(i.getName()).append(", ");
        returnString.append("]\n\tPantry: [");
        for (Ingredient i : pantry)
            returnString.append(i.getName()).append(", ");
        returnString.append("]\n\tDisliked: [");
        for (Ingredient i : dislikedIngredients)
            returnString.append(i.getName()).append(", ");
        returnString.append("]");

        return returnString.toString();
    }


    public byte[] getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(byte[] profilePic) {
        this.profilePic = profilePic;
    }
}
