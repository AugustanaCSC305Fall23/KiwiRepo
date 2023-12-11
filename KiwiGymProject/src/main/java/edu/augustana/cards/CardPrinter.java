package edu.augustana.cards;

import javafx.print.*;
import javafx.scene.Node;
import javafx.scene.transform.Scale;

public class CardPrinter {

    public void printCard(Node card) {
        // Create a printer job for the default printer

        PrinterJob printerJob = PrinterJob.createPrinterJob();

        if (printerJob != null) {
            // Create a PageLayout with A4 paper size
            PageLayout pageLayout = printerJob.getPrinter().createPageLayout(Paper.A4, PageOrientation.LANDSCAPE, Printer.MarginType.DEFAULT);

            // Calculate scaling factors to fit the card into the printable area
            double scaleX = pageLayout.getPrintableWidth() / card.getBoundsInParent().getWidth();
            double scaleY = pageLayout.getPrintableHeight() / card.getBoundsInParent().getHeight();
            double scale = Math.min(scaleX, scaleY);

            // Apply scaling to the card
            card.getTransforms().add(new Scale(scale, scale));

            // Show the print setup dialog
            boolean proceed = printerJob.showPrintDialog(card.getScene().getWindow());

            if (proceed) {
                // Print the node and check for success
                boolean success = printerJob.printPage(pageLayout, card);

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
        /*PrinterJob printerJob = PrinterJob.createPrinterJob();

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
        }*/
    }
}
