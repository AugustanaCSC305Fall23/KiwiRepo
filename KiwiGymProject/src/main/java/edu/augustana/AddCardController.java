package edu.augustana;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import java.io.IOException;

public class AddCardController {
    @FXML
    private Button backButton;
    @FXML
    private ComboBox<?> eventChoiceBox;
    @FXML
    private void backFromCreateCardAction(ActionEvent event) throws IOException {
        GymnasticsApp.setRoot("CreateAPlan");
    }

}
