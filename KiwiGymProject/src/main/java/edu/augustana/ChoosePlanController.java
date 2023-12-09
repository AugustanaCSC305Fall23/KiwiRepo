package edu.augustana;

import edu.augustana.cards.Card;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class ChoosePlanController {
    @FXML
    private ChoiceBox<String> choosePlanChoiceBox;
    @FXML
    private Button finishAddToPlanButton;
    private Card cardToAdd;
    private Stage stage;
    public ChoosePlanController() {
    }
    @FXML
    public void initialize(){
        updateChoiceBox();
    }

    @FXML
    public void updateChoiceBox(){
        choosePlanChoiceBox.getItems().clear();
        for (Plan plan : Course.getPlanList()){
            choosePlanChoiceBox.getItems().add(plan.getName());
        }
        choosePlanChoiceBox.setValue(Course.getPlanList().get(0).getName());
    }
    public void setCard(Card card){
        cardToAdd = card;
    }
    @FXML
    private void setFinishAddToPlanButton(ActionEvent event)throws IOException{
        CreateAPlanController.addCardToTreeView(cardToAdd, choosePlanChoiceBox.getValue());
        finishAddToPlanButton.getScene().getWindow().hide();
    }

}
