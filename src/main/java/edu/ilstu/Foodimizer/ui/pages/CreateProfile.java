package edu.ilstu.Foodimizer.ui.pages;

import edu.ilstu.Foodimizer.app.db.models.Profile;
import edu.ilstu.Foodimizer.app.db.service.ProfileService;
import edu.ilstu.Foodimizer.lib.ByteTools;
import edu.ilstu.Foodimizer.ui.MainWindowContentManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class CreateProfile extends Page{
    public CreateProfile() {
        super();
        init();
    }


    public void init() {
        JLabel header = new JLabel("Create a profile");
        nameInfoStr = new JLabel("Name:");
        name = new JTextField();
        saveButton = new JButton("Create Profile");
        saveButton.addActionListener(e -> createProfile());
        pfpChoiceInfoStr = new JLabel("Profile Picture Color:");
        pfpChoice = new JComboBox<>(pfps);
        this.add(header);
        this.add(nameInfoStr);
        this.add(name);
        this.add(pfpChoiceInfoStr);
        this.add(pfpChoice);
        this.add(saveButton);
    }

    private void createProfile() {
        ProfileService ps = new ProfileService();
        Profile profile = new Profile();
        BufferedImage image;
        profile.setName(name.getText());

        try {
            image = ImageIO.read(this.getClass().getResource("/images/nopfp" + pfpChoice.getSelectedIndex() + ".png"));
            byte[] bytes = ByteTools.toByteArray(image, "png");
            profile.setProfilePic(bytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ps.save(profile);
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

    String[] pfps = new String[]{"Black", "Blue", "Green", "Red" };

}
