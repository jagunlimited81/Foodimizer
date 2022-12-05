package edu.ilstu.Foodimizer.ui.pages;

import edu.ilstu.Foodimizer.Controller.IngredientSearchController;
import edu.ilstu.Foodimizer.Controller.SearchFunction;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;


// Search for Recipe that have any ingredients in the pantry
public class FindRecipesByIngredient extends Page {

    JPanel contentPane;
    JPanel logo_panel;
    JPanel search_panel;
    JLabel logo;
    JLabel searchPrompt;
    JTextField searchText;
    JButton searchButton;
    ImageIcon foodimizer_logo;

    public FindRecipesByIngredient() {
        init();
    }

    protected void init() {
        setBackground(Color.white);

        contentPane = new JPanel();
        contentPane.setBackground(Color.white);
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        contentPane.setPreferredSize(new Dimension(1000, 1000));
        contentPane.add(Box.createRigidArea(new Dimension(50, 50)));

        /* Logo */
        {
            logo_panel = new JPanel();
            logo_panel.setPreferredSize(new Dimension(300, 300));
            logo_panel.setBackground(Color.white);

            foodimizer_logo = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/images/logo.png")));
            logo = new JLabel(foodimizer_logo);

            logo_panel.add(logo);
        }
        contentPane.add(logo_panel);

        /* Search Pane */
        {
            search_panel = new JPanel();
            search_panel.setPreferredSize(new Dimension(500, 500));
            search_panel.setBackground(Color.white);

            searchText = new JTextField(35);
            searchText.setFont(new Font("Helvetica Neue", Font.PLAIN, 20));
            IngredientSearchController ingredientSearchController = new IngredientSearchController(searchText);

            searchPrompt = new JLabel("Search Ingredient: ");
            searchPrompt.setFont(new Font("Helvetica Neue", Font.PLAIN, 20));

            searchButton = new JButton("Search");
            searchButton.setFont(new Font("Helvetica Neue", Font.PLAIN, 20));

            SearchFunction searchFunction = new SearchFunction(ingredientSearchController);
            searchFunction.searchIngredient(searchButton);

            search_panel.add(searchPrompt, BorderLayout.WEST);
            search_panel.add(searchText, BorderLayout.CENTER);
            search_panel.add(searchButton, BorderLayout.EAST);
        }
        contentPane.add(search_panel);

        add(contentPane);

    }
}
