package edu.ilstu.Foodimizer.ui.pages;

import edu.ilstu.Foodimizer.app.db.models.Ingredient;
import edu.ilstu.Foodimizer.app.db.models.Profile;

import javax.swing.*;
import java.awt.*;

public class MyPantry extends JPanel {
    public MyPantry() {
        init();
        persistPantry();
    }

    private void init() {
        setBackground(Color.RED);
    }
    private void persistPantry(){
        Ingredient ingredient = new Ingredient();
        Profile profile = new Profile();

        ingredient.getName();
        profile.getProfileId();
    }
}
