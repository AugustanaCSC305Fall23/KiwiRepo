package edu.augustana;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

/**
 * JavaFX App
 */
public class GymnasticsApp extends Application {

    private static Scene scene;
    private static Course currentCourse = new Course("Course 1");
    private static File currentCourseFile = null;


    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("MenuPage"), 700, 500);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GymnasticsApp.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
    public static void loadCurrentCourseFromFile(File courseFile) throws IOException {
        currentCourse = Course.loadFromFile(courseFile);
        currentCourseFile = courseFile;
    }
    public static void saveCurrentCourseToFile(File chosenFile) throws IOException {
        currentCourse.saveToFile(chosenFile);
        currentCourseFile = chosenFile;
    }
    public static Course getCurrentCourse(){
        return currentCourse;
    }


    public static void main(String[] args) {
        launch();
    }

}