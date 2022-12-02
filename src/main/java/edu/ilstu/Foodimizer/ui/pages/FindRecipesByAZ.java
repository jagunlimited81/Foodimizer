package edu.ilstu.Foodimizer.ui.pages;

import edu.ilstu.Foodimizer.Controller.RecipeSearchController;
import edu.ilstu.Foodimizer.app.db.models.Recipe;
import edu.ilstu.Foodimizer.app.db.service.RecipeService;
import edu.ilstu.Foodimizer.ui.MainWindowContentManager;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FindRecipesByAZ extends Page {
    public FindRecipesByAZ() {
        init();
    }

    public void init() {
        //ArrayList to put into the JComboBox
        List<String> recipe_list = new ArrayList<>();
        // Get the Recipe in the Database
        List<Recipe> recipes = new RecipeService().getAll();
        for (Recipe r: recipes)
            recipe_list.add(r.getName());
        Collections.sort(recipe_list);

        setBackground(Color.white);

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane,BoxLayout.Y_AXIS));
        contentPane.add(Box.createRigidArea(new Dimension(50, 50)));
        contentPane.setPreferredSize(new Dimension(650,650));
        contentPane.setBackground(Color.white);

        /* Logo */
        {
            logo_panel = new JPanel();
            logo_panel.setPreferredSize(new Dimension(350,350));
            logo_panel.setBackground(Color.white);

            foodimizer_logo = new ImageIcon("logo.png");
            logo = new JLabel(foodimizer_logo);

            logo_panel.add(logo);
        }
        contentPane.add(logo_panel);
        /* Combo Box and Search */
        {
            combo_panel = new JPanel();
            combo_panel.setPreferredSize(new Dimension(300,300));
            combo_panel.setBackground(Color.white);

            List_Of_Recipe_JC = new JComboBox<>();
            List_Of_Recipe_JC.setFont(new Font("Helvetica Neue", Font.PLAIN, 20));
            List_Of_Recipe_JC.setModel(new DefaultComboBoxModel<>(recipe_list.toArray(new String[0])));
            List_Of_Recipe_JC.setMaximumRowCount(5);

            searchButton = new JButton("Search");
            searchButton.setFont(new Font("Helvetica Neue", Font.PLAIN, 20));
            RecipeSearchController r = new RecipeSearchController(List_Of_Recipe_JC);
            searchButton.addActionListener(r);

            combo_panel.add(List_Of_Recipe_JC);
            combo_panel.add(searchButton);
        }
        contentPane.add(combo_panel);

        add(contentPane);
    }

    ImageIcon foodimizer_logo;

    JPanel logo_panel;
    JPanel combo_panel;

    JLabel logo;
    JComboBox<String> List_Of_Recipe_JC;
    JButton searchButton;
}
