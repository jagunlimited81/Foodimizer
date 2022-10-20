package edu.ilstu.Foodimizer.ui.pages;

import edu.ilstu.Foodimizer.app.db.service.IngredientService;
import edu.ilstu.Foodimizer.app.db.service.ProfileService;
import edu.ilstu.Foodimizer.app.db.service.RecipeService;

import javax.swing.*;
import java.util.Collections;
import java.util.List;
import java.util.Set;

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
