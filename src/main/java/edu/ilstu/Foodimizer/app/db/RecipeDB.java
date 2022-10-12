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
        readInRecipes();
    }

    private void readInRecipes() {
        System.out.println("attempting to read file");
        try {
            InputStream is = this.getClass().getClassLoader().getResourceAsStream("recipes.dbtxt");
            Scanner scan = new Scanner(is).useDelimiter("\\t");
            String line = "";
            // ignore first line
            //scan.nextLine();
            /*
            while (scan.hasNextLine()) {
                Recipe readRecipe;

                //recipes.add(new Recipe(scan.next(), scan.next(), scan.next(), scan.next(), scan.next(), scan.next(), scan.next(), scan.next(), scan.next(), scan.next()));
            }

             */
        } catch (Exception e) {

        }
    }

    public Recipe[] getAllRecipes() {
        return new Recipe[]{};
    }
}
