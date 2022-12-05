package edu.ilstu.Foodimizer.ui.pages;

import edu.ilstu.Foodimizer.app.StateManager;
import edu.ilstu.Foodimizer.app.db.models.Ingredient;
import edu.ilstu.Foodimizer.app.db.models.Profile;
import edu.ilstu.Foodimizer.app.db.service.IngredientService;
import edu.ilstu.Foodimizer.app.db.service.ProfileService;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MyPantry extends Page {
    IngredientService is = new IngredientService();
    JList availableIngredientsJList;
    JList profilePantryJList;
    JList dislikedIngredientsJList;

    public MyPantry() {
        super();
        init();
    }

    @Override
    protected void init() {
        if (StateManager.getInstance().getActiveProfile() == null) return;
        /* Get Data */
        ArrayList<Ingredient> pantryIngredients = new ArrayList<>(StateManager.getInstance().getActiveProfile().getPantry());
        ArrayList<Ingredient> dislikedIngrediients = new ArrayList<>(StateManager.getInstance().getActiveProfile().getDislikedIngredients());
        ArrayList<Ingredient> availableIngredients = new ArrayList<>(is.getAll());
        availableIngredients.removeAll(pantryIngredients);
        availableIngredients.removeAll(dislikedIngrediients);

        this.setLayout(new BorderLayout());
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());

        /* Search Bar Panel */
        JPanel searchBarPanel = new JPanel();
        {
            JLabel searchLabel = new JLabel("Search Ingredient:");
            searchLabel.setFont(new Font(searchLabel.getFont().getName(), Font.PLAIN, 20));
            searchBarPanel.add(searchLabel);

            JTextField searchTextField = new JTextField(35);
            searchTextField.setFont(new Font(searchTextField.getFont().getName(), Font.PLAIN, 20));
            searchBarPanel.add(searchTextField);

            JButton searchBtn = new JButton("Quick Add");
            searchBtn.addActionListener(e -> addToPantry(searchTextField.getText()));
            searchBarPanel.add(searchBtn);
        }
        contentPanel.add(searchBarPanel, BorderLayout.NORTH);

        /* Available Ingredients Panel */
        JPanel ingredientsPanel = new JPanel();
        ingredientsPanel.setLayout(new BorderLayout());
        JScrollPane ingredientsPanelScrollPane = new JScrollPane(ingredientsPanel);
        ingredientsPanelScrollPane.setBorder(BorderFactory.createTitledBorder("Ingredients List"));
        {
            /* Jlist */
            availableIngredientsJList = new JList(availableIngredients.toArray());
            ingredientsPanel.add(availableIngredientsJList);
        }
        ingredientsPanelScrollPane.setPreferredSize(new Dimension(this.getWidth()/3, this.getHeight()));

        contentPanel.add(ingredientsPanelScrollPane, BorderLayout.WEST);

        /* Profile Pantry Panel */
        JPanel profilePantryIngredientsPanel = new JPanel();
        profilePantryIngredientsPanel.setLayout(new BorderLayout());
        JScrollPane profilePantryIngredientsPanelScrollPane = new JScrollPane(profilePantryIngredientsPanel);
        profilePantryIngredientsPanelScrollPane.setBorder(BorderFactory.createTitledBorder("Pantry"));
        {
            profilePantryJList = new JList<>(pantryIngredients.toArray());
            profilePantryIngredientsPanel.add(profilePantryJList);
        }
        profilePantryIngredientsPanelScrollPane.setPreferredSize(new Dimension(this.getWidth()/3, this.getHeight()));
        contentPanel.add(profilePantryIngredientsPanelScrollPane, BorderLayout.CENTER);

        /* Dislikes Panel */
        JPanel dislikedIngredientsPanel = new JPanel();
        dislikedIngredientsPanel.setLayout(new BorderLayout());
        JScrollPane dislikedIngredientsPanelScrollPane = new JScrollPane(dislikedIngredientsPanel);
        dislikedIngredientsPanelScrollPane.setBorder(BorderFactory.createTitledBorder("Disliked"));
        {
            dislikedIngredientsJList = new JList<>(dislikedIngrediients.toArray());
            dislikedIngredientsPanel.add(dislikedIngredientsJList);
        }
        dislikedIngredientsPanelScrollPane.setPreferredSize(new Dimension(this.getWidth()/3, this.getHeight()));
        contentPanel.add(dislikedIngredientsPanelScrollPane, BorderLayout.EAST);

        /* Buttons Panel */
        JPanel buttonPanel = new JPanel();
        {
            JButton addBtn = new JButton("Add");
            addBtn.addActionListener(e -> addIngredientToPantryFromList(availableIngredientsJList));
            buttonPanel.add(addBtn);

            JButton rmvBtn = new JButton("Remove");
            rmvBtn.addActionListener(e -> removeIngredientFromPantryList(profilePantryJList));
            buttonPanel.add(rmvBtn);

            JButton addToDislikedIngredients = new JButton("Dislike");
            addToDislikedIngredients.addActionListener(e -> addDislike(availableIngredientsJList, profilePantryJList));
            buttonPanel.add(addToDislikedIngredients);

            JButton removeFromDislikedIngredients = new JButton("remove Dislike");
            removeFromDislikedIngredients.addActionListener(e -> removeDislike(dislikedIngredientsJList));
            buttonPanel.add(removeFromDislikedIngredients);
        }
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);
        this.add(contentPanel, BorderLayout.CENTER);
    }

    private void addDislike(JList availableIngredientsJList, JList profilePantryJList) {
        IngredientService is = new IngredientService();
        Profile activeProfile = StateManager.getInstance().getActiveProfile();
        ProfileService ps = new ProfileService();

        for (Object i : availableIngredientsJList.getSelectedValuesList()) {
            activeProfile.getDislikedIngredients().add((Ingredient) i);

        }
        for (Object i : profilePantryJList.getSelectedValuesList()) {
            activeProfile.getDislikedIngredients().add((Ingredient) i);
            activeProfile.getPantry().remove(i);
        }
        ps.update(activeProfile, "");
        refreshContent();
    }

    private void removeDislike(JList dislikedIngredientsJList) {
        IngredientService is = new IngredientService();
        Profile activeProfile = StateManager.getInstance().getActiveProfile();
        ProfileService ps = new ProfileService();

        for (Object i : dislikedIngredientsJList.getSelectedValuesList()) {
            activeProfile.getDislikedIngredients().remove((Ingredient) i);
        }
        ps.update(activeProfile, "");
        refreshContent();
    }

    private void addToPantry(String ingText) {
        IngredientService is = new IngredientService();
        Profile activeProfile = StateManager.getInstance().getActiveProfile();
        Ingredient i = is.getFromName(ingText.toLowerCase());
        if (i != null) {
            activeProfile.getPantry().add(i);
        } else {
            JOptionPane.showMessageDialog(new JFrame(),
                    "No Ingredient Found In Pantry", "Dialog",
                    JOptionPane.ERROR_MESSAGE);
        }
        ProfileService ps = new ProfileService();
        ps.update(activeProfile, "");
        refreshContent();
    }

    private void removeIngredientFromPantryList(JList list) {
        IngredientService is = new IngredientService();
        Profile activeProfile = StateManager.getInstance().getActiveProfile();
        ProfileService ps = new ProfileService();
        //list.getSelectedValuesList();
        for (Object i : list.getSelectedValuesList()) {
            activeProfile.getPantry().remove(i);
        }
        ps.update(activeProfile, "");
        refreshContent();
    }

    private void addIngredientToPantryFromList(JList list) {
        IngredientService is = new IngredientService();
        Profile activeProfile = StateManager.getInstance().getActiveProfile();
        ProfileService ps = new ProfileService();
        //list.getSelectedValuesList();
        for (Object i : list.getSelectedValuesList()) {
            if (activeProfile.getPantry().contains((Ingredient) i)) {
                //TODO Show Pop up
            } else {
                activeProfile.getPantry().add((Ingredient) i);
            }
        }
        ps.update(activeProfile, "");
        refreshContent();
    }
}
