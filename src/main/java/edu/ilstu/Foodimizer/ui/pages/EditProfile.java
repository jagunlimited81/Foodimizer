package edu.ilstu.Foodimizer.ui.pages;

import edu.ilstu.Foodimizer.app.StateManager;
import edu.ilstu.Foodimizer.app.db.models.Profile;
import edu.ilstu.Foodimizer.app.db.service.ProfileService;
import edu.ilstu.Foodimizer.lib.ByteTools;
import edu.ilstu.Foodimizer.ui.MainWindowContentManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class EditProfile extends Page{
    public EditProfile() {
        super();
        init();
    }

    public void init() {
        StateManager sm = StateManager.getInstance();
        if (sm.getActiveProfile() == null)
            return;
        nameInfoStr = new JLabel("Name:");
        name = new JTextField(sm.getActiveProfile().getName());
        saveButton = new JButton("Save Profile");
        saveButton.addActionListener(e -> editProfile());
        deleteButton = new JButton("Delete Profile");
        deleteButton.addActionListener(e -> deleteProfile());
        pfpChoiceInfoStr = new JLabel("Profile Picture Color:");
        pfpChoice = new JComboBox<>(pfps);
        this.add(nameInfoStr);
        this.add(name);
        this.add(pfpChoiceInfoStr);
        this.add(pfpChoice);
        this.add(saveButton);
        this.add(deleteButton);
    }

    private void deleteProfile() {
        MainWindowContentManager mwcm = MainWindowContentManager.getInstance();
        ProfileService ps = new ProfileService();
        StateManager sm = StateManager.getInstance();
        Profile profile = sm.getActiveProfile();
        ps.delete(profile);
        sm.setActiveProfile(null);
        if (ps.getAll().isEmpty())
            mwcm.goToPage("CreateProfile");
        else
            mwcm.goToPage("ProfileSelector");
    }

    private void editProfile() {
        ProfileService ps = new ProfileService();
        StateManager sm = StateManager.getInstance();
        Profile profile = sm.getActiveProfile();
        BufferedImage image;
        profile.setName(name.getText());

        try {
            image = ImageIO.read(this.getClass().getResource("/images/nopfp" + pfpChoice.getSelectedIndex() + ".png"));
            byte[] bytes = ByteTools.toByteArray(image, "png");
            profile.setProfilePic(bytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ps.update(profile, "");
        MainWindowContentManager mwcm = MainWindowContentManager.getInstance();
        mwcm.goToPage("ProfileSelector");
    }
    public void refreshContent() {
        this.removeAll();
        init();
    }

    JPanel contentPane;
    JPanel pfp;
    JLabel nameInfoStr;
    JTextField name;
    JButton saveButton;
    JLabel pfpChoiceInfoStr;
    JComboBox<String> pfpChoice;
    JButton deleteButton;

    String[] pfps = new String[]{"Black", "Blue", "Green", "Red" };

}
