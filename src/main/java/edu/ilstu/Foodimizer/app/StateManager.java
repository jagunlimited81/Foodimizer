package edu.ilstu.Foodimizer.app;

import edu.ilstu.Foodimizer.app.db.models.Profile;


public class StateManager {
    private static StateManager instance = null;
    private Profile activeProfile;


    public StateManager() {
        init();
    }

    private void init() {
    }

    public static StateManager getInstance() {
        if (instance == null)
            instance = new StateManager();
        return instance;
    }

    public void setActiveProfile(Profile profile) {
        this.activeProfile = profile;
    }

    /**
     * Get active user as a profile object
     *
     * @return the active user profile
     */

    public Profile getActiveProfile() {
        return activeProfile;
    }


}
