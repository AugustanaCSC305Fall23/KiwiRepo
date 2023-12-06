package edu.augustana;

import edu.augustana.cards.Card;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class PopupLoader {

    public static void cardPopupWindow(Card card) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(PopUpCardController.class.getResource("PopUpCard.fxml"));
            Parent root = fxmlLoader.load();
            PopUpCardController controller = fxmlLoader.getController();
            controller.setCardToShow(card);
            Scene popupScene = new Scene(root);

            // make new stage and set the scene to popupScene, and showAndWait the stage
            Stage stage = new Stage();
            stage.setScene(popupScene);
            stage.showAndWait();
        } catch (IOException e) {
            handleException(e);
        }
    }

    public static void handleException(Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("An error occurred");
        alert.setContentText("Please contact support for assistance.");

        alert.showAndWait();
    }
}