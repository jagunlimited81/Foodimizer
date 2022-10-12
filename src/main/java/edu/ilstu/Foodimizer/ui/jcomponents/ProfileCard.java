package edu.ilstu.Foodimizer.ui.jcomponents;

import edu.ilstu.Foodimizer.app.FoodimizerClient;
import edu.ilstu.Foodimizer.app.FoodimizerClientManager;
import edu.ilstu.Foodimizer.lib.Profile;
import edu.ilstu.Foodimizer.ui.MainWindowContentManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ProfileCard extends JPanel implements MouseListener {
    Profile profile;

    public ProfileCard(Profile profile) {
        this.profile = profile;
        init();

    }

    private void init() {
        FoodimizerClientManager fcm = FoodimizerClientManager.getInstance();
        Dimension cardSize = new Dimension(200, 250);
        Dimension profilePicSize = new Dimension(200, 200);

        /* ----this---- */
        //setBackground(Color.white);
        setPreferredSize(cardSize);

        /* ----pfp----*/
        JPanel pfp = new JPanel();
        pfp.setPreferredSize(profilePicSize);
        pfp.addMouseListener(this);
        pfp.setBackground(Color.gray);
        add(pfp);

        /* ----edit button----*/
        editButton = new JButton("edit");
        editButton.addActionListener(e -> editProfileButtonPressed());
        add(editButton);
    }

    private void switchProfiles() {
        System.out.println("Switching to profile " + profile.getName());
        FoodimizerClientManager fcm = FoodimizerClientManager.getInstance();
        MainWindowContentManager mwcm = MainWindowContentManager.getInstance();
        fcm.setActiveProfile(profile);
        mwcm.goToPage("MyPantry");
    }

    private void editProfileButtonPressed() {
        System.out.println("editing profile " + profile.getName());
        MainWindowContentManager mwcm = MainWindowContentManager.getInstance();
        mwcm.goToPage("CreateOrEditProfile");

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("you clicked profile " + profile.getName());
        switchProfiles();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
    private JButton editButton;
}
