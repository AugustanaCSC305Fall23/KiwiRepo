package edu.augustana;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MenuPageController {

    @FXML
    private Button createAPlan;

    @FXML
    private void switchToSecondary(ActionEvent event) throws IOException {
        GymnasticsApp.setRoot("CreateAPlan");
    }
}
