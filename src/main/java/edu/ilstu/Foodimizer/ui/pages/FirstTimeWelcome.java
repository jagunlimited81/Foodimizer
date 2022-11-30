package edu.ilstu.Foodimizer.ui.pages;

import edu.ilstu.Foodimizer.ui.MainWindowContentManager;

import javax.swing.*;

public class FirstTimeWelcome extends Page{

    public FirstTimeWelcome() {
        super();
        init();
    }

    private void init() {
     JLabel motd = new JLabel("Welcome to Foodimizer!");
     JLabel instr = new JLabel("Click the button to get started.");
     this.add(motd);
     this.add(instr);
     goToCreateOrEditProfilePage = new JButton("Create A Profile");
     goToCreateOrEditProfilePage.addActionListener(e -> goToCreateOrEditProfilePage());
     this.add(goToCreateOrEditProfilePage);
    }

    private void goToCreateOrEditProfilePage(){
        MainWindowContentManager mwcm = MainWindowContentManager.getInstance();
        mwcm.goToPage("CreateProfile");
    }

    JButton goToCreateOrEditProfilePage;
}
