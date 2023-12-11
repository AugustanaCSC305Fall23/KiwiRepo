package edu.augustana;

import edu.augustana.cards.Card;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.print.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.transform.Scale;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class PrintViewController {

    private Plan plan;

    @FXML
    private GridPane printTextGridPane;

    @FXML
    private Button printViewBackBtn;

    @FXML
    private Button printButton;

    @FXML
    private HBox cardHbox;

    @FXML
    private GridPane printGridPane;


    @FXML
    private Button printPlanBtn;

    public HBox getPrintViewHbox(){
        return cardHbox;
    }

    public GridPane getPrintGridPane(){
        return printGridPane;
    }

    public PrintViewController(){

    }

    @FXML
    public void printGridPaneContent() {
        PrinterJob printerJob = PrinterJob.createPrinterJob();
        if (printerJob != null) {

            PageLayout pageLayout = printerJob.getPrinter().createPageLayout(Paper.A4, PageOrientation.PORTRAIT, Printer.MarginType.DEFAULT);

            double scaleX = pageLayout.getPrintableWidth() / printGridPane.getBoundsInParent().getWidth();
            double scaleY = pageLayout.getPrintableHeight() / printGridPane.getBoundsInParent().getHeight();
            double scale = Math.min(scaleX, scaleY);


            Scale originalScale = new Scale(printGridPane.getScaleX(), printGridPane.getScaleY());

            // Scale the gridPane to fit the page
            printGridPane.getTransforms().add(new Scale(scale, scale));

            if (printerJob.showPrintDialog(printGridPane.getScene().getWindow())) {
                boolean success = printerJob.printPage(pageLayout, printGridPane);
                if (!success) {
                    // Handle unsuccessful print
                }
            } else {
                // Handle case where print dialog is canceled or no printers are found
            }

            // Restore the original scale after printing
            printGridPane.getTransforms().setAll(originalScale);

            printerJob.endJob();
        }
    }

    public void setPlan(String planName){
        this.plan = Course.getPlanList().get(Course.getListPlanNames().indexOf(planName));

    }

    public void setPrintPreview() throws IOException {
        Map<String, List<Image>> eventToCards = new TreeMap<>();
        Map<String, List<Card>> eventToCardText = new TreeMap<>();

        for(Card card: this.plan.getCardList()){
            if(!eventToCards.containsKey(card.getEvent())){
                List<Image> cardImages = new ArrayList<>();
                List<Card> cards = new ArrayList<>();
                //System.out.println(card.getImage());
                cardImages.add(new Image("file:CardPacks/" + card.getPackFolder() +"/"+ card.getImage()));
                cards.add(card);
                eventToCards.put(card.getEvent(), cardImages);
                eventToCardText.put(card.getEvent(), cards);

            } else {
                eventToCards.get(card.getEvent()).add(new Image("file:CardPacks/" + card.getPackFolder() +"/" + card.getImage()));
                eventToCardText.get(card.getEvent()).add(card);
            }
        }

        //System.out.println(eventToCards);

        int column = 0;
        int row = 1;

        for(String events: eventToCards.keySet()){

            for (Image cardImages: eventToCards.get(events)){
                ImageView imageView = new ImageView(cardImages);
                imageView.setPreserveRatio(true);
                imageView.setFitWidth(350);
                imageView.setFitHeight(270.45);


                if (column == 5) {
                    column = 0;
                    row += 1;
                }
                //System.out.println(imageView);
                printGridPane.add(imageView, column++, row);
                GridPane.setMargin(imageView, new Insets(1));

            }

            column = 0;
            row += 2;

        }


    }

    public static void printCardsFromPlan(String planName) {

        PrinterJob job = PrinterJob.createPrinterJob();
        boolean proceed = job.showPrintDialog(null);
        PageLayout pageLayout = job.getJobSettings().getPageLayout();
        double printableWidth = pageLayout.getPrintableWidth();

        Plan planToBePrinted = Course.getPlanList().get(Course.getListPlanNames().indexOf(planName));

        //maps event to card images that belong to that event



        VBox vBox = new VBox(10);


        boolean success = job.printPage(vBox);
        if (!success) {
            System.out.println("printing not wokring aman");

        } else {

        }

        job.endJob();

    }

    @FXML
    public void switchToPlanChooser() throws IOException {
        GymnasticsApp.setRoot("choosePlanToBePrinted");
    }



}
