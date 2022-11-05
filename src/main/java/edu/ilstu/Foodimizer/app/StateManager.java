package edu.ilstu.Foodimizer.app;

import edu.ilstu.Foodimizer.app.db.models.Profile;
import edu.ilstu.Foodimizer.ui.jcomponents.AppBar;


public class StateManager {
    private static StateManager instance = null;
    private Profile activeProfile;
    /*components which update*/
    private AppBar appBar;

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
        appBar.getProfileCornerMenu().updateProfile(profile);
    }

    /**
     * Get active user as a profile object
     *
     * @return the active user profile
     */

    public Profile getActiveProfile() {
        return activeProfile;
    }

    /*Pass components to the StateManager*/
    /*So that StateManager can call their update methods later*/
    public void setAppBar(AppBar ab)
    {
        appBar = ab;
    }
}
