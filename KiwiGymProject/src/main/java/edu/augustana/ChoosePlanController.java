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
    @FXML
    private List<Plan> choiceBoxPlans;
    private Card cardToAdd;
    private Stage stage;
    public ChoosePlanController() {
        choiceBoxPlans = Course.getPlanList();
    }
    @FXML
    public void initialize(){
        updateChoiceBox();
    }

    @FXML
    public void addToChoiceBoxPlans(Plan plan){
        choiceBoxPlans.add(plan);
    }
    @FXML
    public void updateChoiceBox(){
        for (Plan plan : choiceBoxPlans){
            choosePlanChoiceBox.getItems().add(plan.getName());
        }
        choosePlanChoiceBox.setValue(choiceBoxPlans.get(0).getName());
    }
    public void setCard(Card card){
        cardToAdd = card;

    }
    @FXML
    private void setFinishAddToPlanButton(ActionEvent event)throws IOException{
        CreateAPlanController.addCardToTreeView(cardToAdd);
        finishAddToPlanButton.getScene().getWindow().hide();

    }

}
