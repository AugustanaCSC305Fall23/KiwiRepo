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

    /**
     * adds all possible events to the choice box
     */
    public void initialize(){
        String[] eventList = new String[]{"Floor", "Uneven Bars", "Beam", "Vault", "Tramp", "Strength", "Pommel Horse", "Rings", "Parallel Bars", "Horizontal Bar"};
        for(String event : eventList){
            chooseEventChoiceBox.getItems().add(event);
        }
        chooseEventChoiceBox.setValue("Choose Event");
    }
    @FXML
    private void setFinishChooseEventButton(ActionEvent event)throws IOException {
        chosenEvent = chooseEventChoiceBox.getValue();
        finishChooseEventButton.getScene().getWindow().hide();
    }
}
