package edu.augustana;

import java.io.File;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Window;

public class MenuPageController {

    @FXML
    private Button giveInformation;

    @FXML
    private Button loadPlan;
    @FXML
    private Button createAPlan;

    // Allows accessibility to information about the development team
    @FXML
    void informationButton(ActionEvent event) {
        new Alert(Alert.AlertType.INFORMATION, "Developed by Team Kiwi.").showAndWait();
    }

    // Allows the user access to the CreateAPlan page from the menu
    @FXML
    private void switchToCreateAPlan(ActionEvent event) throws IOException {
        GymnasticsApp.setRoot("CreateAPlan");
    }
    @FXML
    private void setLoadPlan(ActionEvent event) throws IOException{
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Load Course File");
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("GymProfCourse (*.GymProfCourse)", "*.GymCourse");
        fileChooser.getExtensionFilters().add(filter);
        Window mainWindow = loadPlan.getScene().getWindow();
        File chosenFile = fileChooser.showOpenDialog(mainWindow);
        CreateAPlanController.loadPlan(chosenFile);
        GymnasticsApp.setRoot("CreateAPlan");
    }
}
