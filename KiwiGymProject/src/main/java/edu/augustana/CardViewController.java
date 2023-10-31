package edu.augustana;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CardViewController {

    @FXML
    private ImageView cardImage;


    public void setImgView(Card card){

        System.out.println("CardPacks/DEMO1Pack/"+card.getImage());
        Image cardImage = new Image("file:CardPacks/DEMO1Pack/"+card.getImage());
        this.cardImage.setImage(cardImage);


    }

}
