package edu.augustana;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
<<<<<<< HEAD:KiwiGymProject/src/main/java/edu/augustana/MenuPageController.java

public class MenuPageController {
=======
import javafx.event.ActionEvent;
import javafx.stage.Stage;


public class PrimaryController {
>>>>>>> 82a2f5e1c2c2edf0d2f6ec1db9c312bd5c06e7fc:KiwiGymProject/src/main/java/edu/augustana/PrimaryController.java


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
        GymnasticsApp.setRoot("CreateAPlan");
    }
}
