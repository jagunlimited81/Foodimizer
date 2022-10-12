package edu.ilstu.Foodimizer.lib;

import javax.swing.*;
import java.awt.*;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

public class SysDialogPrinter {
    public static int print(String jobName, Component page) {
        // Obtain a Printerjob
        PrinterJob pj = PrinterJob.getPrinterJob();
        pj.setJobName(jobName);

        // Set PrinterJob to
        pj.setPrintable(new Printable() {
            public int print(Graphics pg, PageFormat pf, int pageNum) {
                if (pageNum > 0) {
                    return Printable.NO_SUCH_PAGE;
                }

                Graphics2D g2 = (Graphics2D) pg;
                g2.translate(pf.getImageableX(), pf.getImageableY());
                page.paint(g2);
                return Printable.PAGE_EXISTS;
            }
        });
        if (!pj.printDialog())
            return 1;

        try {
            pj.print();
        } catch (PrinterException ex) {
            // handle exception
        }
        return 0;
    }
}
