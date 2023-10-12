package edu.augustana;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class CreateAPlanController {
    @FXML
    private Button backButton;
    @FXML
    private Button filterAccessButton;
    @FXML
    void switchToMain(ActionEvent event) throws IOException{
        GymnasticsApp.setRoot("MenuPage");
    }
    @FXML
    void switchToFilter(ActionEvent event) throws IOException{
        GymnasticsApp.setRoot("Filter");
    }
}