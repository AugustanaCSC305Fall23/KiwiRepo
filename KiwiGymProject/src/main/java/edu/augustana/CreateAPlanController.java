package edu.augustana;

import com.opencsv.bean.CsvToBeanBuilder;
import edu.augustana.cards.Card;
import edu.augustana.filters.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CreateAPlanController  implements Initializable {
    public Button addCardBtn;
    @FXML
    private Button searchButton;
    public CheckBox addedCardsCheckBox;
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
    private Button deleteButton;
    @FXML
    private TextField shortCodeTextBox;
    @FXML
    private TextField superSearchTextBox;
    @FXML
    private Button printPlanButton;
    @FXML
    private Button savePlanButton;
    @FXML
    private Button addPlanButton;
    @FXML
    private GridPane cardGrid;
    @FXML
    private Button changeNameButton;
    @FXML
    private Label currentCourseLabel;
    FileChooser fileChooser = new FileChooser();
    List<CheckBox> genderCBList = new ArrayList<>();
    List<CheckBox> modelCBList = new ArrayList<>();
    List<CardFilter> filterList = new ArrayList<>();
    private List<Card> cardBeans;
    public static TreeItem<String> overallRoot;
    public static TreeItem<String> planItems;
    public static Course course;
    public static Plan currentPlan;
    public int numCreatedPlans;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        createTreeView();

        try {
            cardBeans = new CsvToBeanBuilder(new FileReader("CardPacks/Demo1/Demo1.csv")).withType(Card.class).build().parse();
            cardBeans.addAll(new CsvToBeanBuilder(new FileReader("CardPacks/Demo2/Demo2.csv")).withType(Card.class).build().parse());
            //demoTwoCards = new CsvToBeanBuilder(new FileReader("CardPacks/Demo2.csv")).withType(Card.class).build().parse();
            //cardBeans.addAll(demoTwoCards);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        displayCards(cardBeans);

        //Fix this at some point
        equipmentChoiceBox.getItems().add("ALL");

        for (Card card : cardBeans) {
            if (!eventChoiceBox.getItems().contains(card.getEvent())) {
                eventChoiceBox.getItems().add(card.getEvent());
            }
            if (!categoryChoiceBox.getItems().contains(card.getCategory())) {
                categoryChoiceBox.getItems().add(card.getCategory());
            }
            for (String equipment : card.getEquipment()) {
                if (!equipmentChoiceBox.getItems().contains(equipment)) {
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
        modelCBList.add(maleModel);
        modelCBList.add(femaleModel);
    }

    @FXML
    void savePlan(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save" + course.getName());
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("GymProfCourse (*.GymProfCourse)", "*.GymCourse");
        fileChooser.getExtensionFilters().add(filter);
        Window mainWindow = lessonPlanTreeView.getScene().getWindow();
        File chosenFile = fileChooser.showSaveDialog(mainWindow);
        //saveCurrentCourseToFile(chosenFile);
    }

    @FXML
    public static void loadPlan(File chosenFile) {
        if (chosenFile != null) {
//            try {
//                //GymnasticsApp.loadCurrentCourseFromFile(chosenFile);
//                overallRoot.getChildren().clear();
//                //Course loadedLog = GymnasticsApp.getCurrentCourse();
////                for(Plan plan : loadedLog.getPlanList()){
////                    for (Card card : plan.getCardList()){
////                        TreeItem newCard = new TreeItem(card.getTitle());
////                        overallRoot.getChildren().add(newCard);
////                    }
////                }
//            } catch (IOException ex) {
//                new Alert(Alert.AlertType.ERROR, "Error loading course file: " + chosenFile).show();
//            }
        }
    }
//    private void saveCurrentCourseToFile(File chosenFile) {
//        if (chosenFile != null) {
//            try {
//                GymnasticsApp.saveCurrentCourseToFile(chosenFile);
//            } catch (IOException e) {
//                new Alert(Alert.AlertType.ERROR, "Error saving course to file: " + chosenFile).show();
//            }
//        }
//    }

    @FXML
    void checkCBsFemaleGend(ActionEvent event) {
        for (CheckBox checkBox : genderCBList) {
            if (!checkBox.equals(femaleCheckBox) && checkBox.isSelected()) {
                checkBox.setSelected(false);
            }
        }
    }

    @FXML
    void checkCbsMaleGend(ActionEvent event) {
        for (CheckBox checkBox : genderCBList) {
            if (!checkBox.equals(maleCheckBox) && checkBox.isSelected()) {
                checkBox.setSelected(false);
            }
        }
    }

    @FXML
    void checkCBsMaleModel(ActionEvent event) {
        for (CheckBox checkBox : modelCBList) {
            if (!checkBox.equals(maleModel) && checkBox.isSelected()) {
                checkBox.setSelected(false);
            }
        }
    }

    @FXML
    void checkCBsFemaleModel(ActionEvent event) {
        for (CheckBox checkBox : modelCBList) {
            if (!checkBox.equals(femaleModel) && checkBox.isSelected()) {
                checkBox.setSelected(false);
            }
        }
    }

    @FXML
    void search(ActionEvent event) {
        List<Card> validCards = new ArrayList<>();
        CardFilter filter;
        if (checkIfMultiple()) {
            filter = new CombinedFilter(filterList);
            for (Card card : cardBeans) {
                if (filter.matches(card)) {
                    validCards.add(card);
                }
            }
        } else if (!filterList.isEmpty()) {
            filter = filterList.get(0);
            for (Card card : cardBeans) {
                System.out.println(filter.matches(card));
                if (filter.matches(card)) {
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

    private boolean checkIfMultiple() {
        int numFilters = 0;
        for (CheckBox checkBox : genderCBList) {
            if (checkBox.isSelected()) {
                numFilters++;
                filterList.add(new GenderFilter(checkBox.getText().charAt(0)));
            }
        }
        for (CheckBox checkBox : modelCBList) {
            if (checkBox.isSelected()) {
                numFilters++;
                filterList.add(new ModelFilter(checkBox.getText().charAt(0)));
            }
        }
        if (!eventChoiceBox.getValue().equalsIgnoreCase("ALL")) {
            numFilters++;
            filterList.add(new EventFilter(eventChoiceBox.getValue()));
        }
        if (!difficultyChoiceBox.getValue().equalsIgnoreCase("ALL")) {
            numFilters++;
            filterList.add(new LevelFilter(difficultyChoiceBox.getValue()));
        }
        if (!categoryChoiceBox.getValue().equalsIgnoreCase("ALL")) {
            numFilters++;
            filterList.add(new CategoryFilter(categoryChoiceBox.getValue()));
        }
        if (!equipmentChoiceBox.getValue().equalsIgnoreCase("ALL")) {
            numFilters++;
            filterList.add(new EquipmentFilter(equipmentChoiceBox.getValue()));
        }
        if (!shortCodeTextBox.getText().isEmpty()) {
            numFilters++;
            filterList.add(new CodeFilter(shortCodeTextBox.getText()));
        }
        if (!superSearchTextBox.getText().isEmpty()) {
            numFilters++;
            filterList.add(new SuperSearchFilter(superSearchTextBox.getText()));
        }
        if (favoritesCheckBox.isSelected()) {
            numFilters++;
            filterList.add(new FavoriteFilter());
        }
        return numFilters > 1;
    }

    private void displayCards(List<Card> cardList) {
        while (!cardGrid.getChildren().isEmpty()) {
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

                if (column == 3) {
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
    void switchToMain(ActionEvent event) throws IOException {
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
        TextInputDialog textInputDialog = new TextInputDialog();
        textInputDialog.setHeaderText("Enter Course name");
        textInputDialog.showAndWait();
        String courseName = textInputDialog.getEditor().getText();
        this.course = new Course(courseName);
        currentCourseLabel.setText(courseName);
        currentPlan = new Plan("Plan 1");
        course.addPlan(currentPlan);
        TreeItem<String> coursePlanTree = new TreeItem<>(course.getName());
        overallRoot = coursePlanTree;
        TreeItem<String> currentPlanTree = new TreeItem<String>(currentPlan.getName());
        coursePlanTree.getChildren().add(currentPlanTree);
        lessonPlanTreeView.setRoot(coursePlanTree);
        lessonPlanTreeView.showRootProperty().setValue(false);
        currentPlanTree.setExpanded(true);
        planItems = currentPlanTree;
        numCreatedPlans = 1;
    }


    public static void addCardToTreeView(Card card, String planName) throws IOException {
        int planNum = findPlanIntTreeView(planName);
        String cardEvent = "";
        if (card.getEvent().equals("ALL")) {
            chooseEventWindow();
            cardEvent = ChooseEventController.chosenEvent;
        } else {
            cardEvent = card.getEvent();
        }
        int eventNum = isEventInTreeView(cardEvent, planNum);
        if (eventNum > overallRoot.getChildren().get(planNum).getChildren().size()) {
            TreeItem newEvent = new TreeItem(cardEvent);
            TreeItem newCard = new TreeItem(card.getTitle());
            newEvent.getChildren().add(newCard);
            overallRoot.getChildren().get(planNum).getChildren().add(newEvent);
        } else {
            TreeItem newCard = new TreeItem(card.getTitle());
            overallRoot.getChildren().get(planNum).getChildren().get(eventNum).getChildren().add(newCard);
        }
        course.addCardToPlan(planName, card);
    }

    private static int isEventInTreeView(String event, int planNum) throws IOException {
        int count = 0;
        for (TreeItem existingEvent : overallRoot.getChildren().get(planNum).getChildren()) {
            if (existingEvent.getValue().equals(event)) {
                return count;
            } else {
                count++;
            }
        }
        return count + 1;
    }

    @FXML
    public static void chooseEventWindow() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ChooseEventController.class.getResource("chooseEvent.fxml"));
        Parent root = fxmlLoader.load();
        ChooseEventController controller = fxmlLoader.getController();
        Scene chooseEventscene = new Scene(root);
        // make new stage and set the scene to choose plan window, and showAndWait the stage
        Stage stage1 = new Stage();
        stage1.setScene(chooseEventscene);
        stage1.showAndWait();
    }

    private static int findPlanIntTreeView(String planName) {
        int count = 0;
        for (TreeItem currentPlan : overallRoot.getChildren()) {
            if (currentPlan.getValue().equals(planName)) {
                return count;
            } else {
                count++;
            }
        }
        return count;
    }

    @FXML
    void addPlanButton(ActionEvent event) {
        numCreatedPlans++;
        Plan newPlan = new Plan("Plan " + numCreatedPlans);
        course.addPlan(newPlan);
        TreeItem<String> newPlanTree = new TreeItem<String>(newPlan.getName());
        overallRoot.getChildren().add(newPlanTree);
    }

    @FXML
    void setDeleteButton(ActionEvent event) {
        System.out.println(Course.getPlanList());
        TreeItem item = lessonPlanTreeView.getSelectionModel().getSelectedItem();
        if (!(item == null)) {
            if(course.getPlanList().size() < 2){
                new Alert(Alert.AlertType.ERROR, "You only have one more plan!").showAndWait();
            }else {
                item.getParent().getChildren().remove(item);
                if (course.getListPlanNames().contains(item.getValue())) {
                    course.removePlan((String) item.getValue());
                } else {
                    for (Plan plan : course.getPlanList()) {
                        if (plan.getEventList().contains(item.getValue())) {
                            plan.removeEvent((String) item.getValue());
                        } else {
                            List<Card> fakeCardList = new ArrayList<>(plan.getCardList());
                            for (Card card : fakeCardList) {
                                if (card.getTitle().equals(item.getValue())) {
                                    plan.removeCard((String) item.getValue());
                                }
                            }
                        }
                    }
                }
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Select something to delete!").showAndWait();
        }
    }

    @FXML
    void setChangeNameButton(ActionEvent event) {
        TreeItem item = lessonPlanTreeView.getSelectionModel().getSelectedItem();
        if (!(item == null)) {
            if (item.getParent() != overallRoot) {
                new Alert(Alert.AlertType.ERROR, "You can only change the name of plans!").showAndWait();
            } else {
                TextInputDialog textInputDialog = new TextInputDialog();
                textInputDialog.setHeaderText("Enter new name");
                textInputDialog.showAndWait();
                String newName = textInputDialog.getEditor().getText();
                if (!newName.equals("")) {
                    course.changePlanName(newName, (String) item.getValue());
                    item.setValue(newName);
                }
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Select something to edit!").showAndWait();
        }
    }
}