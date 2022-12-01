package edu.ilstu.Foodimizer.ui.pages;

import edu.ilstu.Foodimizer.app.db.models.Profile;
import edu.ilstu.Foodimizer.app.db.service.ProfileService;
import edu.ilstu.Foodimizer.lib.ByteTools;
import edu.ilstu.Foodimizer.lib.InputValidator;
import edu.ilstu.Foodimizer.ui.MainWindowContentManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class CreateProfile extends Page {
    public CreateProfile() {
        super();
        init();
    }


    public void init() {
        contentPanel = new JPanel();
        JLabel header = new JLabel("Create a profile");
        nameInfoStr = new JLabel("Name:");
        name = new JTextField();
        name.setPreferredSize(new Dimension(name.getWidth()+200, name.getHeight()));
        saveButton = new JButton("Create Profile");
        saveButton.addActionListener(e -> createProfile());
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
                                .addComponent(pfpChoice))
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
                        .addComponent(saveButton)
        );
        this.add(contentPanel);
    }

    private void createProfile() {
        if (!InputValidator.validateProfileName(name.getText())){
            JOptionPane.showMessageDialog(new JPanel(),"Failed to create Profile.\nOnly alphanumeric characters and spaces are allowed.","Failed to create profile " + name.getText(), JOptionPane.ERROR_MESSAGE);
            return;
        }
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

    JPanel contentPanel;
    JPanel pfp;
    JLabel nameInfoStr;
    JTextField name;
    JButton saveButton;
    JLabel pfpChoiceInfoStr;
    JComboBox<String> pfpChoice;

    String[] pfps = new String[]{"Black", "Blue", "Green", "Red"};

}
