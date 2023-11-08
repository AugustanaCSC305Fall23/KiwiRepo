package edu.augustana;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.opencsv.bean.CsvToBeanBuilder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class CreateAPlanController  implements Initializable{
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
    private TreeView<Plan> lessonPlanTreeView;
    @FXML
    private StackPane treeViewStackedPane;
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

    @FXML
    private GridPane cardGrid;
    FileChooser fileChooser = new FileChooser();

    private List<Card> cardBeans;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        //fileChooser.setTitle("Save");
        //fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All Files", "*.*"));
        try {
            cardBeans = new CsvToBeanBuilder(new FileReader("CardPacks/DEMO1.csv")).withType(Card.class).build().parse();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        int column = 0;
        int row = 1;

        try {
            for (Card cardBean : cardBeans) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("card.fxml"));
                Pane pane = fxmlLoader.load();
                CardViewController cardController = fxmlLoader.getController();
                cardController.setImgView(cardBean);

                if(column == 3){
                    column = 0;
                    row += 1;
                }

                cardGrid.add(pane, column++, row);
                GridPane.setMargin(pane, new Insets(1));




            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        createTreeView();
    }
    @FXML
    private void createTreeView() {
        lessonPlanTreeView = new TreeView<>();
        TreeItem<Plan> rootItem = new TreeItem<Plan>(new Plan("Week 1"));
        lessonPlanTreeView.setRoot(rootItem);
        rootItem.getChildren().add(new TreeItem<Plan>(new Plan("Plan 1")));
        rootItem.setExpanded(true);
        treeViewStackedPane.getChildren().add(lessonPlanTreeView);
        lessonPlanTreeView.isEditable();
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