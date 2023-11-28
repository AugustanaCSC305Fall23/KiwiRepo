package edu.augustana;

import edu.augustana.cards.Card;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;


public class CardViewController {

    @FXML
    private ImageView cardImage;

    @FXML
    private VBox cardVBox;

    private static final String CARD_IMAGE_PATH = "CardPacks/DEMO1Pack/";

    public void setImgView(Card card) {
        try {
            setImage(card);
            setCardVBoxClickListener(card);
        } catch (Exception e) {
            handleException(e);
        }
    }

    private void setImage(Card card) {
        Image cardImage = new Image("file:" + CARD_IMAGE_PATH + card.getImage());
        this.cardImage.setImage(cardImage);
    }

    private void setCardVBoxClickListener(Card card) {
        cardVBox.setOnMouseClicked(e -> PopupLoader.cardPopupWindow(card));
    }

    private void handleException(Exception e) {
        PopupLoader.handleException(e);
    }
}