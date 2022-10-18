package edu.ilstu.Foodimizer.ui.jcomponents;

import edu.ilstu.Foodimizer.app.StateManager;
import edu.ilstu.Foodimizer.app.db.models.Profile;
import edu.ilstu.Foodimizer.lib.ByteTools;
import edu.ilstu.Foodimizer.ui.MainWindowContentManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ProfileCard extends JPanel implements MouseListener {
    Profile profile;

    public ProfileCard(Profile profile) {
        this.profile = profile;
        init();

    }

    private void init() {

        Dimension cardSize = new Dimension(200, 400);
        Dimension profilePicSize = new Dimension(200, 200);

        /* ----this---- */
        //setMaximumSize(cardSize);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createLineBorder(Color.RED));
        /* ----pfp----*/
        JPanel pfp = new JPanel();
        pfp.setPreferredSize(profilePicSize);
        pfp.setMaximumSize(profilePicSize);
        pfp.addMouseListener(this);
        pfp.setBorder(BorderFactory.createLineBorder(Color.RED));
        try {
            BufferedImage profilePic = ByteTools.toBufferedImage(profile.getProfilePic());
            ImageIcon ii = new ImageIcon(profilePic);
            pfp.add(new JLabel(ii));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        add(pfp);
        JLabel name = new JLabel(profile.getName());
        name.setFont(new Font(name.getFont().toString(), Font.BOLD, 20));
        name.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(name);

        /* ----edit button----*/
        editButton = new JButton("edit");
        editButton.addActionListener(e -> editProfileButtonPressed());
        editButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(editButton);
    }

    private void switchProfiles() {
        System.out.println("Switching to profile " + profile.getName());
        StateManager sm = StateManager.getInstance();
        MainWindowContentManager mwcm = MainWindowContentManager.getInstance();
        sm.setActiveProfile(profile);
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
