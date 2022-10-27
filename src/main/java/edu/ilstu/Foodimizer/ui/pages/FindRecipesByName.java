package edu.ilstu.Foodimizer.ui.pages;

import edu.ilstu.Foodimizer.Main;
import edu.ilstu.Foodimizer.ui.MainWindowContentManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class FindRecipesByName extends JPanel {
    public FindRecipesByName() {
        init();
    }

    private void init() {
        setBackground(Color.white);
        testButton = new JButton("Recipe");
        testButton.addActionListener(e -> buttonPressed());

        // Changes

        title = new JLabel("Foodimizer");
        title.setFont(new Font("Segoe UI",1, 24)); // Set the font of the title
        title.setBounds(130,100,100,40);

        searchPrompt = new JLabel("Search Recipe:");
        searchPrompt.setFont(new Font("Segoe UI",1, 14));

        searchText = new JTextField();
        searchText.setBounds(100,130,480,30);
        searchText.setColumns(20);
        searchText.setBorder(new EmptyBorder(10,5,10,5));
        searchText.setVisible(true);

        searchButton = new JButton("Search");
        searchButton.setBounds(500,130,80,30);
        searchButton.setFont(searchText.getFont());
        searchButton.setBorder(new EmptyBorder(10,5,19,5));

        add(title);
        add(searchPrompt);
        add(searchText);


    }

    private void buttonPressed() {
        contentManager = MainWindowContentManager.getInstance();
        contentManager.goToPage("RecipePage");
    }

    private MainWindowContentManager contentManager;
    private RecipePage recipePage;
    private JButton testButton;

    // Changes
    private JButton searchButton;

    private JLabel title;
    private JLabel searchPrompt;
    private JTextField searchText;
}