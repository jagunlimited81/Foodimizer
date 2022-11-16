package edu.ilstu.Foodimizer.ui.pages;

import edu.ilstu.Foodimizer.app.db.models.Ingredient;
import edu.ilstu.Foodimizer.app.db.models.Recipe;
import edu.ilstu.Foodimizer.app.db.service.IngredientService;
import edu.ilstu.Foodimizer.app.db.service.ProfileService;
import edu.ilstu.Foodimizer.app.db.service.RecipeService;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DatabaseDebugAndTest extends JPanel {
    public DatabaseDebugAndTest() {
        init();
    }

    public void init() {
        IngredientService ingredientService = new IngredientService();
        ProfileService profileService = new ProfileService();
        RecipeService recipeService = new RecipeService();
        JPanel contentPanel = new JPanel();

        JButton jb = new JButton("Get All Recipes");
        jb.addActionListener(e -> printQuery(Collections.singletonList(recipeService.getAll())));
        contentPanel.add(jb);

        jb = new JButton("Get All Ingredients");
        jb.addActionListener(e -> printQuery(Collections.singletonList(ingredientService.getAll())));
        contentPanel.add(jb);

        jb = new JButton("Get All Profiles");
        jb.addActionListener(e -> printQuery(Collections.singletonList(profileService.getAll())));
        contentPanel.add(jb);


        JPanel searchPanel1 = new JPanel();
        JTextField searchBox = new JTextField();
        searchPanel1.add(searchBox);

        jb = new JButton("search by name");
        jb.addActionListener(e->printQuery(Collections.singletonList(recipeService.searchLikeString(searchBox.getText()))));
        searchPanel1.add(jb);

        contentPanel.add(searchPanel1);

        jb = new JButton("search by ingredients");
        List<Recipe> rl = recipeService.searchLikeString("English Muffin");
        Recipe r = rl.get(0);
        jb.addActionListener(e->printQuery(Collections.singletonList(recipeService.searchByIngredients(new ArrayList<>(r.getRecipeIngredients())))));
        contentPanel.add(jb);


        this.add(contentPanel);
    }

    private void printQuery(List<Object> lists) {
        for (Object li : lists) {
            for (Object o : (List) li) {
                System.out.println(o);
            }
        }

    }
}
