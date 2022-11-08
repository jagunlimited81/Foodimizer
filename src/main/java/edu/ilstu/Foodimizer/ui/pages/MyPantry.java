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
    public MyPantry() {
        init();
        //persistPantry();
    }

    private void init() {
//        JButton jb = new JButton("testing");
//        jb.addActionListener(e ->persistPantry());
//        this.add(jb);
        setBackground(Color.WHITE);
        contentPane = new JPanel();
//        ingredientsListPanel = new BorderLayout();
//        ingredientsList = new JList(activePantry);
//        ingredientsList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
//        ingredientsList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
//        ingredientsList.setVisibleRowCount(-1);
//        JScrollPane listScroller = new JScrollPane(ingredientsList);
//        listScroller.setPreferredSize(new Dimension(250,80));
    }
    private void persistPantry(){
        Ingredient ingredient = new Ingredient();
       // ingredient.setName("Food");

        Profile profile = StateManager.getInstance().getActiveProfile();

        profile.addIngredientToPantry(ingredient);
        ProfileService pService = new ProfileService();
        IngredientService iService = new IngredientService();

        iService.save(ingredient);
        pService.update(profile, "");

        List<Ingredient> pList = new ArrayList<>(profile.getPantry());
        this.add(new JLabel(pList.get(0).toString()));
    }

    private void valueChanged(ListSelectionEvent e){
//        if(e.getValueIsAdjusting() == false){
//            if (ingredientsList.getSelectedIndex() == -1){
//                fireButton.setEnabled(false);
//            } else {
//                fireButton.setEnabled(true);
//            }
//        }
    }


    private Ingredient[] activePantry;
    private JPanel contentPane;
    private BorderLayout ingredientsListPanel;

    private JList ingredientsList;

}
