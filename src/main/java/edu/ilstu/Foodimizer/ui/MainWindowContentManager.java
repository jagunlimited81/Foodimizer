package edu.ilstu.Foodimizer.ui;

import edu.ilstu.Foodimizer.ui.pages.*;

import javax.swing.*;
import java.awt.*;

public class MainWindowContentManager extends JPanel {
    public MainWindowContentManager() {
        init();
    }

    private void init() {
        /* this */
        setLayout(new CardLayout());
        pages = new JPanel(new CardLayout()); // Like cards in a deck, only one is vilible
        contentPanelSwitcher = (CardLayout) pages.getLayout();
        pages.add(new FindRecipesByName(), "FindRecipesByName");
        pages.add(new FindRecipesByIngredient(), "FindRecipesByIngredient");
        pages.add(new FindRecipesByAZ(), "FindRecipesByAZ");
        pages.add(new MyPantry(), "MyPantry");
        pages.add(new ProfileSelector(), "ProfileSelector");
        pages.add(RecipePage.getInstance(), "RecipePage");
        pages.add(CreateOrEditProfile.getInstance(), "CreateOrEditProfile");

        this.add(pages);
        this.goToPage("ProfileSelector");

    }

    public void goToPage(String page) {
        System.out.println(page);
        contentPanelSwitcher.show(pages, page);
    }

    /**
     * Singleton Class architecture.
     * Allows the class to be used across multiple files in a Java project
     *
     * @return
     */
    public static MainWindowContentManager getInstance() {
        if (instance == null)
            instance = new MainWindowContentManager();
        return instance;
    }

    private static MainWindowContentManager instance = null;
    private JPanel pages;
    CardLayout contentPanelSwitcher;
}
