package edu.ilstu.Foodimizer.ui.pages;

import edu.ilstu.Foodimizer.lib.Profile;
import edu.ilstu.Foodimizer.ui.MainWindowContentManager;

import javax.swing.*;

public class CreateOrEditProfile extends JPanel{
    public CreateOrEditProfile() {
        init();
    }

    private void init() {
    }

    public void setModeCreate() {

    }

    public void setModeEdit(Profile profile) {
        //Retrieve profile data

    }

    public static CreateOrEditProfile getInstance() {
        if (instance == null)
            instance = new CreateOrEditProfile();
        return instance;
    }

    private static CreateOrEditProfile instance = null;
    JPanel contentPane;
    JPanel pfp;

}
