package edu.ilstu.Foodimizer;

import com.formdev.flatlaf.FlatLightLaf;
import edu.ilstu.Foodimizer.app.DatabaseFiller;
import edu.ilstu.Foodimizer.ui.MainWindowContentManager;

import javax.swing.*;
import java.awt.*;

public class Main {

    /**
     * Entry point for the Foodimizer project
     *
     * @param args Program arguments (unused)
     */
    public static void main(String[] args) {
        /* Start the JFrame Application */
        try {
            FlatLightLaf.setup();
        } catch (Exception e) {
            System.out.println("failed to FlatLaf");
        }

        DatabaseFiller dbf = new DatabaseFiller();
        dbf.createTestIngredients();
        dbf.createTestRecipes();
        //dbf.createTestProfiles();


        System.out.println("Starting JFrame...");
        /* TODO: Explain why we use invokeLater*/
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            JPanel mwcm = MainWindowContentManager.getInstance();
            MainWindowContentManager.getInstance().init();
            frame.getContentPane().add(mwcm);
            frame.setPreferredSize((new Dimension(1200, 675)));
            frame.pack();
            frame.setLocationByPlatform(true);
            //frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            //frame.setResizable(false);
        });
    }
}