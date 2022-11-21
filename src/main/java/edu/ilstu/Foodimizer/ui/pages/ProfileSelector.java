package edu.ilstu.Foodimizer.ui.pages;

import edu.ilstu.Foodimizer.app.StateManager;
import edu.ilstu.Foodimizer.app.db.models.Profile;
import edu.ilstu.Foodimizer.app.db.service.ProfileService;
import edu.ilstu.Foodimizer.ui.jcomponents.ProfileCard;

import javax.swing.*;
import java.awt.*;

public class ProfileSelector extends Page {
    public ProfileSelector() {
        init();
    }

    private void init() {
        sm = StateManager.getInstance();
        contentPane = new JPanel();
        profileSelectPanel = new JPanel();
        contentScrollPanel = new JScrollPane();


        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        contentPane.add(Box.createVerticalGlue());

        profileSelectPanel.setLayout(new FlowLayout());
        profileSelectPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        // for each user, add to profileSelectPanel
        //profileSelectPanel.add(Box.createHorizontalGlue());
        ProfileService profileService = new ProfileService();
        for (Profile p : profileService.getAll()){
        //for (int i = 0; i < 3; i++) {
            ProfileCard pc = new ProfileCard(p);
            pc.setAlignmentX(Component.CENTER_ALIGNMENT);
            pc.setAlignmentY(Component.CENTER_ALIGNMENT);
            profileSelectPanel.add(pc);
            profileSelectPanel.add(Box.createRigidArea(new Dimension(20, 20)));
        }
        //profileSelectPanel.setBorder(BorderFactory.createLineBorder(Color.red));
        contentScrollPanel.setBorder(BorderFactory.createEmptyBorder());
        contentScrollPanel.setViewportView(profileSelectPanel);
        contentPane.add(contentScrollPanel, BorderLayout.CENTER);
        contentPane.add(Box.createVerticalGlue());
        this.setLayout(new BorderLayout());
        this.add(contentPane, BorderLayout.CENTER);
    }

    JPanel contentPane;
    JPanel profileSelectPanel;
    JScrollPane contentScrollPanel;
    StateManager sm;
}
