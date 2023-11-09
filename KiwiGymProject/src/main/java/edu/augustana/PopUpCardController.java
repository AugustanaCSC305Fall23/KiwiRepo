package edu.augustana;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PopUpCardController {

    @FXML
    private ImageView popUpImageView;


    public void setCardToShow(Card card) {
        Image cardImage = new Image("file:CardPacks/DEMO1Pack/"+card.getImage());
        popUpImageView.setImage(cardImage);

    }

    @FXML
    void closePopUp(ActionEvent event) {
        popUpImageView.getScene().getWindow().hide();
    }
}
