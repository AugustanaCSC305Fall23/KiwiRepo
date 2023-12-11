package edu.augustana;

import edu.augustana.cards.Card;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.print.*;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.transform.Scale;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class PrintViewController {

    private Plan plan;

    @FXML
    private ChoiceBox<String> printEquipmentBtn;


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

    @FXML
    public void printTextGridePaneContent(){
        PrinterJob printerJob = PrinterJob.createPrinterJob();
        if (printerJob != null) {

            PageLayout pageLayout = printerJob.getPrinter().createPageLayout(Paper.A4, PageOrientation.PORTRAIT, Printer.MarginType.DEFAULT);

            double scaleX = pageLayout.getPrintableWidth() / printTextGridPane.getBoundsInParent().getWidth();
            double scaleY = pageLayout.getPrintableHeight() / printTextGridPane.getBoundsInParent().getHeight();
            double scale = Math.min(scaleX, scaleY);


            Scale originalScale = new Scale(printTextGridPane.getScaleX(), printTextGridPane.getScaleY());

            // Scale the gridPane to fit the page
            printTextGridPane.getTransforms().add(new Scale(scale, scale));

            if (printerJob.showPrintDialog(printTextGridPane.getScene().getWindow())) {
                boolean success = printerJob.printPage(pageLayout, printTextGridPane);
                if (!success) {
                    // Handle unsuccessful print
                }
            } else {
                // Handle case where print dialog is canceled or no printers are found
            }

            // Restore the original scale after printing
            printTextGridPane.getTransforms().setAll(originalScale);

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
                imageView.setFitWidth(300);
                imageView.setFitHeight(177.485);


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

        int columnText = 0;
        int rowText = 1;

        for(String events: eventToCardText.keySet()){

            for(Card card: eventToCardText.get(events)){
                Text cardText = new Text(card.toString());
                cardText.setFont(Font.font(20));
                cardText.setStyle("-fx-margin: 10;");

                if (columnText == 5) {
                    columnText = 0;
                    rowText += 1;
                }

                printTextGridPane.add(cardText, columnText++, rowText);
            }

            columnText = 0;
            rowText += 2;
        }


    }

    @FXML
    public void switchToPlanChooser() throws IOException {
        GymnasticsApp.setRoot("choosePlanToBePrinted");
    }



}
