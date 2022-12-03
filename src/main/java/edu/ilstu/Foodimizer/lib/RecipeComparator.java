package edu.ilstu.Foodimizer.lib;

import edu.ilstu.Foodimizer.app.StateManager;
import edu.ilstu.Foodimizer.app.db.models.JoinProfileRateRecipe;
import edu.ilstu.Foodimizer.app.db.models.Recipe;
import edu.ilstu.Foodimizer.app.db.service.RatingService;

import java.util.Comparator;

public class RecipeComparator {
    RatingService rs;

    public RecipeComparator(RatingService rs) {
        this.rs = rs;
    }

    public static Comparator<Recipe> getAZComparator() {
        return new Comparator<Recipe>() {
            @Override
            public int compare(Recipe o1, Recipe o2) {

                return o1.getName().compareToIgnoreCase(o2.getName());
            }
        };
    }

    public static Comparator<Recipe> getRatingComparator(RatingService rs) {
        return new Comparator<Recipe>() {
            @Override
            public int compare(Recipe o1, Recipe o2) {
                byte o1rate = -1;
                byte o2rate = -1;
                JoinProfileRateRecipe o1r = rs.getRatingObj(StateManager.getInstance().getActiveProfile(), o1);
                if (o1r != null)
                    o1rate = o1r.getRating();
                JoinProfileRateRecipe o2r = rs.getRatingObj(StateManager.getInstance().getActiveProfile(), o2);
                if (o2r != null)
                    o2rate = o2r.getRating();

                return Byte.compare(o2rate, o1rate);
            }
        };
    }
}
