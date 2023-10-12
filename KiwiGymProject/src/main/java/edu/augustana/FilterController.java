package edu.augustana;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class FilterController {
    @FXML
    private Button backSearchBtn;

    @FXML
    private Button searchBtn;
    //Allows access to the CreateAPlan page via the Filter page
    @FXML
    private void backFromSearchAction(ActionEvent event) throws IOException{
        GymnasticsApp.setRoot("CreateAPlan");
    }
}
