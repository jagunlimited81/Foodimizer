package edu.ilstu.Foodimizer.app.db.models;

import javax.persistence.Entity;
import java.util.Dictionary;
import java.util.Hashtable;

@Entity
public class Recipe {

    private String uniqueId;
    private String name;
    private String description;
    private Dictionary ingredients; // Ingredient, Quantity
    private float cookTime;
    private float waitTime;
    private float prepTime;
    private String mealType;
    private int servingSize;
    private String directions;
    private String cookMethod;

    public Recipe(String uniqueId,
                  String name,
                  String description,
                  float cookTime,
                  float waitTime,
                  float prepTime,
                  String mealType,
                  int servingSize,
                  String directions,
                  Dictionary ingredients) {
        this.uniqueId = uniqueId;
        this.name = name;
        this.description = description;
        this.cookTime = cookTime;
        this.waitTime = waitTime;
        this.prepTime = prepTime;
        this.mealType = mealType;
        this.servingSize = servingSize;
        this.directions = directions;
        this.cookMethod = cookMethod;
        this.ingredients = new Hashtable();



    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getCookTime() {
        return "" + cookTime;
    }

    public String getWaitTime() {
        return "" + waitTime;
    }

    public String getPrepTime() {
        return "" + prepTime;
    }

    public String getMealType() {
        return "" + mealType;
    }

    public String getServingSize() {
        return "" + servingSize;
    }

    public String getCookMethod() {
        return "" + cookMethod;
    }

    public String getDirections() {
        return "" + directions;
    }

    public String[] getIngredients() {
        String[] strings = {};
        return strings;
    }
}
