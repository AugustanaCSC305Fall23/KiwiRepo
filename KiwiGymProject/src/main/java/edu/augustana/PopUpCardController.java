package edu.augustana;

import edu.augustana.cards.Card;
import edu.augustana.cards.CardPrinter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class PopUpCardController extends FavoriteCardCollection {

    @FXML
    private ImageView popUpImageView;
    @FXML
    private Button addToPlanButton;

    @FXML
    private Button printCardBtn;

    @FXML
    private AnchorPane popUpPane;

    @FXML
    private CheckBox favoriteCheckBox;
    private Card card;




    public void setCardToShow(Card card) {
        this.card = card;
        Image cardImage = new Image("file:CardPacks/"+card.getPackFolder()+"/"+card.getImage());
        popUpImageView.setImage(cardImage);

    }
    @FXML
    public void addToPlanButton(ActionEvent event){
        try {
            choosePlanWindow(card);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    };
    @FXML
    public void choosePlanWindow(Card card) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ChoosePlanController.class.getResource("choosePlan.fxml"));
        Parent root = fxmlLoader.load();
        ChoosePlanController controller = fxmlLoader.getController();
        controller.setCard(card);
        Scene choosePlanScene = new Scene(root);
        // make new stage and set the scene to choose plan window, and showAndWait the stage
        Stage stage1 = new Stage();
        stage1.setScene(choosePlanScene);
        stage1.showAndWait();
        popUpImageView.getScene().getWindow().hide();
    }

    @FXML
    public void onPrintButtonAction(ActionEvent event) {
        new CardPrinter().printCard(popUpImageView);
    }

    @FXML
    void closePopUp(ActionEvent event) {
        popUpImageView.getScene().getWindow().hide();
    }

    @FXML
    void favorite(ActionEvent event){
        if (favoriteCheckBox.isSelected()){
            if (!getFavorite().contains(card)){
                setFavorite(card);
            }
        } else {
            if (getFavorite().contains(card)){
                removeFav(card);
            }
        }
    }
}
