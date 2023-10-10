package edu.augustana;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class SecondaryController {
    @FXML
    private Button backButton;
    @FXML
    void switchToMain(ActionEvent event) throws IOException{
        App.setRoot("primary");
    }
}