package edu.ilstu.Foodimizer.ui.pages;

import edu.ilstu.Foodimizer.Main;
import edu.ilstu.Foodimizer.ui.MainWindowContentManager;

import javax.swing.*;
import java.awt.*;

public class FindRecipesByName extends JPanel {
    public FindRecipesByName() {
        init();
    }

    private void init() {
        setBackground(Color.darkGray);
        testbutton = new JButton("Recipe");
        testbutton.addActionListener(e -> buttonPressed());
        add(testbutton);

    }

    private void buttonPressed() {
        contentManager = MainWindowContentManager.getInstance();
        contentManager.goToPage("RecipePage");
    }

    private MainWindowContentManager contentManager;
    private RecipePage recipePage;
    private JButton testbutton;
}
