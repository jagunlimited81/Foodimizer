package edu.ilstu.Foodimizer.ui.pages;

import edu.ilstu.Foodimizer.app.StateManager;
import edu.ilstu.Foodimizer.app.db.models.Recipe;
import edu.ilstu.Foodimizer.app.db.service.RecipeService;
import edu.ilstu.Foodimizer.ui.MainWindowContentManager;
import edu.ilstu.Foodimizer.ui.jcomponents.RecipeSearchResult;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class RecipeSearchResultsPage extends Page {

    private static RecipeSearchResultsPage instance = null;
    ArrayList<Recipe> recipesOnPage = new ArrayList<>();
    JPanel contentPanel;

    public RecipeSearchResultsPage() {
        init();
    }

    public static RecipeSearchResultsPage getInstance() {
        if (instance == null)
            instance = new RecipeSearchResultsPage();
        return instance;
    }

    public void setActiveRecipes(List<Recipe> recipes) {
        if (recipes != null)
            recipesOnPage = new ArrayList<>(recipes);
    }

    @Override
    protected void init() {
        if (StateManager.getInstance().getActiveProfile() == null)
            return;

        contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        JLabel title = new JLabel("Search Results");
        title.setFont(new Font("Verdana", Font.PLAIN, 21));
        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        contentPanel.add(title);
        RecipeService rs = new RecipeService();

        for (Recipe recipe : recipesOnPage) {
            RecipeSearchResult result = new RecipeSearchResult(recipe);
            result.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    RecipePage.getInstance().setRecipe(recipe);
                    MainWindowContentManager.getInstance().goToPage("RecipePage");
                }
            });
            contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));
            contentPanel.add(result);
        }
        JScrollPane csp = new JScrollPane(contentPanel);
        csp.setPreferredSize(new Dimension(this.getWidth(), this.getHeight()));
        this.setLayout(new BorderLayout());
        this.add(csp);
    }
}
