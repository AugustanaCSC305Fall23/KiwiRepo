package edu.augustana;

import java.util.*;
//for the list of plans in the tree view
public class Course {
    private static List<Plan> listOfPlans;
    private String name;

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
}
