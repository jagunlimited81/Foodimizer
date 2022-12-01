package edu.ilstu.Foodimizer.ui.pages;

import edu.ilstu.Foodimizer.app.db.models.Ingredient;
import edu.ilstu.Foodimizer.app.db.models.Profile;

import edu.ilstu.Foodimizer.app.StateManager;
import edu.ilstu.Foodimizer.app.db.models.Ingredient;
import edu.ilstu.Foodimizer.app.db.service.IngredientService;
import edu.ilstu.Foodimizer.app.db.service.ProfileService;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MyPantry extends JPanel {
    private JList<Ingredient> activePantry;
    private JPanel contentPane;
    private JPanel panel;

    JList<Ingredient> list = new JList<>();
    DefaultListModel<Ingredient> model = new DefaultListModel<>();
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
        contentPane = new JPanel();
        this.setLayout(new BorderLayout());



        /* Pantry Items Panel */
        contentPane.setBackground(Color.PINK);
//        this.add(contentPane, BorderLayout.CENTER);
        contentPane.add(addBtn);
        contentPane.add(rmvBtn);

        /* Pantry Search Bar */
        panel = new JPanel();
        panel.setBackground(Color.BLUE);
        activePantry = new JList(is.getAll().toArray());
        panel.add(activePantry);
        activePantry.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        activePantry.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        activePantry.setVisibleRowCount(-1);
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,activePantry,contentPane);
//        JScrollPane listScroller = new JScrollPane(activePantry);
//        listScroller.setPreferredSize(new Dimension(getPreferredSize()));
        this.add(panel, BorderLayout.EAST);
        splitPane.setLeftComponent(new JScrollPane(activePantry));
        splitPane.setRightComponent(contentPane);
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
