package edu.augustana;

import edu.augustana.cards.Card;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.IOException;

import static edu.augustana.PopupLoader.handleException;


public class CardViewController {

    @FXML
    private ImageView cardImage;

    @FXML
    private VBox cardVBox;

    public void setImgView(Card card) {
        try {
            displayThumbnailImage(card);
            //displayDummyCards();
            setCardVBoxClickListener(card);
        } catch (Exception e) {
            handleException(e);
        }

    }

    public void setImgView(Image img){
        cardImage.setImage(img);
    }

    private void setCardVBoxClickListener(Card card) {
        cardVBox.setOnMouseClicked(e -> {
            try {
                PopupLoader.cardPopupWindow(card);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    private void displayThumbnailImage(Card card) {
        String thumbNailName = card.getImage().replace(".png",".jpg");

        Image cardImage = new Image("file:CardPacks/" + card.getPackFolder() + "/thumbs/" + thumbNailName);

        this.cardImage.setImage(cardImage);
    }

    private void displayDummyCards(){

        for(int i = 0; i < 900; i++){
            Image cardImage = new Image("file:CardPacks/StressTest/" + i +".jpg");

            this.cardImage.setImage(cardImage);
        }

    }
}