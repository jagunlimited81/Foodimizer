package edu.ilstu.Foodimizer.app.db.models;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

/**
 * The Ingredient class is simple but vital to the operation of Foodimizer.
 * <p>
 * The ingredients are keyed by ingredientId, not name.
 * The class is always on the subordinate side of a relationship.
 * It does not interact with the database, so be sure to save the entities.
 */
@Entity
@Table(name = "INGREDIENTS")
public class Ingredient {

    @ManyToMany(mappedBy = "dislikedIngredients")
    private final Set<Profile> profilesThatDislikeThisIngredient;
    @ManyToMany(mappedBy = "shoppingList")
    private final Set<Profile> profilesShoppingListsThatContainThisIngredient;
    @ManyToMany(mappedBy = "pantry")
    private final Set<Profile> profilesPantriesThatContainThisIngredient;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ingredientId", nullable = false, unique = true)
    long ingredientId;
    @Column(name = "name", nullable = false, unique = true)
    String name;
    @ManyToMany(mappedBy = "recipeIngredients")
    private Set<Recipe> recipesThatContainThisIngredient;

    public Ingredient() {
        this.recipesThatContainThisIngredient = new HashSet<>();
        this.profilesThatDislikeThisIngredient = new HashSet<>();
        this.profilesPantriesThatContainThisIngredient = new HashSet<>();
        this.profilesShoppingListsThatContainThisIngredient = new HashSet<>();
    }

    public long getIngredientId() {
        return ingredientId;
    }

    public Set<Recipe> getRecipesThatContainThisIngredient() {
        return recipesThatContainThisIngredient;
    }

    public void setRecipesThatContainThisIngredient(Set<Recipe> recipeSet) {
        this.recipesThatContainThisIngredient = recipeSet;
    }

    public Set<Profile> getProfilesThatDislikeThisIngredient() {
        return profilesThatDislikeThisIngredient;
    }

    public Set<Profile> getProfilesShoppingListsThatContainThisIngredient() {
        return profilesShoppingListsThatContainThisIngredient;
    }

    public Set<Profile> getProfilesPantriesThatContainThisIngredient() {
        return profilesPantriesThatContainThisIngredient;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }

}
