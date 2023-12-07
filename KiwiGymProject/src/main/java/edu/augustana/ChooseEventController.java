package edu.augustana;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ChooseEventController {

    @FXML
    private ChoiceBox<String> chooseEventChoiceBox;

    @FXML
    private Button finishChooseEventButton;
    public static String chosenEvent;
    public void initialize(){
        String[] eventList = new String[]{"Uneven Bars", "Beam", "Vault", "Tramp", "Strength", "Pommel Horse", "Rings", "Parallel Bars", "Horizontal Bar"};
        for(String event : eventList){
            chooseEventChoiceBox.getItems().add(event);
        }
        chooseEventChoiceBox.setValue(chooseEventChoiceBox.getItems().get(0));
    }
    @FXML
    private void setFinishChooseEventButton(ActionEvent event)throws IOException {
        chosenEvent = chooseEventChoiceBox.getValue();
        finishChooseEventButton.getScene().getWindow().hide();


    }
}
