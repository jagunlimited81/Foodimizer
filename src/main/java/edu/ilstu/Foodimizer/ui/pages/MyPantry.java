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
    private JList<Ingredient> activePantry;
    private JPanel buttonPanel;
    private JPanel ingredientsPanel;

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
        JButton addBtn = new JButton("Add");
        addBtn.addActionListener(e ->persistPantry());
        this.add(addBtn);
        JButton rmvBtn = new JButton("Remove");
        rmvBtn.addActionListener(e ->persistPantry());
        this.add(rmvBtn);
//        setBackground(Color.WHITE);
        buttonPanel = new JPanel();
        this.setLayout(new BorderLayout());



        /* Pantry Items Panel */
        buttonPanel.setBackground(Color.PINK);
        //buttonPanel.setPreferredSize(getPreferredSize());
//        this.add(contentPane, BorderLayout.CENTER);
        buttonPanel.add(addBtn);
        buttonPanel.add(rmvBtn);

        /* Pantry Search Bar */
        ingredientsPanel = new JPanel();
        ingredientsPanel.setBackground(Color.BLUE);
        activePantry = new JList(is.getAll().toArray());
        ingredientsPanel.add(activePantry);
        activePantry.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        activePantry.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        activePantry.setVisibleRowCount(-1);
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,activePantry, buttonPanel);
//        JScrollPane listScroller = new JScrollPane(activePantry);
//        listScroller.setPreferredSize(new Dimension(getPreferredSize()));
        ;
        this.add(ingredientsPanel, BorderLayout.EAST);
        splitPane.setLeftComponent(new JScrollPane(activePantry));
        splitPane.setRightComponent(buttonPanel);
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
            if (activePantry.getSelectedIndex() == -1){
                fireButton.setEnabled(false);
            } else {
                fireButton.setEnabled(true);
            }
        }
    }

    private void addToPantry(){

    }


}
