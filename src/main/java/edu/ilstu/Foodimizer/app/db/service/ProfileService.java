package edu.ilstu.Foodimizer.app.db.service;

import edu.ilstu.Foodimizer.app.db.models.JoinProfileRateRecipe;
import edu.ilstu.Foodimizer.app.db.models.Profile;

import java.util.List;

public class ProfileService extends Service<Profile> {


    public List<Profile> getAll() {
        return super.getAll(Profile.class);
    }

    @Override
    public void delete(Profile profile) {
        RatingService rs = new RatingService();
        for (JoinProfileRateRecipe jprr : profile.getRecipeRatings()) {
            rs.delete(jprr);
        }
        super.delete(profile);
    }
}
