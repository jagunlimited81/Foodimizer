package edu.ilstu.Foodimizer.ui.pages;

import edu.ilstu.Foodimizer.app.db.models.Ingredient;
import edu.ilstu.Foodimizer.app.db.service.IngredientService;
import edu.ilstu.Foodimizer.app.StateManager;
import edu.ilstu.Foodimizer.app.db.models.Profile;


import javax.swing.*;
import java.awt.*;
import java.util.List;


public class ShoppingList extends Page {
    public ShoppingList() {
        init();

    }
    public void init() {
        Profile activeProfile = StateManager.getInstance().getActiveProfile();
        if(activeProfile == null)
            return;

        // Create 2 Panels for Shopping List and Buttons to Clear and Save to PDF
        JPanel contentPanel = new JPanel(new BorderLayout());
        JPanel shopListPanel = new JPanel(new BorderLayout());
        JPanel buttonPanel = new JPanel(new BorderLayout());
        JPanel addIngredientPanel = new JPanel(new BorderLayout());

        //Create Buttons to be able to trigger the clearing of the list, and saving the List to PDF
        JButton removeIngButton = new JButton("Remove Ingredient from the Shopping List");
        JButton clearListButton = new JButton("Clear the Shopping List");
        clearListButton.addActionListener(e->clearShoppingList());

        //Button for printing the Shopping List to PDF
        JButton pdfButton = new JButton("Print Shopping List to PDF");
        //pdfButton.addActionListener(e->saveToPDF());

        // Panel for storing the add ingredient button
        // create a label to display text
        // create the add button
        JButton addIngButton = new JButton("Add Ingredient");
        JTextField ingText = new JTextField();
        addIngButton.addActionListener(e->addIngredientToShoppingList(ingText));

        JLabel ingSearchLabel = new JLabel("Add Ingredient to Shopping List: ");
        addIngredientPanel.add(ingText,BorderLayout.PAGE_START);
        //addIngredientPanel.add(ingSearchLabel,BorderLayout.PAGE_START);
        addIngredientPanel.add(addIngButton);
        addIngredientPanel.setPreferredSize(new Dimension(250,500));


//      Create a JList to display the array
//      Import ingredients over into the list;
        groceryList = new JList(activeProfile.getShoppingList().toArray());
        groceryList.setBorder(BorderFactory.createTitledBorder("Foodimizer Grocery List"));
        groceryList.setPreferredSize(new Dimension(500,500));

//      //Add the Shopping list Panel to side of the content Panel
        shopListPanel.add(groceryList);

//         Add the buttons to the right side panel
//        buttonPanel.add(addIngredientPanel);
        buttonPanel.add(removeIngButton);
        buttonPanel.add(clearListButton, BorderLayout.PAGE_START);
        buttonPanel.add(pdfButton,BorderLayout.PAGE_END);

        contentPanel.add(shopListPanel,BorderLayout.WEST);
        contentPanel.add(addIngredientPanel);
        contentPanel.add(buttonPanel,BorderLayout.SOUTH);
        add(contentPanel);

    }

    //List of ingredients imported to the shopping list from other pages
    private JList groceryList;

    private void removeIngredientFromShoppingList(Ingredient ingToRemove)
    {
        Profile activeProfile = StateManager.getInstance().getActiveProfile();
        activeProfile.getShoppingList().remove(ingToRemove);
        refreshContent();
    }

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