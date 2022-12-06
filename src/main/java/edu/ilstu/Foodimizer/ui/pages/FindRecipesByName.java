package edu.ilstu.Foodimizer.ui.pages;

import edu.ilstu.Foodimizer.Controller.RecipeSearchController;
import edu.ilstu.Foodimizer.ui.MainWindowContentManager;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class FindRecipesByName extends Page {

    MainWindowContentManager contentManager;
    JPanel contentPane;
    JPanel logo_panel;
    JPanel search_panel;
    JLabel logo;
    JLabel searchPrompt;
    JTextField searchText;
    JButton searchButton;
    ImageIcon foodimizer_logo;

    public FindRecipesByName() {
        init();
    }

    protected void init() {
        setBackground(Color.white);

        contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        contentPane.setPreferredSize(new Dimension(1000, 1000));
        contentPane.add(Box.createRigidArea(new Dimension(50, 50)));
        contentPane.setBackground(Color.white);

        contentManager = MainWindowContentManager.getInstance();

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

            searchPrompt = new JLabel("Search Recipe: ");
            searchPrompt.setFont(new Font("Helvetica Neue", Font.PLAIN, 20));

            searchButton = new JButton("Search");
            searchButton.setFont(new Font("Helvetica Neue", Font.PLAIN, 20));

            // Declare an object of RecipeSearchController class to make the searchButton to go to the recipe page
            RecipeSearchController recipeSearchController = new RecipeSearchController(searchText, searchButton);
            searchButton.addActionListener(recipeSearchController);

            search_panel.add(searchPrompt, BorderLayout.WEST);
            search_panel.add(searchText, BorderLayout.CENTER);
            search_panel.add(searchButton, BorderLayout.EAST);
        }
        contentPane.add(search_panel);

        add(contentPane);
    }
}
