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

public class ProfileCornerMenu extends JPanel implements MouseListener {
    Profile profile;
    Dimension pfpSize = new Dimension(50, 50);
    private JLabel pfpLabel;
    private JLabel name;
    private JPopupMenu clickMenu;

    public ProfileCornerMenu() {
        System.out.println("Initializing corner menu");
        init();
    }

    private void init() {

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.profile = StateManager.getInstance().getActiveProfile();
        if (profile == null) {
            return;
        }


        /* ----this---- */
        //setMaximumSize(size);
        //setBorder(BorderFactory.createLineBorder(Color.RED));
        addMouseListener(this);
        /* ----pfp----*/
        JPanel pfp = new JPanel();
        //pfp.setBorder(BorderFactory.createLineBorder(Color.RED));
        add(pfp);
        /*----pfpLabel----*/
        pfpLabel = new JLabel();
        pfp.add(pfpLabel);
        /*----name----*/
        name = new JLabel("Name go here.");
        name.setFont(new Font(name.getFont().toString(), Font.BOLD, 12));
        name.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(name);
        /*----popup menu----*/
        clickMenu = new JPopupMenu();

        JMenuItem menuItem = new JMenuItem("Profile Settings");
        menuItem.addActionListener(e -> editProfileButtonPressed());
        clickMenu.add(menuItem);
        menuItem = new JMenuItem("Switch Profiles");
        menuItem.addActionListener(e -> switchProfilesButtonPressed());
        clickMenu.add(menuItem);
        add(clickMenu);

        updateProfile(profile);
    }

    public void updateProfile(Profile profile) {
        //set image
        System.out.println("Updating profile corner menu to display profile " + profile.getName());
        this.profile = profile;
        try {
            //need to scale the image somehow
            BufferedImage profilePic = ByteTools.toBufferedImage(profile.getProfilePic());
            Image temp = profilePic.getScaledInstance(pfpSize.width, pfpSize.height, Image.SCALE_SMOOTH);
            BufferedImage scaled = new BufferedImage(pfpSize.width, pfpSize.height, profilePic.getType());

            Graphics2D g2d = scaled.createGraphics();
            g2d.drawImage(temp, 0, 0, null);
            g2d.dispose();

            ImageIcon ii = new ImageIcon(scaled);
            pfpLabel.setIcon(ii);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //set name
        name.setText(this.profile.getName());
    }

    private void switchProfilesButtonPressed() {
        System.out.println("Switch profile button pressed");
        StateManager sm = StateManager.getInstance();
        MainWindowContentManager mwcm = MainWindowContentManager.getInstance();
        sm.setActiveProfile(profile);
        mwcm.goToPage("ProfileSelector");
    }

    private void editProfileButtonPressed() {
        System.out.println("Edit profile button pressed");
        MainWindowContentManager mwcm = MainWindowContentManager.getInstance();
        mwcm.goToPage("EditProfile");
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        clickMenu.show(e.getComponent(), e.getX(), e.getY());
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
