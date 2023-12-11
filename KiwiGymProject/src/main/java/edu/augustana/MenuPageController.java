package edu.augustana;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

public class MenuPageController {

    @FXML
    private Button giveInformation;

    @FXML
    private Button loadPlan;
    @FXML
    private Button createAPlan;
    private static Map<String, List<Plan>> courseMap;

    /*
    shows a popup of information from the development team
     */
    @FXML
    void informationButton(ActionEvent event)throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ChoosePlanController.class.getResource("InformationBox.fxml"));
        Parent root = fxmlLoader.load();
        ChoosePlanController controller = fxmlLoader.getController();
        Scene inforBoxScene = new Scene(root);
        // make new stage and set the scene to choose plan window, and showAndWait the stage
        Stage stage1 = new Stage();
        stage1.setScene(inforBoxScene);
        stage1.showAndWait();
    }

    /*
     Allows the user access to the CreateAPlan page from the menu
     */
    @FXML
    private void switchToCreateAPlan(ActionEvent event) throws IOException {
        GymnasticsApp.setRoot("CreateAPlan");
    }
    public static Map<String, List<Plan>> getCourseMap(){
        return courseMap;
    }
    /*
    loads a saved plan from the current computer
     */
    @FXML
    private void setLoadPlan(ActionEvent event) throws IOException{
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Load Course File");
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("GymProfCourse (*.GymProfCourse)", "*.GymCourse");
        fileChooser.getExtensionFilters().add(filter);
        Window mainWindow = loadPlan.getScene().getWindow();
        File chosenFile = fileChooser.showOpenDialog(mainWindow);
        courseMap = CreateAPlanController.loadCourse(chosenFile);
        CreateAPlanController.changeLoadPlan();
        GymnasticsApp.setRoot("CreateAPlan");
    }
}
