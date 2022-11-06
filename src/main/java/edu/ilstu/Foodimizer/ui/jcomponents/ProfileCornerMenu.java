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

    public ProfileCornerMenu(Profile profile) {
        System.out.println("Initializing corner menu with profile " + profile.getName());
        init();
        updateProfile(profile);
    }

    public ProfileCornerMenu() {
        init();
    }

    private void init() {

        Dimension pfpSize = new Dimension(50, 50);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        /* ----this---- */
        //setMaximumSize(size);
        setBorder(BorderFactory.createLineBorder(Color.RED));
        addMouseListener(this);
        /* ----pfp----*/
        pfp = new JPanel();
        pfp.setPreferredSize(pfpSize);
        pfp.setMaximumSize(pfpSize);
        pfp.setBorder(BorderFactory.createLineBorder(Color.RED));
        add(pfp);
        /*----pfpLabel----*/
        pfpLabel = new JLabel("Picture go here.");
        pfp.add(pfpLabel);
        /*----name----*/
        name = new JLabel("Name go here.");
        name.setFont(new Font(name.getFont().toString(), Font.BOLD, 12));
        name.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(name);
        /*----popup menu----*/
        clickMenu = new JPopupMenu();
        JMenuItem menuItem = new JMenuItem("Profile Settings");
        clickMenu.add(menuItem);
        menuItem = new JMenuItem("Switch Profiles");
        clickMenu.add(menuItem);
        menuItem = new JMenuItem("Delete Profile");
        menuItem.setForeground(Color.RED);
        clickMenu.add(menuItem);
        add(clickMenu);

    }

    public void updateProfile(Profile profile) {
        //set image
        System.out.println("Updating profile corner menu to display profile " + profile.getName());
        this.profile = profile;
        try {
            //need to scale the image somehow
            BufferedImage profilePic = ByteTools.toBufferedImage(profile.getProfilePic());
            ImageIcon ii = new ImageIcon(profilePic);
            pfpLabel.setIcon(ii);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //set name
        name.setText(this.profile.getName());
    }


    private void switchProfilesButtonPressed() {
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

    private void deleteProfileButtonPressed() {
        System.out.println("editing profile " + profile.getName());
        MainWindowContentManager mwcm = MainWindowContentManager.getInstance();
        mwcm.goToPage("CreateOrEditProfile");
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

    private MainWindowContentManager contentManager;
    private JPanel pfp;
    private JLabel pfpLabel;
    private JLabel name;
    private JPopupMenu clickMenu;


}
