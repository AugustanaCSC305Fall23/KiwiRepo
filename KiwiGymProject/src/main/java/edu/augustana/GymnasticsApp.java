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

    /** creates the menu page
     *
     * @param stage the stage that everything is going to be created on
     * @throws IOException
     */
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
    static void saveCurrentCourseToFile(File chosenFile, Course course) {
        if (chosenFile != null && course != null) {
            try {
                course.saveToFile(chosenFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        launch();
    }

}