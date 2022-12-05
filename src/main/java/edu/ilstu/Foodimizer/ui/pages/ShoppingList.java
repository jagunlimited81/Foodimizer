package edu.ilstu.Foodimizer.ui.pages;

import edu.ilstu.Foodimizer.app.StateManager;
import edu.ilstu.Foodimizer.app.db.models.Ingredient;
import edu.ilstu.Foodimizer.app.db.models.Profile;
import edu.ilstu.Foodimizer.app.db.service.IngredientService;
import edu.ilstu.Foodimizer.app.db.service.ProfileService;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ShoppingList extends Page {

    public ShoppingList() {
        super();
        init();
    }

    @Override
    protected void init() {
        if (StateManager.getInstance().getActiveProfile() == null)
            return;
        contentPanel = new JPanel();
        GroupLayout layout = new GroupLayout(contentPanel);
        contentPanel.setLayout(layout);
//        layout.setAutoCreateGaps(true);
//        layout.setAutoCreateContainerGaps(true);

        ArrayList<Ingredient> ingredients = new ArrayList<>(StateManager.getInstance().getActiveProfile().getShoppingList());
        JList shoppingList = new JList<>(ingredients.toArray());

        JLabel title = new JLabel("My Shopping List");
        title.setFont(new Font("Verdana", Font.PLAIN, 22));

        //Create Buttons to be able to trigger the clearing of the list, and saving the List to PDF
        JButton removeIngButton = new JButton("Remove selected Ingredient(s) from the Shopping List");
        removeIngButton.addActionListener(e -> removeIngredientFromShoppingList(shoppingList));
        JButton clearListButton = new JButton("Clear the Shopping List");
        clearListButton.addActionListener(e -> clearShoppingList());
        JButton pdfButton = new JButton("Print Shopping List to PDF");
//      pdfButton.addActionListener(e->saveToPDF());
        JButton addIngButton = new JButton("Add Ingredient");
        JTextField addIngText = new JTextField();
        addIngButton.addActionListener(e -> addIngredientToShoppingList(addIngText.getText()));

        //Layout for the full Page
        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(title)
                                .addComponent(shoppingList))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(addIngText)
                                .addComponent(addIngButton)
                                .addComponent(removeIngButton)
                                .addComponent(clearListButton)
                                .addComponent(pdfButton))
        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(title))
                        .addComponent(shoppingList)
                        .addComponent(addIngText)
                        .addComponent(addIngButton)
                        .addComponent(removeIngButton)
                        .addComponent(clearListButton)
                        .addComponent(pdfButton)

        );
        this.add(contentPanel);
    }

    //JPanel
    JPanel contentPanel;


    private void clearShoppingList() {
        Profile activeProfile = StateManager.getInstance().getActiveProfile();
        activeProfile.getShoppingList().clear();
        ProfileService ps = new ProfileService();
        ps.update(activeProfile, "");
        refreshContent();
    }


    private void addIngredientToShoppingList(String ingText) {
        IngredientService is = new IngredientService();
        Profile activeProfile = StateManager.getInstance().getActiveProfile();
        Ingredient i = is.getFromName(ingText.toLowerCase());
        if (i != null) {
            if (activeProfile.getShoppingList().contains(i))
            {
                JOptionPane.showMessageDialog(new JFrame(),
                        "Ingredient is already in the Shopping List!", "Dialog",JOptionPane.ERROR_MESSAGE);
            }

            else
            {
                activeProfile.getShoppingList().add(i);
            }
        }

        if (i == null)
        {
            JOptionPane.showMessageDialog(new JFrame(),
                    "No Ingredient Found!", "Dialog",JOptionPane.ERROR_MESSAGE);
        }


        ProfileService ps = new ProfileService();
        ps.update(activeProfile, "");
        refreshContent();
    }

    private void removeIngredientFromShoppingList(JList list) {
        IngredientService is = new IngredientService();
        Profile activeProfile = StateManager.getInstance().getActiveProfile();
        ProfileService ps = new ProfileService();
//        if(list.getSelectedValuesList().equals(null))
//        {
//            JOptionPane.showMessageDialog(new JFrame(),
//                    "No Ingredients Selected!", "Dialog",JOptionPane.ERROR_MESSAGE);
//        }

        for (Object i : list.getSelectedValuesList()) {
            activeProfile.getShoppingList().remove(i);
        }
        ps.update(activeProfile, "");
        refreshContent();
    }

}
