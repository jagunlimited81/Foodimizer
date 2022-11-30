package edu.ilstu.Foodimizer.ui.pages;


import edu.ilstu.Foodimizer.app.db.models.Ingredient;
import edu.ilstu.Foodimizer.app.db.service.IngredientService;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MyPantry extends Page {
    public MyPantry() {
        super();
        init();
    }

    private void init() {
        IngredientService is = new IngredientService();
        PantryItemsPanel = new JPanel();
        addItemsToMyPantry = new JButton("Add Items to My Pantry");


        this.setLayout(new BorderLayout());

        /* Pantry Items Panel */
        PantryItemsPanel.setBackground(Color.GREEN);
        this.add(PantryItemsPanel, BorderLayout.CENTER);

        /* Search Bar */
        searchPanel = new JPanel();
        searchPanel.setBackground(Color.BLUE);
        searchBox = new JList(is.getAll().toArray());
        searchPanel.add(searchBox);
        this.add(searchPanel, BorderLayout.NORTH);

    }

    private void addItemsToPantry(List ingredientList) {

    }


    JPanel PantryItemsPanel;
    JPanel searchPanel;
    JButton addItemsToMyPantry;
    JList<Ingredient> searchBox;

}
