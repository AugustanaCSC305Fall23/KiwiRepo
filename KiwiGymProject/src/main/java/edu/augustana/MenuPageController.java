package edu.augustana;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MenuPageController {

    @FXML
    private Button giveInformation;

    // Allows accessibility to information about the development team
    @FXML
    void informationButton(ActionEvent event) {
        new Alert(Alert.AlertType.INFORMATION, "Developed by Team Kiwi.").showAndWait();
    }
    @FXML
    private Button createAPlan;

    // Allows the user access to the CreateAPlan page from the menu
    @FXML
    private void switchToSecondary(ActionEvent event) throws IOException {
        GymnasticsApp.setRoot("CreateAPlan");
    }
}
