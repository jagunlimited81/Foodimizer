package edu.ilstu.Foodimizer.lib;

import java.util.Dictionary;

public class Recipe {
    private String uniqueId;
    private String name;
    private String description;
    private Dictionary ingredients; // Ingredient, Quantity
    public float cookTime;
    public float waitTime;
    public float prepTime;

    public Recipe() {

    }
}
