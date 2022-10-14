package edu.ilstu.Foodimizer.app.db.models;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "INGREDIENTS")
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ingredientId")
    long ingredientId;
    @Column(name = "name", nullable = false, unique = true)
    String name;

    @ManyToMany(mappedBy = "recipeIngredients")
    Set<Recipe> recipeSet;

    @ManyToMany(mappedBy = "dislikes")
    Set<Profile> disliked_ingredients;

    @ManyToMany(mappedBy = "shoppingList")
    Set<Profile> shoppingListIngredients;

    @ManyToMany(mappedBy = "pantry")
    Set<Profile> pantryIngredients;


    public long getId() {
        return ingredientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(long ingredientId) {
        this.ingredientId = ingredientId;
    }
}
