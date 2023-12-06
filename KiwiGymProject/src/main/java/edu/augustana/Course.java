package edu.augustana;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.*;
//for the list of plans in the tree view
public class Course {
    private String name;
    private static List<Plan> listOfPlans;

    public Course(String name){
        listOfPlans = new ArrayList<>();
        this.name = name;
    }
    public void addPlan(Plan plan){
        listOfPlans.add(plan);
    }
    public String getName(){
        return name;
    }
    public static List<Plan> getPlanList(){
        return listOfPlans;
    }
    public static Course loadFromFile(File logFile) throws IOException {
        FileReader reader = new FileReader(logFile);
        Gson gson = new Gson();
        return gson.fromJson(reader,Course.class);
    }

    public void saveToFile(File logFile) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String serializedGymCourse = gson.toJson(this);
        PrintWriter writer = new PrintWriter(new FileWriter(logFile));
        writer.print(serializedGymCourse);
        for (Plan plan : listOfPlans){
            String serializedPlan = gson.toJson(plan);
            writer.println(serializedPlan);
        }
        writer.close();
    }
}
