package edu.ilstu.Foodimizer.app.db;


import edu.ilstu.Foodimizer.lib.Recipe;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class RecipeDB {
    private ArrayList<Recipe> recipes;

    public RecipeDB() {
        init();
    }

    private void init() {

    }

    public Recipe[] getAllRecipes() {
        return new Recipe[]{};
    }
}
