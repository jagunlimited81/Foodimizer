package edu.ilstu.Foodimizer;

import com.formdev.flatlaf.FlatLightLaf;
import edu.ilstu.Foodimizer.ui.MainWindow;

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
        } catch(Exception e) {
            System.out.println("failed to FlatLaf");
        }

        System.out.println("Starting JFrame...");
        /* TODO: Explain why we use invokeLater*/
        SwingUtilities.invokeLater(() -> {
            MainWindow frame = new MainWindow();
            frame.setPreferredSize((new Dimension(1200, 675)));
            //frame.setPreferredSize((new Dimension(200, 200)));
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            frame.setResizable(false);
        });
    }
}