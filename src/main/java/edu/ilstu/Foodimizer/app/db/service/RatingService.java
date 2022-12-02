package edu.ilstu.Foodimizer.app.db.service;

import edu.ilstu.Foodimizer.app.db.models.JoinProfileRateRecipe;
import edu.ilstu.Foodimizer.app.db.models.Profile;
import edu.ilstu.Foodimizer.app.db.models.Recipe;

import java.util.List;

public class RatingService extends Service<JoinProfileRateRecipe> {
    public List<JoinProfileRateRecipe> getAll() {
        return super.getAll(JoinProfileRateRecipe.class);
    }
}
