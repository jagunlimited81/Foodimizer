package edu.ilstu.Foodimizer.lib;


import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

public class SysDialogPrinter {
    public static int print(String jobName, PDFFormatter page) {
        // Obtain a Printerjob
        PrinterJob pj = PrinterJob.getPrinterJob();
        pj.setJobName(jobName);
        pj.setPrintable((Printable) page);
        if (!pj.printDialog()) //if not ok
            return 1;
        try {
            pj.print();
        } catch (PrinterException ex) {
            // The job did not complete
            System.out.println("[FAIL] Failed to print");
        }
        return 0;
    }
}
