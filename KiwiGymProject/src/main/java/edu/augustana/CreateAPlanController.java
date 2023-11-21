package edu.augustana;

import com.opencsv.bean.CsvToBeanBuilder;
import edu.augustana.cards.Card;
import edu.augustana.filters.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CreateAPlanController  implements Initializable{
    public Button addCardBtn;
    @FXML
    private Button searchButton;
    public CheckBox addedCardsCheckBox;
    //@FXML
    //private Button addCardBtn;
    //@FXML
    //private CheckBox addedCardsCheckBox;
    @FXML
    private Button backButton;
    @FXML
    private ChoiceBox<String> categoryChoiceBox;
    @FXML
    private ChoiceBox<String> difficultyChoiceBox;
    @FXML
    private ChoiceBox<String> equipmentChoiceBox;
    @FXML
    private ChoiceBox<String> eventChoiceBox;
    @FXML
    private CheckBox favoritesCheckBox;
    @FXML
    private CheckBox femaleCheckBox;
    @FXML
    private CheckBox maleCheckBox;
    @FXML
    private CheckBox neutralCheckBox;
    @FXML
    private TreeView<String> lessonPlanTreeView;
    @FXML
    private StackPane treeViewStackedPane;
    @FXML
    private ScrollBar scrollBar;
    @FXML
    private CheckBox maleModel;
    @FXML
    private CheckBox femaleModel;

    @FXML
    private TextField shortCodeTextBox;
    @FXML
    private TextField keywordTextBox;
    @FXML
    private Button printPlanButton;
    @FXML
    private Button savePlanButton;
    @FXML
    private GridPane cardGrid;
    FileChooser fileChooser = new FileChooser();
    List<CheckBox> genderCBList = new ArrayList<>();
    List<CheckBox> modelCBList = new ArrayList<>();
    List<CardFilter> filterList = new ArrayList<>();
    private List<Card> cardBeans;
    public TreeItem<String> weekOneItems;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        createTreeView();
        //fileChooser.setTitle("Save");
        //fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All Files", "*.*"));
        try {
            cardBeans = new CsvToBeanBuilder(new FileReader("CardPacks/DEMO1.csv")).withType(Card.class).build().parse();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        displayCards(cardBeans);
        //Find way to read cards and insert these values in automatically

        //Fix this at some point
        equipmentChoiceBox.getItems().add("ALL");

        for (Card card : cardBeans){
            if (!eventChoiceBox.getItems().contains(card.getEvent())){
                eventChoiceBox.getItems().add(card.getEvent());
            }
            if (!categoryChoiceBox.getItems().contains(card.getCategory())){
                categoryChoiceBox.getItems().add(card.getCategory());
            }
            for (String equipment : card.getEquipment()){
                if (!equipmentChoiceBox.getItems().contains(equipment)){
                    equipmentChoiceBox.getItems().add(equipment);
                }
            }

         }
        //Fix this at some point
        eventChoiceBox.getItems().remove("Uneven bars");
        difficultyChoiceBox.getItems().addAll("ALL", "B", "AB", "I", "A");
        eventChoiceBox.setValue("ALL");
        difficultyChoiceBox.setValue("ALL");
        categoryChoiceBox.setValue("ALL");
        equipmentChoiceBox.setValue("ALL");
        genderCBList.add(femaleCheckBox);
        genderCBList.add(maleCheckBox);
        genderCBList.add(neutralCheckBox);
        modelCBList.add(maleModel);
        modelCBList.add(femaleModel);
    }

    @FXML
    void savePlan(ActionEvent event) {
        Stage stage = new Stage();
        fileChooser.showSaveDialog(stage);
    }

    @FXML
    void checkCBsFemaleGend(ActionEvent event){
        for (CheckBox checkBox: genderCBList){
            if (!checkBox.equals(femaleCheckBox) && checkBox.isSelected()){
                checkBox.setSelected(false);
            }
        }
    }

    @FXML
    void checkCbsMaleGend(ActionEvent event){
        for (CheckBox checkBox: genderCBList){
            if (!checkBox.equals(maleCheckBox) && checkBox.isSelected()){
                checkBox.setSelected(false);
            }
        }
    }

    @FXML
    void checkCBsNeutralGend(ActionEvent event){
        for (CheckBox checkBox: genderCBList){
            if (!checkBox.equals(neutralCheckBox) && checkBox.isSelected()){
                checkBox.setSelected(false);
            }
        }
    }

    @FXML
    void checkCBsMaleModel(ActionEvent event){
        for (CheckBox checkBox: modelCBList){
            if (!checkBox.equals(maleModel) && checkBox.isSelected()){
                checkBox.setSelected(false);
            }
        }
    }

    @FXML
    void checkCBsFemaleModel(ActionEvent event){
        for (CheckBox checkBox: modelCBList){
            if (!checkBox.equals(femaleModel) && checkBox.isSelected()){
                checkBox.setSelected(false);
            }
        }
    }

    @FXML
    void search(ActionEvent event){
        List<Card> validCards = new ArrayList<>();
        CardFilter filter;
        if (checkIfMultiple()){
            filter = new CombinedFilter(filterList);
            for (Card card : cardBeans){
                if (filter.matches(card)){
                    validCards.add(card);
                }
            }
        } else if (!filterList.isEmpty()){
            filter = filterList.get(0);
            for (Card card : cardBeans){
                if (filter.matches(card)){
                    validCards.add(card);
                }
            }
        } else {
            validCards.addAll(cardBeans);
        }

        displayCards(validCards);
        filterList.clear();
        validCards.clear();
    }

    private boolean checkIfMultiple(){
        int numFilters = 0;
        for (CheckBox checkBox : genderCBList){
            if (checkBox.isSelected()){
                numFilters++;
                filterList.add(new GenderFilter(checkBox.getText().charAt(0)));
            }
        }
        for (CheckBox checkBox : modelCBList){
            if (checkBox.isSelected()){
                numFilters++;
                filterList.add(new ModelFilter(checkBox.getText().charAt(0)));
            }
        }
        if (!eventChoiceBox.getValue().equalsIgnoreCase("ALL")){
            numFilters++;
            filterList.add(new EventFilter(eventChoiceBox.getValue()));
        }
        if (!difficultyChoiceBox.getValue().equalsIgnoreCase("ALL")){
            numFilters++;
            filterList.add(new LevelFilter(difficultyChoiceBox.getValue()));
        }
        if (!categoryChoiceBox.getValue().equalsIgnoreCase("ALL")) {
            numFilters++;
            filterList.add(new CategoryFilter(categoryChoiceBox.getValue()));
        }
        if (!equipmentChoiceBox.getValue().equalsIgnoreCase("ALL")){
            numFilters++;
            filterList.add(new EquipmentFilter(equipmentChoiceBox.getValue()));
        }
        if (!shortCodeTextBox.getText().isEmpty()){
            numFilters++;
            filterList.add(new CodeFilter(shortCodeTextBox.getText()));
        }
        if (!keywordTextBox.getText().isEmpty()){
            numFilters++;
            filterList.add(new KeyWordFilter(keywordTextBox.getText()));
        }
        return numFilters > 1;
    }

    private void displayCards(List<Card> cardList){
        while (!cardGrid.getChildren().isEmpty()){
            cardGrid.getChildren().remove(0);
        }
        int column = 0;
        int row = 1;

        try {
            for (Card cardBean : cardList) {
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




    }

    // Allows user to access the Menu page from the CreateAPlan page
    @FXML
    void switchToMain(ActionEvent event) throws IOException{
        GymnasticsApp.setRoot("MenuPage");
    }

    //Allows user to access the Filter page from the CreateAPlan page

    @FXML
    private void switchToAddCard(ActionEvent event) throws IOException {
        GymnasticsApp.setRoot("addCard");
    }


    @FXML
    void printPlan(ActionEvent event) throws IOException {
        GymnasticsApp.setRoot("PrintView");
    }

    @FXML
    private void createTreeView() {
        Plan plan1 = new Plan("Plan 1");
        PlanList.PlanList();
        PlanList.addPlan(plan1);
        ChoosePlanController.ChoosePlanController();
        ChoosePlanController.addToChoiceBoxPlans(plan1);
        TreeItem<String> rootItem = new TreeItem<String>("Week 1");
        lessonPlanTreeView.setRoot(rootItem);
        rootItem.getChildren().add(new TreeItem<String>(plan1.getName()));
        rootItem.setExpanded(true);
        weekOneItems = rootItem;
    }


    public void addCardToTreeView(Card card){
        TreeItem newCard = new TreeItem(card.getTitle());
        weekOneItems.getChildren().add(newCard);
    }
    //@FXML
    //private void switchToAddCard(ActionEvent event) throws IOException {
    //    GymnasticsApp.setRoot("addCard");
    //}
    @FXML
    private void addCard(ActionEvent event) throws IOException {

    }

    @FXML
    void modifyPlan(ActionEvent event) {

    }

}