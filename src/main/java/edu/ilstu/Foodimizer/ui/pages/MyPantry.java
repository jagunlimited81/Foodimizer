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

public class MyPantry extends JPanel {
    private JList<Ingredient> ingredientPantryList;
    private JList<Ingredient> emptyPantryList;
    private JPanel buttonPanel;
    private JPanel ingredientsPanel;
    private JPanel emptyPantryPanel;
    private JPanel searchBarPanel;
    private DefaultListModel<Ingredient> ingredientPantryModel;
    private DefaultListModel<Ingredient> emptyPantryModel;
    private JTextField searchTextField;

    JList<Ingredient> list = new JList<>();

    JLabel label = new JLabel();
    //    JSplitPane splitPane = new JSplitPane();
    IngredientService is = new IngredientService();
    JButton fireButton;

    public MyPantry() {
        init();
        //persistPantry();

    }

    private void init() {
        /* Button Implementation */
        JButton addBtn = new JButton("Add");
//        addBtn.addActionListener(e ->persistPantry());
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
//        rmvBtn.addActionListener(e ->persistPantry());
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

//        setBackground(Color.WHITE);
        buttonPanel = new JPanel();
        this.setLayout(new BorderLayout());

        /* Button Panel */
        buttonPanel.setBackground(Color.PINK);
//        buttonPanel.setSize(500,500);
        buttonPanel.add(addBtn);
        buttonPanel.add(rmvBtn);
        this.add(buttonPanel, BorderLayout.SOUTH);

        /* Search Bar Panel */
        searchBarPanel = new JPanel();
        searchBarPanel.setBackground(Color.BLACK);
//        buttonPanel.setSize(500,500);
        JButton searchBtn = new JButton("Add");
        searchTextField = new JTextField();
        searchBarPanel.add(searchTextField);
        searchBarPanel.add(searchBtn);
        searchBtn.addActionListener(e -> addToPantry());
        this.add(searchBarPanel, BorderLayout.NORTH);

        /* Ingredients List */
        ingredientPantryModel = new DefaultListModel<>();
        ingredientPantryList = new JList<>(ingredientPantryModel);
        ingredientsPanel = new JPanel();
        ingredientsPanel.setBackground(Color.BLUE);
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
        JScrollPane listScroller = new JScrollPane(emptyPantryPanel);
        listScroller.setPreferredSize(new Dimension(getPreferredSize()));
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

    private void valueChanged(ListSelectionEvent e){
        if(!e.getValueIsAdjusting() == false){
            if (ingredientPantryList.getSelectedIndex() == -1){
                fireButton.setEnabled(false);
            } else {
                fireButton.setEnabled(true);
            }
        }
    }

    private void addToPantry()
    {
        String tempSearchText = searchTextField.getText();
        Boolean doesIngredientExist = false;
        Ingredient ingredientToAdd = new Ingredient();
        IngredientService iService = new IngredientService();
        Profile activeProfile = StateManager.getInstance().getActiveProfile();
        List<Ingredient> searchIngredientsList = iService.getAll();

        for(Ingredient ingredient: searchIngredientsList)
        {
            if(ingredient.getName().equals(tempSearchText))
            {
                ingredientToAdd = ingredient;
                doesIngredientExist = Boolean.TRUE;
            }
        }
        activeProfile.getShoppingList().add(ingredientToAdd);
//        refreshContent();
    }

//    private void addToPantry(){
//
//    }

//    public void removeFromPantry(ActionEvent e) {
//        int index = list.getSelectedIndex();
//        activePantryList.remove(index);
//
//        int size = activePantryList.getSelectedIndex();
//
//        if (size == 0) { //Nobody's left, disable firing.
//            fireButton.setEnabled(false);
//
//        } else { //Select an index.
//            if (index == activePantryList.getSelectedIndex()) {
//                //removed item in last position
//                index--;
//            }
//
//            list.setSelectedIndex(index);
//            list.ensureIndexIsVisible(index);
//        }
//    }
//
//    public void addToPantry(ActionEvent e) {
//        Ingredient ingredient = new Ingredient();
//        String name = ingredient.getName();
//
//        //User did not type in a unique name...
//        if (name.equals("") || alreadyInList(name)) {
//            Toolkit.getDefaultToolkit().beep();
//            activePantryList.requestFocusInWindow();
//            employeeName.selectAll();
//            return;
//        }
//
//        int index = list.getSelectedIndex(); //get selected index
//        if (index == -1) { //no selection, so insert at beginning
//            index = 0;
//        } else {           //add after the selected item
//            index++;
//        }
//
//        listModel.insertElementAt(employeeName.getText(), index);
//
//        //Reset the text field.
//        employeeName.requestFocusInWindow();
//        employeeName.setText("");
//
//        //Select the new item and make it visible.
//        list.setSelectedIndex(index);
//        list.ensureIndexIsVisible(index);
//    }
}
