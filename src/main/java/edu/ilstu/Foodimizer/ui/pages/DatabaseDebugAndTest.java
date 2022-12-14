package edu.ilstu.Foodimizer.ui.pages;

import edu.ilstu.Foodimizer.app.StateManager;
import edu.ilstu.Foodimizer.app.db.models.Ingredient;
import edu.ilstu.Foodimizer.app.db.models.Profile;
import edu.ilstu.Foodimizer.app.db.models.Recipe;
import edu.ilstu.Foodimizer.app.db.service.IngredientService;
import edu.ilstu.Foodimizer.app.db.service.ProfileService;
import edu.ilstu.Foodimizer.app.db.service.RecipeService;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.List;
import java.util.*;

public class DatabaseDebugAndTest extends Page {
    Hashtable<JCheckBox, Ingredient> ht;

    public DatabaseDebugAndTest() {
        init();
    }

    public void init() {
        IngredientService ingredientService = new IngredientService();
        ProfileService profileService = new ProfileService();
        RecipeService recipeService = new RecipeService();
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new FlowLayout());

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
        jb.addActionListener(e -> printQuery(Collections.singletonList(recipeService.searchLikeString(searchBox.getText(), Recipe.class))));
        searchPanel1.add(jb);
        contentPanel.add(searchPanel1);

        TitledBorder b = new TitledBorder("ingredients");
        JPanel searchPanelIngredients = new JPanel();
        searchPanelIngredients.setLayout(new FlowLayout());
        searchPanelIngredients.setBorder(b);
        searchPanelIngredients.setPreferredSize(new Dimension(1200, 350));

        IngredientService is = new IngredientService();
        ht = new Hashtable<>();
        for (Ingredient i : is.getAll()) {
            JCheckBox jcbx = new JCheckBox(i.getName());
            searchPanelIngredients.add(jcbx);
            ht.put(jcbx, i);
        }
        jb = new JButton("search by ingredients");
        jb.addActionListener(e -> printQuery(Collections.singletonList(recipeService.searchByIngredients(getActiveIngredients()))));

        searchPanelIngredients.add(jb);

        jb = new JButton("Add to pantry");
        jb.addActionListener(e -> addSelectedIngredientsToPantry(getActiveIngredients()));

        searchPanelIngredients.add(jb);
        contentPanel.add(searchPanelIngredients);

        this.setLayout(new BorderLayout());
        this.add(contentPanel, BorderLayout.CENTER);
    }

    private void addSelectedIngredientsToPantry(List<Ingredient> activeIngredients) {
        Profile p = StateManager.getInstance().getActiveProfile();
        p.getPantry().addAll(activeIngredients);
        ProfileService ps = new ProfileService();
        ps.update(p, "");
    }

    private List<Ingredient> getActiveIngredients() {
        ArrayList<Ingredient> returnIngredients = new ArrayList<>();
        Enumeration<JCheckBox> e = ht.keys();
        while (e.hasMoreElements()) {
            JCheckBox jcb = e.nextElement();
            if (jcb.isSelected()) {
                returnIngredients.add(ht.get(jcb));
            }
        }
        return returnIngredients;
    }

    private void printQuery(List<Object> lists) {
        for (Object li : lists) {
            for (Object o : (List) li) {
                System.out.println(o);
            }
        }
    }
}
