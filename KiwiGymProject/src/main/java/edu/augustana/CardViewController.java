package edu.augustana;

import edu.augustana.cards.Card;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;


public class CardViewController {

    @FXML
    private ImageView cardImage;

    @FXML
    private VBox cardVBox;

    //private static final String CARD_IMAGE_PATH = "CardPacks/DEMO1Pack/";

    public void setImgView(Card card) {
        try {
            displayThumbnailImage(card);
            setCardVBoxClickListener(card);
        } catch (Exception e) {
            handleException(e);
        }


    }

    private void setCardVBoxClickListener(Card card) {
        cardVBox.setOnMouseClicked(e -> PopupLoader.cardPopupWindow(card));
    }

    private void handleException(Exception e) {
        PopupLoader.handleException(e);
    }

    private void displayThumbnailImage(Card card) {
        StringBuilder thumbNail = new StringBuilder();

        for (char c : card.getImage().toCharArray()) {
            if (Character.isDigit(c)) {
                thumbNail.append(c);
            }
        }

        thumbNail.append(".jpg");


        Image cardImage = new Image("file:CardPacks/" + card.getPackFolder() + "/thumbs/" + thumbNail);

        this.cardImage.setImage(cardImage);
    }
}