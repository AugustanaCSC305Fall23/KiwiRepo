package edu.augustana;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.augustana.cards.Card;

import java.io.*;
import java.util.*;
//for the list of plans in the tree view
public class Course {
    private String name;
    private static List<Plan> listOfPlans;
    private static List<String>listPlanNames;

    public Course(String name){
        listOfPlans = new ArrayList<>();
        listPlanNames = new ArrayList<>();
        this.name = name;
    }
    public void addPlan(Plan plan){
        listOfPlans.add(plan);
        listPlanNames.add(plan.getName());
    }

    public void removePlan(String planName){
        listOfPlans.remove(listPlanNames.indexOf(planName));
        listPlanNames.remove(planName);
    }
    public void addCardToPlan(String plan, Card card){
        Plan selectedPlan = listOfPlans.get(listPlanNames.indexOf(plan));
        selectedPlan.addCard(card);
    }
    public String getName(){
        return name;
    }
    public void changePlanName(String newName, String oldName){
        listOfPlans.get(listPlanNames.indexOf(oldName)).changeName(newName);
        listPlanNames.add(listPlanNames.indexOf(oldName), newName);
        listPlanNames.remove(oldName);
    }
    public static List<Plan> getPlanList(){
        return listOfPlans;
    }
    public static List<String> getListPlanNames(){
        return listPlanNames;
    }
    public static Course loadFromFile(File logFile) throws IOException {
        FileReader reader = new FileReader(logFile);
        Gson gson = new Gson();
        return gson.fromJson(reader,Course.class);
    }
    public void saveToFile(File logFile) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Map<String, List<Plan>> courseMap = new HashMap<>();
        courseMap.put(name, listOfPlans);

        String serializedGymCourse = gson.toJson(courseMap);
        PrintWriter writer = new PrintWriter(new FileWriter(logFile));
        writer.print(serializedGymCourse);
        writer.close();
    }
//    public void saveToFile(File logFile) throws IOException {
//        Gson gson = new GsonBuilder().setPrettyPrinting().create();
//        String serializedGymCourse = gson.toJson(this);
//        PrintWriter writer = new PrintWriter(new FileWriter(logFile));
//        writer.print(serializedGymCourse);
//        for (Plan plan : listOfPlans){
//            String serializedPlan = gson.toJson(plan);
//            writer.println(serializedPlan);
//        }
//        writer.close();
//    }
}
