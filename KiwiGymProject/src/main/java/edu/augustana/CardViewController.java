package edu.augustana;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class CardViewController {

    @FXML
    private ImageView cardImage;

    @FXML
    private VBox cardVBox;


    public void setImgView(Card card){

        //System.out.println("CardPacks/DEMO1Pack/"+card.getImage());
        Image cardImage = new Image("file:CardPacks/DEMO1Pack/"+card.getImage());
        this.cardImage.setImage(cardImage);

        cardVBox.setOnMouseClicked(e -> {
            try {
                cardPopupWindow(card);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

    }

    public void cardPopupWindow(Card card) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PopUpCardController.class.getResource("PopUpCard.fxml"));
        Parent root = fxmlLoader.load();
        PopUpCardController controller = fxmlLoader.getController();
        controller.setCardToShow(card);
        Scene popupScene = new Scene(root);

        // make new stage and set the scene to popupScene, and showAndWait the stage
        Stage stage1 = new Stage();
        stage1.setScene(popupScene);
        stage1.showAndWait();
    }

}
