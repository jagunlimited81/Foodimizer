package edu.ilstu.Foodimizer.ui.pages;

import edu.ilstu.Foodimizer.app.StateManager;
import edu.ilstu.Foodimizer.app.db.models.Ingredient;
import edu.ilstu.Foodimizer.app.db.models.Profile;
import edu.ilstu.Foodimizer.app.db.service.IngredientService;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ShoppingList extends Page {

    public ShoppingList() {
        super();
        init();
    }

    @Override
    protected void init() { //TODO implement everyhing else
        if (StateManager.getInstance().getActiveProfile() == null)
            return;
        contentPanel = new JPanel();
        GroupLayout layout = new GroupLayout(contentPanel);
        contentPanel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        ArrayList<Ingredient> ingredients = new ArrayList<>(StateManager.getInstance().getActiveProfile().getShoppingList());
        JList<Object> shoppingList = new JList<>( ingredients.toArray());

        JLabel title = new JLabel("My Shopping List");
        title.setFont(new Font("Verdana", Font.PLAIN, 22));

        //Create Buttons to be able to trigger the clearing of the list, and saving the List to PDF
        JButton removeIngButton = new JButton("Remove Ingredient from the Shopping List");
        JButton clearListButton = new JButton("Clear the Shopping List");
        clearListButton.addActionListener(e->clearShoppingList());
        JButton pdfButton = new JButton("Print Shopping List to PDF");
//        pdfButton.addActionListener(e->saveToPDF());
        JButton addIngButton = new JButton("Add Ingredient");
        JTextField addIngText = new JTextField();
        addIngButton.addActionListener(e->addIngredientToShoppingList(addIngText));


        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(shoppingList)
                                .addComponent(title))
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
                                .addComponent(shoppingList)
                                .addComponent(title))
                        .addComponent(addIngText)
                        .addComponent(addIngButton)
                        .addComponent(removeIngButton)
                        .addComponent(clearListButton)
                        .addComponent(pdfButton)

        );
        this.add(contentPanel);

    }

    //JPanels
    JPanel contentPanel;

    private void clearShoppingList()
    {
        Profile activeProfile = StateManager.getInstance().getActiveProfile();
        activeProfile.getShoppingList().clear();
        refreshContent();
    }


    private void addIngredientToShoppingList(JTextField ingText)
    {
        String tempIngText = ingText.getText();
        Boolean doesIngExist = false;
        Ingredient ingToAdd = new Ingredient();
        IngredientService ingService = new IngredientService();
        Profile activeProfile = StateManager.getInstance().getActiveProfile();
        List<Ingredient> searchIngredientsList = ingService.getAll();

        for(Ingredient ing: searchIngredientsList)
        {
            if(ing.getName().equals(tempIngText))
            {
                ingToAdd = ing;
                doesIngExist = Boolean.TRUE;
            }
        }
        activeProfile.getShoppingList().add(ingToAdd);
        refreshContent();

    }

}