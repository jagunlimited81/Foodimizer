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
    private JList<Ingredient> emptyPantryList;
    private JPanel buttonPanel;
    private JPanel ingredientsPanel;
    private JPanel emptyPantryPanel;
    private JPanel searchBarPanel;
    private DefaultListModel<Ingredient> ingredientPantryModel;
    private DefaultListModel<Ingredient> emptyPantryModel;
    private JTextField searchTextField;
    private JLabel searchPrompt;
    IngredientService is = new IngredientService();
    JButton fireButton;

    public MyPantry() {
        super();
        init();
        //persistPantry();

    }

    @Override
    protected void init() {
        if (StateManager.getInstance().getActiveProfile() == null)
            return;
        this.setLayout(new BorderLayout());

        /* Button Implementation */
        JButton addBtn = new JButton("Add");
        //noinspection DuplicatedCode
        addBtn.addActionListener((ActionEvent arg0) -> {
            for (Ingredient selectedValue : ingredientPantryList.getSelectedValuesList()) {
                emptyPantryModel.addElement(selectedValue);
                ingredientPantryModel.removeElement(selectedValue);
                int iSelected = ingredientPantryList.getSelectedIndex();
                if (iSelected == -1) {
                    return;
                }
            }
        });
        this.add(addBtn);

        JButton rmvBtn = new JButton("Remove");
        //noinspection DuplicatedCode
        rmvBtn.addActionListener((ActionEvent arg0) -> {
            for (Ingredient selectedValue : emptyPantryList.getSelectedValuesList()) {
                ingredientPantryModel.addElement(selectedValue);
                emptyPantryModel.removeElement(selectedValue);
                int selected = emptyPantryList.getSelectedIndex();
                if (selected == -1) {
                    return;
                }
            }
        });
        this.add(rmvBtn);

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
        JButton searchBtn = new JButton(" Quick Add");
        searchTextField = new JTextField(35);
        searchTextField.setFont(new Font("Helvetica Neue", Font.PLAIN, 20));
        searchBarPanel.add(searchTextField);
        searchBarPanel.add(searchBtn);
        searchPrompt = new JLabel("Search Ingredient: ");
        searchPrompt.setFont(new Font("Helvetica Neue", Font.PLAIN, 20));
        searchBtn.addActionListener(e -> addToPantry(searchTextField));
        searchBtn.addActionListener(e -> addToPantry(searchTextField.getText()));
        searchBarPanel.add(searchPrompt);
        this.add(searchPrompt, BorderLayout.EAST);
        this.add(searchBarPanel, BorderLayout.NORTH);

        /* Ingredients List */
        ingredientPantryModel = new DefaultListModel<>();
        ingredientPantryList = new JList<>(ingredientPantryModel);
        ingredientsPanel = new JPanel();
        ingredientsPanel.setBackground(Color.BLUE);
//        ingredientPantryList = new JList(activeProfile.getPantry().toArray());
        ingredientPantryList = new JList(is.getAll().toArray());
        ingredientsPanel.add(ingredientPantryList);
        ingredientPantryList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        ingredientPantryList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        ingredientPantryList.setVisibleRowCount(-1);
//        activePantryList.setPreferredSize(new Dimension(500,500));
        ingredientsPanel.setBorder(BorderFactory.createTitledBorder("Ingredients List"));
        this.add(ingredientsPanel, BorderLayout.WEST);


        /* Empty Pantry Panel */
        emptyPantryModel = new DefaultListModel<>();
        emptyPantryList = new JList<>(emptyPantryModel);
        emptyPantryPanel = new JPanel();
        emptyPantryPanel.setBackground(Color.LIGHT_GRAY);
        emptyPantryPanel.add(emptyPantryList);
        emptyPantryList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        emptyPantryList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        emptyPantryList.setVisibleRowCount(-1);
        emptyPantryPanel.setBorder(BorderFactory.createTitledBorder("Pantry"));
        this.add(emptyPantryPanel, BorderLayout.EAST);

        /* Split Pane Panel */
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, emptyPantryPanel, ingredientsPanel);
        //might need to change this to ingredientsPanel once main is pull OR
        //create another listScroller for both lists.
        JScrollPane listScroller = new JScrollPane(emptyPantryPanel);
        listScroller.setPreferredSize(new Dimension(getPreferredSize()));
        JScrollPane listScroller2 = new JScrollPane(ingredientsPanel);
        listScroller2.setPreferredSize(new Dimension(getPreferredSize()));
        splitPane.setLeftComponent(new JScrollPane(ingredientsPanel));
        splitPane.setRightComponent(emptyPantryPanel);
        splitPane.setResizeWeight(0.5);
        this.add(splitPane);
    }

    private void persistPantry(){
        Ingredient ingredient = new Ingredient();
//        ingredient.setName("Food");

        Profile profile = StateManager.getInstance().getActiveProfile();

        profile.addIngredientToPantry(ingredient);
        ProfileService pService = new ProfileService();
        IngredientService iService = new IngredientService();

        iService.save(ingredient);
        pService.update(profile, "");

        profile.getPantry().add(ingredient);

        List<Ingredient> pList = new ArrayList<>(profile.getPantry());
        this.add(new JLabel(pList.get(0).toString()));
    }

//    private void valueChanged(ListSelectionEvent e){
//        if(!e.getValueIsAdjusting() == false){
//            if (ingredientPantryList.getSelectedIndex() == -1){
//                fireButton.setEnabled(false);
//            } else {
//                fireButton.setEnabled(true);
//            }
//        }
//    }

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
    private void addToPantry(JTextField ingText)
    {
        String tempSearchText = ingText.getText();
        Boolean doesIngredientExist = false;
        Ingredient ingredientToAdd;
        IngredientService iService = new IngredientService();
        Profile activeProfile = StateManager.getInstance().getActiveProfile();
        List<Ingredient> searchIngredientsList = iService.getAll();
        ProfileService ps = new ProfileService();

        ingredientToAdd = iService.getFromName(ingText.getText().toLowerCase());
        if (ingredientToAdd == null){
            JOptionPane.showMessageDialog(new JFrame(),
                    "No Ingredient Found In Pantry", "Dialog",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        activeProfile.getShoppingList().add(ingredientToAdd);
        ps.update(activeProfile, "");
        emptyPantryModel.addElement(ingredientToAdd);


//        for(Ingredient ingredient: searchIngredientsList) {
//                if(ingredient.getName().equals(tempSearchText))
//                {
//
//                    ingredientToAdd = ingredient;
//                    emptyPantryModel.addElement(ingredientToAdd);
//                    doesIngredientExist = Boolean.TRUE;
//                }
//        }
        refreshContent();
    }
}
