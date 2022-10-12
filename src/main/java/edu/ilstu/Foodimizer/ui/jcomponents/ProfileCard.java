package edu.ilstu.Foodimizer.ui.jcomponents;

import edu.ilstu.Foodimizer.lib.Profile;

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
        Dimension cardSize = new Dimension(200, 250);
        Dimension profilePicSize = new Dimension(200, 200);

        setBackground(Color.white);
        setPreferredSize(cardSize);

        JPanel pfp = new JPanel();
        pfp.setPreferredSize(profilePicSize);
        pfp.addMouseListener(this);
        pfp.setBackground(Color.gray);

        add(pfp);
    }

    private void switchProfiles() {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("you clicked profile " + profile.getName());
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
}
