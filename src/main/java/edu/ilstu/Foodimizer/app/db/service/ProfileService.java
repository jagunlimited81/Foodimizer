package edu.ilstu.Foodimizer.app.db.service;

import edu.ilstu.Foodimizer.app.db.models.Profile;

import java.util.List;

public class ProfileService extends Service<Profile> {


    public List<Profile> getAll() {
        return super.getAll(Profile.class);
    }
}
