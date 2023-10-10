package edu.augustana;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

<<<<<<< HEAD:KiwiGymProject/src/main/java/edu/augustana/CreateAPlanController.java
public class CreateAPlanController {
=======

public class SecondaryController {
>>>>>>> 82a2f5e1c2c2edf0d2f6ec1db9c312bd5c06e7fc:KiwiGymProject/src/main/java/edu/augustana/SecondaryController.java
    @FXML
    private Button backButton;
    @FXML
    void switchToMain(ActionEvent event) throws IOException{
        GymnasticsApp.setRoot("MenuPage");
    }
}