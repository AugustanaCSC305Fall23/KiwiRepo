package edu.augustana;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class SecondaryController {
    @FXML
    private Button backButton;
    @FXML
    private void backButtonAction() throws IOException {
        App.setRoot("primary");
    }
}