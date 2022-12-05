package edu.ilstu.Foodimizer.ui;

import edu.ilstu.Foodimizer.app.StateManager;
import edu.ilstu.Foodimizer.app.db.service.ProfileService;
import edu.ilstu.Foodimizer.ui.jcomponents.AppBar;
import edu.ilstu.Foodimizer.ui.pages.*;

import javax.swing.*;
import java.awt.*;

public class MainWindowContentManager extends JPanel {
    private static MainWindowContentManager instance = null;
    CardLayout contentPanelSwitcher;
    private JPanel pages;
    private AppBar ab;

    public MainWindowContentManager() {
    }

    /**
     * Singleton Class architecture.
     * Allows the class to be used across multiple files in a Java project
     *
     * @return Instance of MainWindowContentManager
     */
    public static MainWindowContentManager getInstance() {
        if (instance == null)
            instance = new MainWindowContentManager();
        return instance;
    }

    public void init() {
        /* this */
        setLayout(new BorderLayout());

        /* AppBar */
        ab = new AppBar();
        this.add(ab, BorderLayout.NORTH);

        /* pages */
        pages = new JPanel(new CardLayout()); // Like cards in a deck, only one is visible
        pages.add(new ProfileSelector(), "ProfileSelector");
        contentPanelSwitcher = (CardLayout) pages.getLayout();
        pages.add(new FindRecipesByName(), "FindRecipesByName");
        pages.add(new FindRecipesByIngredient(), "FindRecipesByIngredient");
        pages.add(new FindRecipesByAZ(), "FindRecipesByAZ");
        pages.add(new MyPantry(), "MyPantry");
        pages.add(new ShoppingList(),"ShoppingList");
        pages.add(new DatabaseDebugAndTest(), "DatabaseDebugAndTest");
        pages.add(RecipePage.getInstance(), "RecipePage");
        pages.add(new CreateProfile(), "CreateProfile");
        pages.add(new EditProfile(), "EditProfile");
        pages.add(new FirstTimeWelcome(), "FirstTimeWelcome");
        pages.add(RecipeSearchResultsPage.getInstance(), "RecipeSearchResultsPage");

        this.add(pages, BorderLayout.CENTER);
        // if there's no profiles, show the welcome message.
        ProfileService ps = new ProfileService();
        if (ps.getAll().isEmpty())
            this.goToPage("FirstTimeWelcome");
        else
            this.goToPage("ProfileSelector");
    }

    public void goToPage(String pageStr) {
        System.out.println(pageStr);
        // only show the appbar if a profile is loaded.
        ab.setVisible(StateManager.getInstance().getActiveProfile() != null);
        //refresh appbar content such as profile corner menu
        ab.refreshContent();

        contentPanelSwitcher.show(pages, pageStr);
        // Call update function on Page
        Page page;
        for (Component comp : pages.getComponents()) {
            if (comp.isVisible()) {
                page = (Page) comp;
                page.refreshContent();
                break;
            }
        }
    }
}
