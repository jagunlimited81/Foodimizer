package edu.ilstu.Foodimizer.ui.pages;

import edu.ilstu.Foodimizer.app.db.models.Ingredient;
import edu.ilstu.Foodimizer.app.db.models.Profile;

import edu.ilstu.Foodimizer.app.StateManager;
import edu.ilstu.Foodimizer.app.db.service.IngredientService;
import edu.ilstu.Foodimizer.app.db.service.ProfileService;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MyPantry extends JPanel {
    private JList<Ingredient> activePantryList;
    private JPanel buttonPanel;
    private JPanel ingredientsPanel;

    private JPanel activePantry;

    DefaultListModel<Ingredient> model = new DefaultListModel<>();

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
        addBtn.addActionListener(e ->persistPantry());
        this.add(addBtn);

        JButton rmvBtn = new JButton("Remove");
        rmvBtn.addActionListener(e ->persistPantry());
        this.add(rmvBtn, BorderLayout.SOUTH);

//        setBackground(Color.WHITE);
        buttonPanel = new JPanel();
        this.setLayout(new BorderLayout());



        /* Button Panel */
        buttonPanel.setBackground(Color.PINK);
//        buttonPanel.setSize(500,500);
        buttonPanel.add(addBtn);
        buttonPanel.add(rmvBtn);
        this.add(buttonPanel, BorderLayout.SOUTH);

        /* Pantry Search Bar */
        ingredientsPanel = new JPanel();
        ingredientsPanel.setBackground(Color.BLUE);
        activePantryList = new JList(is.getAll().toArray());
        ingredientsPanel.add(activePantryList);
        activePantryList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        activePantryList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        activePantryList.setVisibleRowCount(-1);
        this.add(ingredientsPanel, BorderLayout.WEST);
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,activePantry, ingredientsPanel);
        JScrollPane listScroller = new JScrollPane(activePantry);
        listScroller.setPreferredSize(new Dimension(getPreferredSize()));
        splitPane.setLeftComponent(new JScrollPane(ingredientsPanel));
        splitPane.setRightComponent(activePantry);
        splitPane.setResizeWeight(0.5);
        this.add(splitPane);

        /* Active Pantry Panel */
        activePantry = new JPanel();
        activePantry.setBackground(Color.LIGHT_GRAY);
        this.add(activePantry, BorderLayout.EAST);
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
            if (activePantryList.getSelectedIndex() == -1){
                fireButton.setEnabled(false);
            } else {
                fireButton.setEnabled(true);
            }
        }
    }

    private void addToPantry(){

    }


}
