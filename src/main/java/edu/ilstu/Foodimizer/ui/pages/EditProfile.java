package edu.ilstu.Foodimizer.ui.pages;

import edu.ilstu.Foodimizer.app.StateManager;
import edu.ilstu.Foodimizer.app.db.models.Profile;
import edu.ilstu.Foodimizer.app.db.service.ProfileService;
import edu.ilstu.Foodimizer.lib.ByteTools;
import edu.ilstu.Foodimizer.lib.InputValidator;
import edu.ilstu.Foodimizer.ui.MainWindowContentManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class EditProfile extends Page {
    public EditProfile() {
        super();
        init();
    }

    public void init() {
        contentPanel = new JPanel();
        StateManager sm = StateManager.getInstance();
        if (sm.getActiveProfile() == null)
            return;
        header = new JLabel("Editing profile " + sm.getActiveProfile().getName());
        nameInfoStr = new JLabel("Name:");
        name = new JTextField(sm.getActiveProfile().getName());
        saveButton = new JButton("Save Profile");
        saveButton.addActionListener(e -> editProfile());
        deleteButton = new JButton("Delete Profile");
        deleteButton.addActionListener(e -> deleteProfile());
        pfpChoiceInfoStr = new JLabel("Profile Picture Color:");
        pfpChoice = new JComboBox<>(pfps);
        /* Layout */
        GroupLayout layout = new GroupLayout(contentPanel);
        contentPanel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup()
                                .addComponent(header)
                                .addComponent(nameInfoStr)
                                .addComponent(pfpChoiceInfoStr)
                                .addComponent(saveButton))
                        .addGroup(layout.createParallelGroup()
                                .addComponent(name)
                                .addComponent(pfpChoice)
                                .addComponent(deleteButton))
        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addComponent(header)
                        .addGroup(layout.createParallelGroup()
                                .addComponent(nameInfoStr)
                                .addComponent(name))
                        .addGroup(layout.createParallelGroup()
                                .addComponent(pfpChoiceInfoStr)
                                .addComponent(pfpChoice))
                        .addGroup(layout.createParallelGroup()
                                .addComponent(saveButton)
                                .addComponent(deleteButton))
        );
        this.add(contentPanel);
    }

    private void deleteProfile() {
        StateManager sm = StateManager.getInstance();
        Object[] options = {"Yes, delete " + sm.getActiveProfile().getName(), "Cancel"};
        int choice = JOptionPane.showOptionDialog(new JPanel(), "Are you sure you want to delete " + sm.getActiveProfile().getName() + "?\n This action cannot be undone.", "Delete Profile " + sm.getActiveProfile().getName(),JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[1]);
        if (choice == 1) { // cancel option
            return;
        }
        // user did not cancel operation
        MainWindowContentManager mwcm = MainWindowContentManager.getInstance();
        ProfileService ps = new ProfileService();

        Profile profile = sm.getActiveProfile();
        ps.delete(profile);
        sm.setActiveProfile(null);
        if (ps.getAll().isEmpty())
            mwcm.goToPage("CreateProfile");
        else
            mwcm.goToPage("ProfileSelector");
    }

    private void editProfile() {
        StateManager sm = StateManager.getInstance();
        if (!InputValidator.validateProfileName(name.getText())){
            JOptionPane.showMessageDialog(new JPanel(),"Failed to update Profile.\nOnly alphanumeric characters and spaces are allowed.","Failed to update profile " + sm.getActiveProfile().getName(), JOptionPane.ERROR_MESSAGE);
            return;
        }
        ProfileService ps = new ProfileService();

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

    JPanel contentPanel;
    JLabel header;
    JLabel nameInfoStr;
    JTextField name;
    JButton saveButton;
    JLabel pfpChoiceInfoStr;
    JComboBox<String> pfpChoice;
    JButton deleteButton;

    String[] pfps = new String[]{"Black", "Blue", "Green", "Red"};

}
