package edu.augustana;

import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;

public class LoadingScreen {
    @FXML
    private ProgressBar progressBar;

    public void initialize() {
        // Perform loading tasks here and update the progress bar.
        // You can use background threads or timers for the loading.
        // Once loading is complete, transition to the main screen.
    }
}