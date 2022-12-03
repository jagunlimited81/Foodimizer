package edu.ilstu.Foodimizer.ui.pages;

import edu.ilstu.Foodimizer.app.StateManager;
import edu.ilstu.Foodimizer.app.db.models.Ingredient;
import edu.ilstu.Foodimizer.app.db.models.Profile;
import edu.ilstu.Foodimizer.app.db.service.IngredientService;
import edu.ilstu.Foodimizer.app.db.service.ProfileService;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class MyPantry extends Page {
    private JList<Ingredient> ingredientPantryList;
    private JPanel buttonPanel;
    private JPanel ingredientsPanel;
    private JPanel emptyPantryPanel;
    private JPanel searchBarPanel;
    private DefaultListModel<Ingredient> ingredientPantryModel;
    private DefaultListModel<Ingredient> emptyPantryModel;
    private JTextField searchTextField;
    private JLabel searchPrompt;
    IngredientService is = new IngredientService();

    public MyPantry() {
        super();
        init();
    }

    @Override
    protected void init() {
        if (StateManager.getInstance().getActiveProfile() == null)
            return;
        this.setLayout(new BorderLayout());

        ArrayList<Ingredient> ingredients = new ArrayList<>(StateManager.getInstance().getActiveProfile().getPantry());
        JList pantry = new JList<>(ingredients.toArray());

        /* Button Implementation */
        JButton addBtn = new JButton("Add");
        //noinspection DuplicatedCode
        addBtn.addActionListener(e->addIngredientToPantryFromList(ingredientPantryList));

        JButton rmvBtn = new JButton("Remove");
        //noinspection DuplicatedCode
        rmvBtn.addActionListener(e->removeIngredientFromPantryList(pantry));

        /* Buttons Panel */
        buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.PINK);
//        buttonPanel.setSize(500,500);
        buttonPanel.add(addBtn);
        buttonPanel.add(rmvBtn);
        this.add(buttonPanel, BorderLayout.SOUTH);

        /* Search Bar Panel */
        searchBarPanel = new JPanel();
        searchBarPanel.setBackground(Color.WHITE);
//        buttonPanel.setSize(500,500);
        JButton searchBtn = new JButton("Quick Add");
        searchTextField = new JTextField(35);
        searchTextField.setFont(new Font("Helvetica Neue", Font.PLAIN, 20));
        searchBarPanel.add(searchTextField);
        searchBarPanel.add(searchBtn);
        searchPrompt = new JLabel("Search Ingredient: ");
        searchPrompt.setFont(new Font("Helvetica Neue", Font.PLAIN, 20));
        searchBtn.addActionListener(e -> addToPantry(searchTextField.getText()));
        searchBarPanel.add(searchPrompt);
//        this.add(searchPrompt);
        this.add(searchBarPanel, BorderLayout.NORTH);

        /* Ingredients List */
        ingredientsPanel = new JPanel();
        ingredientsPanel.setBackground(Color.BLUE);
//        ingredientPantryList = new JList(activeProfile.getPantry().toArray());
        ArrayList<Ingredient> availableIngredients;
        availableIngredients = new ArrayList<>(is.getAll());
        availableIngredients.removeAll(StateManager.getInstance().getActiveProfile().getPantry());
        ingredientPantryList = new JList(availableIngredients.toArray());
        ingredientsPanel.add(ingredientPantryList);
        ingredientPantryList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        ingredientPantryList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        ingredientPantryList.setVisibleRowCount(-1);
//        activePantryList.setPreferredSize(new Dimension(500,500));
        ingredientsPanel.setBorder(BorderFactory.createTitledBorder("Ingredients List"));
        this.add(ingredientsPanel, BorderLayout.WEST);


        /* Empty Pantry Panel */
        emptyPantryPanel = new JPanel();
        emptyPantryPanel.add(pantry);
        emptyPantryPanel.setBorder(BorderFactory.createTitledBorder("Pantry"));
        this.add(emptyPantryPanel, BorderLayout.EAST);

        /* Split Pane Panel */
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, emptyPantryPanel, ingredientsPanel);
        //might need to change this to ingredientsPanel once main is pull OR
        //create another listScroller for both lists.
        JScrollPane listScroller = new JScrollPane(ingredientsPanel);
        listScroller.setPreferredSize(new Dimension(getPreferredSize()));
        splitPane.setLeftComponent(new JScrollPane(ingredientsPanel));
        splitPane.setRightComponent(emptyPantryPanel);
        splitPane.setResizeWeight(0.5);
        this.add(splitPane);
    }

    private void addToPantry(String ingText){
        IngredientService is = new IngredientService();
        Profile activeProfile = StateManager.getInstance().getActiveProfile();
        Ingredient i = is.getFromName(ingText.toLowerCase());
        if (i != null) {
            activeProfile.getPantry().add(i);
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
            if (activeProfile.getPantry().contains((Ingredient) i)){
                //TODO Show Pop up
            } else {
                activeProfile.getPantry().add((Ingredient) i);
            }
        }
        ps.update(activeProfile, "");
        refreshContent();
    }
}
