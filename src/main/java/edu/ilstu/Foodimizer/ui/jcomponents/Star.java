package edu.ilstu.Foodimizer.ui.jcomponents;

import javax.swing.*;
import java.awt.*;

public class Star extends JButton {

    public Star() {

        setContentAreaFilled(false);
        setBackground(new Color(200,200,200));
        setForeground(Color.yellow);
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int width = 25;
        int height = 25;
        int size = Math.min(width, height) / 2;
        int x = width / 2;
        int y = height / 2;
        Star2D s = new Star2D(x, y, size / 2, size, 5);
        g2.setColor(getBackground());
        g2.fill(s);
        if (isSelected()) {
            g2.setColor(getForeground());
            g2.fill(s);
        }
        g2.dispose();
    }
}
