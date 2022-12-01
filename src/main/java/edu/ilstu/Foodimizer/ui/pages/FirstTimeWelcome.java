package edu.ilstu.Foodimizer.ui.pages;

import edu.ilstu.Foodimizer.ui.MainWindowContentManager;

import javax.swing.*;
import java.awt.*;

public class FirstTimeWelcome extends Page {

    public FirstTimeWelcome() {
        super();
        init();
    }

    @Override
    protected void init() {
        contentPanel = new JPanel();

        JLabel motd = new JLabel("Welcome to Foodimizer!");
        JLabel instr = new JLabel("Click the button to get started.");

        JButton goToCreateProfilePage = new JButton("Create A Profile");
        goToCreateProfilePage.addActionListener(e -> goToCreateProfilePage());

        /* Layout */
        GroupLayout layout = new GroupLayout(contentPanel);
        contentPanel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup()
                                .addComponent(motd)
                                .addComponent(instr)
                                .addComponent(goToCreateProfilePage))
        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addComponent(motd)
                        .addComponent(instr)
                        .addComponent(goToCreateProfilePage)

        );

        this.add(contentPanel);
    }

    private void goToCreateProfilePage() {
        MainWindowContentManager mwcm = MainWindowContentManager.getInstance();
        mwcm.goToPage("CreateProfile");
    }

    JPanel contentPanel;


}
