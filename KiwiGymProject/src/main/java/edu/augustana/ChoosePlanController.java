package edu.augustana;

import edu.augustana.cards.Card;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.io.IOException;

public class ChoosePlanController {

    @FXML
    private Button backBtn;

    @FXML
    private ChoiceBox<String> choosePlanChoiceBox;

    @FXML
    private Button finishAddToPlanButton;

    @FXML
    private Button printSelectedPlanButton;

    private Card cardToAdd;
    private Stage stage;
    public ChoosePlanController() {
    }
    @FXML
    public void initialize(){
        updateChoiceBox();
    }

    @FXML
    private void updateChoiceBox(){
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
        CreateAPlanController.addCardToCourse(cardToAdd, choosePlanChoiceBox.getValue());
        finishAddToPlanButton.getScene().getWindow().hide();
    }


    @FXML
    private void switchToPrintView() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PrintView.fxml"));
        Parent root = loader.load();
        PrintViewController printViewController = loader.getController();
        printViewController.setPlan(choosePlanChoiceBox.getValue());

        printViewController.setPrintPreview();
        GymnasticsApp.setRoot(root);

    }

    @FXML
    private void switchToCreateAPlan() throws IOException {
        GymnasticsApp.setRoot("CreateAPlan");
    }

    @FXML
    public void printLessonPlanImages() throws IOException {
        PrintViewController printViewController = new PrintViewController();
        printViewController.setPlan(choosePlanChoiceBox.getValue());
        printViewController.setPrintPreview();
        //CreateAPlanController.printCardsFromPlan(choosePlanChoiceBox.getValue());
        printSelectedPlanButton.getScene().getWindow().hide();

    }

}
