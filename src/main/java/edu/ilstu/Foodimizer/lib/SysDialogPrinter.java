package edu.ilstu.Foodimizer.lib;


import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

public class SysDialogPrinter {
    public static void print(String jobName, PDFFormatter page) {
        // Obtain a Printerjob
        PrinterJob pj = PrinterJob.getPrinterJob();
        pj.setJobName(jobName);
        pj.setPrintable(page);
        if (!pj.printDialog()) //if not ok
            return;
        try {
            pj.print();
        } catch (PrinterException ex) {
            // The job did not complete
            System.out.println("[FAIL] Failed to print");
        }
    }
}
