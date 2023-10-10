package edu.augustana;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.stage.Stage;


public class PrimaryController {


    @FXML
    private Button giveInformation;

    @FXML
    void informationButton(ActionEvent event) {
        System.out.println("Developed by team Kiwi");

    }
    @FXML
    private Button createAPlan;

    @FXML
    private void switchToSecondary(ActionEvent event) throws IOException {
        App.setRoot("secondary");
    }
}
