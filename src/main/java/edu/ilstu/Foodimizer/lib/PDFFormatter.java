package edu.ilstu.Foodimizer.lib;

import edu.ilstu.Foodimizer.app.db.models.Ingredient;
import edu.ilstu.Foodimizer.app.db.models.Recipe;

import javax.swing.*;
import java.awt.*;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterGraphics;
import java.util.List;

public class PDFFormatter extends Formatter implements Printable {

    private JPanel contentPanel;

    public PDFFormatter(Recipe recipe) {
        formatRecipe(recipe);
    }

    public PDFFormatter(List<Ingredient> ingredients) {
        formatShoppingList(ingredients);
    }

    /**
     * Prints the page at the specified index into the specified
     * {@link Graphics} context in the specified
     * format.  A {@code PrinterJob} calls the
     * {@code Printable} interface to request that a page be
     * rendered into the context specified by
     * {@code graphics}.  The format of the page to be drawn is
     * specified by {@code pageFormat}.  The zero based index
     * of the requested page is specified by {@code pageIndex}.
     * If the requested page does not exist then this method returns
     * NO_SUCH_PAGE; otherwise PAGE_EXISTS is returned.
     * The {@code Graphics} class or subclass implements the
     * {@link PrinterGraphics} interface to provide additional
     * information.  If the {@code Printable} object
     * aborts the print job then it throws a {@link PrinterException}.
     *
     * @param g   the context into which the page is drawn
     * @param pf the size and orientation of the page being drawn
     * @param pageIndex  the zero based index of the page to be drawn
     * @return PAGE_EXISTS if the page is rendered successfully
     * or NO_SUCH_PAGE if {@code pageIndex} specifies a
     * non-existent page.
     * @throws PrinterException thrown when the print job is terminated.
     */
    @Override
    public int print(Graphics g, PageFormat pf, int pageIndex) throws PrinterException {
        if (pageIndex > 0) { /* We have only one page, and 'page' is zero-based */
            return NO_SUCH_PAGE;
        }

        /* User (0,0) is typically outside the imageable area, so we must
         * translate by the X and Y values in the PageFormat to avoid clipping
         */
        Graphics2D g2d = (Graphics2D) g;
        g2d.translate(pf.getImageableX(), pf.getImageableY());

        /* Now print the window and its visible contents */
        contentPanel.printAll(g);

        /* tell the caller that this page is part of the printed document */
        return PAGE_EXISTS;
    }

    @Override
    public void formatRecipe(Recipe recipe)
    {
        contentPanel = new JPanel();

        contentPanel.add(new JLabel("asdftesting"));
        contentPanel.add(new JLabel("asdftesting"));
        contentPanel.add(new JLabel("asdftesting"));
        contentPanel.add(new JLabel("asdftesting"));
        JPanel BIGREDBOX = new JPanel();
        BIGREDBOX.setBackground(Color.RED);
        BIGREDBOX.setPreferredSize(new Dimension(100, 100));
        contentPanel.add(BIGREDBOX);
    }

    @Override
    public void formatShoppingList(List<Ingredient> shoppingList)
    {

    }
}
