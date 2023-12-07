package edu.augustana;

import edu.augustana.cards.Card;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * JavaFX App
 */
public class GymnasticsApp extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("MenuPage"), 700, 500);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();

        try {
            FileInputStream fileIn = new FileInputStream("favorites.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            List<Card> list = (List<Card>) in.readObject();
            for (Card card : list){
                FavoriteCardCollection.setFavorite(card);
            }
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("Employee class not found");
            c.printStackTrace();
        }
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GymnasticsApp.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();

        try {
            FileOutputStream fileOut = new FileOutputStream("favorites.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            ArrayList<Card> list = (ArrayList<Card>) FavoriteCardCollection.getFavorite();
            out.writeObject(list);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

}