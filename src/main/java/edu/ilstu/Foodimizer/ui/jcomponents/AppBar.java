package edu.ilstu.Foodimizer.ui.jcomponents;

import javax.swing.*;
import java.awt.*;

public class AppBar extends JPanel {
    public AppBar() {
        init();
    }

    private void init() {
        title = new JLabel();
        pantryMenu = new JMenu();
        newPantryItem = new JMenuItem();
        /* this */
        this.setLayout(new BorderLayout());
        //this.setBackground(Color.gray);

        /* title*/
        {
            title.setText("Foodimizer");
            title.setFont(new Font("Verdana", Font.PLAIN, 22));
            title.setSize(500, 500);
        }
        this.add(title, BorderLayout.NORTH);

        /* menuBar1 */
        {
            /* Pantry */
            {
                pantryMenu.setText("Pantry");
                pantryMenu.setMnemonic('P');

                newPantryItem.setText("Add to Pantry...");
                newPantryItem.setMnemonic('n');
                pantryMenu.add(newPantryItem);

            }
        }
        JMenuBar menuBar1 = new JMenuBar();
        menuBar1.add(pantryMenu);
        this.add(menuBar1, BorderLayout.SOUTH);

    }

    /* Declare variables here */
    private JMenu pantryMenu;
    private JMenuBar menuBar1;
    private JMenuItem newPantryItem;
    private JLabel title;

}
