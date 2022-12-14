package edu.ilstu.Foodimizer.ui.jcomponents;

import edu.ilstu.Foodimizer.app.StateManager;
import edu.ilstu.Foodimizer.app.db.models.JoinProfileRateRecipe;
import edu.ilstu.Foodimizer.app.db.models.Profile;
import edu.ilstu.Foodimizer.app.db.models.Recipe;
import edu.ilstu.Foodimizer.app.db.service.RatingService;
import edu.ilstu.Foodimizer.ui.pages.RecipePage;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class StarRating extends JPanel {

    final static byte starNum = 5;
    byte rating = -1;
    Recipe recipe;
    ArrayList<JButton> stars = new ArrayList<>();

    public StarRating() {
        recipe = RecipePage.getInstance().getActiveRecipe();
        init();
    }

    public StarRating(Recipe recipe) {
        this.recipe = recipe;
        init();
    }

    private void init() {
        if (StateManager.getInstance().getActiveProfile() == null)
            return;
        // Create stars
        Profile profile = StateManager.getInstance().getActiveProfile();
        RatingService rs = new RatingService();
        JoinProfileRateRecipe jprr = rs.getRatingObj(profile, recipe);
        if (jprr != null)
            rating = jprr.getRating();

        for (byte i = 0; i < starNum; i++) {
            byte finalI = i;
            Star star = new Star();
            star.addActionListener(e -> starActionPerformed(finalI));

            star.setSelected(i <= rating);
            stars.add(star);
            add(star);
        }

        // Create a 5-star grid layout
        setLayout(new java.awt.GridLayout(1, 5));
        setMinimumSize(new Dimension(130, 30));
        setPreferredSize(new Dimension(130, 30));
        setMaximumSize(new Dimension(130, 30));
    }

    private void starActionPerformed(byte starSelection) {
        rating = starSelection;
        for (int i = 0; i < starNum; i++) {
            stars.get(i).setSelected(i <= starSelection);
        }
        Profile profile = StateManager.getInstance().getActiveProfile();
        RatingService rs = new RatingService();
        JoinProfileRateRecipe jprr = rs.getRatingObj(profile, recipe);
        if (jprr == null) {
            jprr = new JoinProfileRateRecipe();
            System.out.println("created new entity");
            jprr.setRecipe(recipe);
            jprr.setProfile(profile);
            jprr.setRating(rating);
            profile.getRecipeRatings().add(jprr);
            rs.save(jprr);
        } else {
            jprr.setRating(rating);
            rs.update(jprr, "");
        }

    }
}
