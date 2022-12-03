package edu.ilstu.Foodimizer.app.db.models;


import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

/**
 * The Recipe class holds recipe data such as name, directions, and ingredients.
 * It does not interact with the database, so be sure to save the entities.
 */
@Entity
@Table(name = "RECIPES")
public class Recipe {

    @ManyToMany
    @JoinTable(
            name = "made_of",
            joinColumns = @JoinColumn(name = "recipeId"),
            inverseJoinColumns = @JoinColumn(name = "ingredientId")
    )
    private final Set<Ingredient> recipeIngredients = new HashSet<>();
    @ManyToMany(mappedBy = "favoriteRecipes")
    private final Set<Profile> profilesThatFavoriteThisRecipe = new HashSet<>();
    @OneToMany(mappedBy = "recipe")
    private final Set<JoinProfileRateRecipe> ProfileRatings = new HashSet<>();
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recipeId")
    private long recipeId;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "directions")
    private String directions;
    @Column(name = "mealType")
    private String mealType;
    @Column(name = "servingSize")
    private int servingSize;
    @Column(name = "cookMethod")
    private String cookMethod;
    @Column(name = "cookTime")
    private long cookTime;
    @Column(name = "waitTime")
    private long waitTime;
    @Column(name = "prepTime")
    private long prepTime;
    @Lob
    @Column(name = "thumbnail")
    private byte[] thumbnail;

    public Recipe() {

    }

    public Set<JoinProfileRateRecipe> getProfileRatings() {
        return ProfileRatings;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDirections() {
        return directions;
    }

    public void setDirections(String directions) {
        this.directions = directions;
    }

    public Set<Ingredient> getRecipeIngredients() {
        return recipeIngredients;
    }

    public void addIngredient(Ingredient ingredient) {
        this.recipeIngredients.add(ingredient);
        ingredient.getRecipesThatContainThisIngredient().add(this);
    }

    public void removeIngredient(Ingredient ingredient) {
        this.recipeIngredients.remove(ingredient);
        ingredient.getRecipesThatContainThisIngredient().remove(this);
    }

    public String getMealType() {
        return mealType;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    public int getServingSize() {
        return servingSize;
    }

    public void setServingSize(int servingSize) {
        this.servingSize = servingSize;
    }

    public String getCookMethod() {
        return cookMethod;
    }

    public void setCookMethod(String cookMethod) {
        this.cookMethod = cookMethod;
    }

    public long getCookTime() {
        return cookTime;
    }

    public void setCookTime(long cookTime) {
        this.cookTime = cookTime;
    }

    public long getWaitTime() {
        return waitTime;
    }

    public void setWaitTime(long waitTime) {
        this.waitTime = waitTime;
    }

    public long getPrepTime() {
        return prepTime;
    }

    public void setPrepTime(long prepTime) {
        this.prepTime = prepTime;
    }

    public byte[] getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(byte[] thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String toString() {
        StringBuilder returnStr = new StringBuilder(this.name + " : {");
        for (Ingredient i : recipeIngredients) {
            returnStr.append(i.getName()).append(", ");
        }
        return returnStr + "}";
    }

    public Set<Profile> getProfilesThatFavoriteThisRecipe() {
        return profilesThatFavoriteThisRecipe;
    }

    public long getRecipeId() {
        return recipeId;
    }
}
