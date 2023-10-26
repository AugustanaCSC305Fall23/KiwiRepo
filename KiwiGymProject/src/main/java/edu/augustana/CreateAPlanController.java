package edu.augustana;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

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
}