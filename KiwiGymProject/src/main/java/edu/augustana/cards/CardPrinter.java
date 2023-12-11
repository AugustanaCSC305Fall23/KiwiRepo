package edu.augustana.cards;

import javafx.print.PrinterJob;
import javafx.scene.Node;

public class CardPrinter {

    public void printCard(Node card) {
        // Create a printer job for the default printer
        PrinterJob printerJob = PrinterJob.createPrinterJob();

        if (printerJob != null) {
            // Show the print setup dialog
            boolean proceed = printerJob.showPrintDialog(card.getScene().getWindow());

            if (proceed) {
                // Print the node and check for success
                boolean success = printerJob.printPage(card);

                if (success) {
                    printerJob.endJob();
                } else {
                    // Handle the case where print did not complete successfully
                    System.out.println("Failed to print");
                }
            }
        } else {
            // Handle the case where a printer could not be found
            System.out.println("No printers installed");
        }
    }
}
