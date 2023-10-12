package edu.augustana;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class CreateAPlanController {
    @FXML
    private Button backButton;
    @FXML
    private Button filterAccessButton;
    @FXML
    private Button addCardBtn;

    @FXML
    private Button modifyPlanButton;

    @FXML
    private Button printPlanButton;

    @FXML
    private Button savePlanButton;


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

    }
}