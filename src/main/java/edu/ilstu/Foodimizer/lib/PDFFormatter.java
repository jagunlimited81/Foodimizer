package edu.ilstu.Foodimizer.lib;

import edu.ilstu.Foodimizer.app.StateManager;
import edu.ilstu.Foodimizer.app.db.models.Ingredient;
import edu.ilstu.Foodimizer.app.db.models.Profile;
import edu.ilstu.Foodimizer.app.db.models.Recipe;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterGraphics;
import java.util.List;

import edu.ilstu.Foodimizer.ui.jcomponents.StarRating;


public class PDFFormatter implements Printable {

    private JPanel contentPanel;

    public PDFFormatter(Recipe recipe) {
        contentPanel = new JPanel();

        /* Left Column */
        JPanel leftColumn = new JPanel();
        JPanel recipeImage = new JPanel();
        JPanel recipeCookInfoPanel = new JPanel();
        JLabel mealType = new JLabel();
        JLabel servingSize = new JLabel();
        JLabel cookMethod = new JLabel();
        JLabel prepTime = new JLabel();
        JLabel cookTime = new JLabel();
        JLabel waitTime = new JLabel();

        /* Center Column */
        JPanel centerColumn = new JPanel();
        JPanel recipeHeader = new JPanel();
        JLabel recipeNameTitle = new JLabel();
        JTextArea recipeDescription = new JTextArea();
        JPanel sideBySideIngredientsActionPanel = new JPanel();
        JPanel ingredientsListPanel = new JPanel();
        JPanel ingredientsList = new JPanel();
        TitledBorder ingredientsBox = new TitledBorder("Ingredients");
        JLabel directionsHeader = new JLabel();
        JPanel directions = new JPanel();
        JTextPane directionsTextPane = new JTextPane();

        /* Right Column */
        JPanel rightColumn = new JPanel();

        GridBagConstraints gbc = new GridBagConstraints();

        /* contentPanel */
        //contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.X_AXIS));
        contentPanel.setLayout(new BorderLayout());

        /* leftColumn */
        {
            leftColumn.setLayout(new BoxLayout(leftColumn, BoxLayout.Y_AXIS));
            leftColumn.setPreferredSize(new Dimension(250, contentPanel.getHeight()));

            recipeImage.setPreferredSize(new Dimension(200, 200));
            recipeImage.setMaximumSize(new Dimension(200, 200));
            recipeImage.setMinimumSize(new Dimension(200, 200));
            recipeImage.setBackground(Color.gray);
            recipeImage.setBorder(BorderFactory.createLineBorder(Color.red));
            recipeImage.setAlignmentX(Component.CENTER_ALIGNMENT);
            leftColumn.add(recipeImage);

            recipeCookInfoPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
            recipeCookInfoPanel.setBorder(BorderFactory.createLineBorder(Color.red));
            GroupLayout rcipLayout = new GroupLayout(recipeCookInfoPanel);
            recipeCookInfoPanel.setLayout(rcipLayout);
            rcipLayout.setAutoCreateGaps(true);
            rcipLayout.setAutoCreateContainerGaps(true);

            mealType.setText("Meal Type: ");
            servingSize.setText("Serving Size: ");
            cookMethod.setText("Cook Method: ");
            cookTime.setText("Cook Time: ");
            waitTime.setText("Wait Time: ");
            prepTime.setText("Prep Time: ");

            JLabel mealTypeData = new JLabel(recipe.getMealType());
            JLabel servingSizeData = new JLabel(recipe.getCookMethod());
            JLabel cookMethodData = new JLabel(recipe.getCookTime() + " min");
            JLabel cookTimeData = new JLabel(recipe.getMealType());
            JLabel waitTimeData = new JLabel(recipe.getWaitTime() + " min");
            JLabel prepTimeData = new JLabel(recipe.getPrepTime() + " min");
            rcipLayout.setHorizontalGroup(
                    rcipLayout.createSequentialGroup()
                            .addGroup(rcipLayout.createParallelGroup()
                                    .addComponent(mealType)
                                    .addComponent(servingSize)
                                    .addComponent(cookMethod)
                                    .addComponent(cookTime)
                                    .addComponent(waitTime)
                                    .addComponent(prepTime))
                            .addGroup(rcipLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                    .addComponent(mealTypeData)
                                    .addComponent(servingSizeData)
                                    .addComponent(cookMethodData)
                                    .addComponent(cookTimeData)
                                    .addComponent(waitTimeData)
                                    .addComponent(prepTimeData))
            );
            rcipLayout.setVerticalGroup(
                    rcipLayout.createSequentialGroup()
                            .addGroup(rcipLayout.createParallelGroup()
                                    .addComponent(mealType)
                                    .addComponent(mealTypeData))
                            .addGroup(rcipLayout.createParallelGroup()
                                    .addComponent(servingSize)
                                    .addComponent(servingSizeData))
                            .addGroup(rcipLayout.createParallelGroup()
                                    .addComponent(cookMethod)
                                    .addComponent(cookMethodData))
                            .addGroup(rcipLayout.createParallelGroup()
                                    .addComponent(cookTime)
                                    .addComponent(cookTimeData))
                            .addGroup(rcipLayout.createParallelGroup()
                                    .addComponent(waitTime)
                                    .addComponent(waitTimeData))
                            .addGroup(rcipLayout.createParallelGroup()
                                    .addComponent(prepTime)
                                    .addComponent(prepTimeData))
            );
            leftColumn.add(recipeCookInfoPanel);
        }
        contentPanel.add(leftColumn, BorderLayout.WEST);

        /* centerColumn */
        {
            centerColumn.setLayout(new BorderLayout());

            recipeHeader.setLayout(new BorderLayout());
            recipeNameTitle.setText(recipe.getName());
            recipeNameTitle.setFont(new Font("Verdana", Font.PLAIN, 22));
            recipeHeader.add(recipeNameTitle, BorderLayout.NORTH);
            recipeDescription.setText(recipe.getDescription());
            recipeDescription.setLineWrap(true);
            recipeDescription.setEditable(false);

            recipeHeader.add(recipeDescription, BorderLayout.SOUTH);
            recipeHeader.setMaximumSize(recipeHeader.getMinimumSize());
            centerColumn.add(recipeHeader, BorderLayout.NORTH);

            sideBySideIngredientsActionPanel.setLayout(new BoxLayout(sideBySideIngredientsActionPanel, BoxLayout.LINE_AXIS));

            ingredientsList.setBorder(ingredientsBox);
            ingredientsList.setLayout(new BoxLayout(ingredientsList, BoxLayout.Y_AXIS));
            //JCheckBox[] jt = new JCheckBox("IngredientName");
            Profile profile = StateManager.getInstance().getActiveProfile();
            for (Ingredient i : recipe.getRecipeIngredients()) {
                JCheckBox jt = new JCheckBox(i.getName());
                if (profile.getPantry().contains(i)) {
                    jt.setSelected(true);
                }
                jt.setEnabled(false);
                ingredientsList.add(jt);
            }

            ingredientsList.setMaximumSize(ingredientsList.getMinimumSize());

            ingredientsListPanel.add(ingredientsList);
            centerColumn.add(ingredientsListPanel, BorderLayout.WEST);

            JPanel space = new JPanel();
            space.setPreferredSize(new Dimension(1, 1));
            directions.setLayout(new BoxLayout(directions, BoxLayout.Y_AXIS));
            directionsHeader.setText("Directions:");
            // TODO align header left
            directionsHeader.setFont(new Font("Verdana", Font.PLAIN, 21));
            directionsTextPane.setText(recipe.getDirections());
            directionsTextPane.setEditable(false);
            directions.add(directionsHeader);
            directions.add(directionsTextPane);
            centerColumn.add(directions, BorderLayout.CENTER);
        }
        JScrollPane centerScrollPane = new JScrollPane(centerColumn);
        centerScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        centerScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        centerScrollPane.setPreferredSize(new Dimension(contentPanel.getWidth() / 2, contentPanel.getHeight()));

        contentPanel.add(centerScrollPane, BorderLayout.CENTER);
        /* rightColumn */
        {
            rightColumn.setLayout(new BoxLayout(rightColumn, BoxLayout.Y_AXIS));
            rightColumn.setPreferredSize(new Dimension(250, contentPanel.getHeight()));

            StarRating sr = new StarRating(recipe);
            rightColumn.add(sr);
        }
        contentPanel.add(rightColumn, BorderLayout.EAST);
    }


    public PDFFormatter(List<Ingredient> ingredients) {
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
        int w = (int)pf.getImageableWidth();
        int h = (int)pf.getImageableHeight();
        contentPanel.setSize(new Dimension(w,h));

        layoutComponent(contentPanel);
        contentPanel.printAll(g2d);

        /* tell the caller that this page is part of the printed document */
        return PAGE_EXISTS;
    }

    private void layoutComponent(Component component)
    {
        synchronized (component.getTreeLock())
        {
            component.doLayout();

            if (component instanceof Container)
            {
                for (Component child : ((Container)component).getComponents())
                {
                    this.layoutComponent(child);
                }
            }
        }
    }
}
