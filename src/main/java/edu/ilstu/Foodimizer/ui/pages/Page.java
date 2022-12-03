package edu.ilstu.Foodimizer.ui.pages;

import javax.swing.*;

public abstract class Page extends JPanel {
    public Page() {
    }

    public void refreshContent() {
        this.removeAll();
        init();
        this.revalidate();
        this.repaint();
    }

    abstract protected void init();
}
