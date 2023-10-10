package edu.augustana;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;


public class PrimaryController {


    @FXML
    private Button giveInformation;

    @FXML
    void informationButton(ActionEvent event) {
        System.out.println("Developed by team Kiwi");

    }
    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
}
