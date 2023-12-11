package edu.augustana;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.opencsv.bean.CsvToBeanBuilder;
import edu.augustana.cards.Card;
import edu.augustana.filters.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.print.PageLayout;
import javafx.print.PrinterJob;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class CreateAPlanController  implements Initializable {

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
    public static boolean loadCourse = false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(course == null){
            createTreeView();
        }else{
            try{
                loadTreeView();
            }catch (IOException e){
                throw new RuntimeException();
            }
        }

        Path startPath = Paths.get("CardPacks"); // replace with your directory path
        cardBeans = new ArrayList<>();
        try (Stream<Path> stream = Files.walk(startPath)) {
            stream.filter(Files::isRegularFile) // Filter to find only files
                    .filter(path -> path.toString().endsWith(".csv")) // Filter for .csv files
                    .forEach(path -> {
                        try {
                            cardBeans.addAll(new CsvToBeanBuilder(new FileReader(path.toFile())).withType(Card.class).build().parse());
                        } catch (FileNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();

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
        if (loadCourse){
            Map<String, List<Plan>> courseMap = MenuPageController.getCourseMap();
            try {
                loadTreeView(courseMap);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


    @FXML
    void saveCourse(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save " + course.getName());
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("GymProfCourse (*.GymProfCourse)", "*.GymCourse");
        fileChooser.getExtensionFilters().add(filter);
        Window mainWindow = lessonPlanTreeView.getScene().getWindow();
        File chosenFile = fileChooser.showSaveDialog(mainWindow);

        GymnasticsApp.saveCurrentCourseToFile(chosenFile, course);
    }



    /**loads a plan from the chosen file
     *
     * @param chosenFile the file that the plan is in
     */
    @FXML
    public static Map<String, List<Plan>> loadCourse(File chosenFile) {
        if (chosenFile != null) {
            try {
                FileReader reader = new FileReader(chosenFile);
                Type type = new TypeToken<Map<String, List<Plan>>>(){}.getType();
                Gson gson = new Gson();
                Map<String, List<Plan>> map =  gson.fromJson(reader, type);
                return map;
            } catch (IOException ex) {
                new Alert(Alert.AlertType.ERROR, "Error loading course file: " + chosenFile).show();
            }
        }
        return null;
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

    /*
    displays the cards
     */
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
    @FXML
    void switchToMain(ActionEvent event) throws IOException {
        GymnasticsApp.setRoot("MenuPage");
    }

    @FXML
    private void switchToAddCard(ActionEvent event) throws IOException {
        GymnasticsApp.setRoot("addCard");
    }


    @FXML
    void printPlan(ActionEvent event) throws IOException {
        GymnasticsApp.setRoot("choosePlanToBePrinted");
    }
    /*
    creates the tree view and sets the course and first plan to the desired data fields
    */
    @FXML
    private void createTreeView() {
        String courseName = " ";
        if(!loadCourse) {
            while(courseName.length() < 3) {
                TextInputDialog textInputDialog = new TextInputDialog();
                textInputDialog.setHeaderText("Enter Course name");
                textInputDialog.showAndWait();
                courseName = textInputDialog.getEditor().getText();
            }
        }
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
    private void loadTreeView(Map<String, List<Plan>> map) throws IOException {
        for(String courseName : map.keySet()){
            course = new Course(courseName);
        }
        currentCourseLabel.setText(course.getName());
        List<Plan> planList = new ArrayList<>();
        planList.addAll(map.get(course.getName()));
        for(Plan plan: planList){
            course.addPlan(plan);
            TreeItem<String> newPlanTree = new TreeItem<String>(plan.getName());
            overallRoot.getChildren().add(newPlanTree);
        }
        for(Plan plan : course.getPlanList()){
            for (Card card : plan.getCardList()){
                String cardEvent = card.getEvent();
                int planNum = findPlanIntTreeView(plan.getName());
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
            }
        }
    }
    private void loadTreeView()throws IOException{
        currentCourseLabel.setText(course.getName());
        TreeItem<String> coursePlanTree = new TreeItem<>(course.getName());
        overallRoot = coursePlanTree;
        lessonPlanTreeView.showRootProperty().setValue(false);
        lessonPlanTreeView.setRoot(overallRoot);
        for(Plan plan: course.getPlanList()){
            TreeItem<String> newPlanTree = new TreeItem<String>(plan.getName());
            overallRoot.getChildren().add(newPlanTree);
        }
        for(Plan plan : course.getPlanList()){
            for (Card card : plan.getCardList()){
                String cardEvent = card.getEvent();
                int planNum = findPlanIntTreeView(plan.getName());
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
            }
        }
    }

    /** adds a card to the course
     *
     * @param card the card being added
     * @param planName the name of the plan the card is being added to
     * @throws IOException
     */
    public static void addCardToCourse(Card card, String planName) throws IOException {
        int planNum = findPlanIntTreeView(planName);
        String cardEvent = "";
        if (card.getEvent().equals("ALL")) {
            chooseEventWindow();
            cardEvent = ChooseEventController.chosenEvent;
        } else {
            cardEvent = card.getEvent();
        }
        if (cardEvent.equals("Choose Event")){
            new Alert(Alert.AlertType.ERROR, "You did not choose an event!").showAndWait();
        }else {
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
    }

    /*
    checks if the event is already in the tree view
     */

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

    /** craetes a choose event window for the cards that are part of "ALL" events
     *
     * @throws IOException
     */
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
    /*
    finds a plan in the tree view using the name
     */
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
    /*
    deletes items from the course
     */
    @FXML
    void setDeleteButton(ActionEvent event) {
        TreeItem item = lessonPlanTreeView.getSelectionModel().getSelectedItem();
        if (!(item == null)) {
            if(course.getPlanList().size() < 2 && item.getParent() == overallRoot){
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
    /*
    changes the name of the selected plan
     */
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
    public static void changeLoadPlan(){
        loadCourse = !loadCourse;
    }
}