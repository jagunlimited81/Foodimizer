package edu.ilstu.Foodimizer.ui.pages;

import edu.ilstu.Foodimizer.app.StateManager;
import edu.ilstu.Foodimizer.app.db.models.Ingredient;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

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

        ArrayList<Ingredient> ingredients = new ArrayList<>(StateManager.getInstance().getActiveProfile().getShoppingList());

        JList<Object> shoppingList = new JList<>( ingredients.toArray());
        JLabel title = new JLabel("My Shopping List");
        title.setFont(new Font("Verdana", Font.PLAIN, 22));

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(title)
                                .addComponent(shoppingList))
        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addComponent(title)
                        .addComponent(shoppingList)

        );
        this.add(contentPanel);

    }
    JPanel contentPanel;
}
