package edu.augustana;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
public class CreateAPlanController {
    @FXML
    private Button addCardBtn;
    @FXML
    private CheckBox addedCardsCheckBox;
    @FXML
    private Button backButton;
    @FXML
    private ChoiceBox<?> categoryChoiceBox;
    @FXML
    private ChoiceBox<?> difficultyChoiceBox;
    @FXML
    private ChoiceBox<?> equipmentChoiceBox;
    @FXML
    private ChoiceBox<?> eventChoiceBox;
    @FXML
    private CheckBox favoritesCheckBox;
    @FXML
    private CheckBox femaleCheckBox;
    @FXML
    private CheckBox maleCheckBox;
    @FXML
    private CheckBox neutralCheckBox;
    @FXML
    private ScrollBar scrollBar;
    @FXML
    private TextField shortCodeTextBox;
    @FXML
    private Button modifyPlanButton;
    @FXML
    private Button printPlanButton;
    @FXML
    private Button savePlanButton;
    FileChooser fileChooser = new FileChooser();
    public void initialize(){
        fileChooser.setTitle("Save");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All Files", "*.*"));
    }

    // Allows user to access the Menu page from the CreateAPlan page
    @FXML
    void switchToMain(ActionEvent event) throws IOException{
        GymnasticsApp.setRoot("MenuPage");
    }

    //Allows user to access the Filter page from the CreateAPlan page
    @FXML
    void switchToFilter(ActionEvent event) throws IOException{
        GymnasticsApp.setRoot("Filter");
    }

    @FXML
    private void switchToAddCard(ActionEvent event) throws IOException {
        GymnasticsApp.setRoot("addCard");
    }


    @FXML
    void modifyPlan(ActionEvent event) {

    }

    @FXML
    void printPlan(ActionEvent event) {

    }
    @FXML
    void savePlan(ActionEvent event) {
        Stage stage = new Stage();
        fileChooser.showSaveDialog(stage);
    }
}