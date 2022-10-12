package edu.ilstu.Foodimizer.ui;

import edu.ilstu.Foodimizer.ui.jcomponents.AppBar;

import javax.swing.*;
import java.awt.*;


/**
 *
 */
public class MainWindow extends JFrame {
    public MainWindow() {
        init();
    }

    private void init() {
        contentPanel = MainWindowContentManager.getInstance();
        /* this */
        setTitle("Foodimizer");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        contentPane.add(new AppBar(), BorderLayout.NORTH);

        contentPane.add(contentPanel, BorderLayout.CENTER);
    }
    /* Declare variables here */
    private MainWindowContentManager contentPanel;
}
