package edu.augustana;

import javafx.scene.control.Alert;

import java.util.*;
//for the list of plans in the tree view
public class PlanList {
    public static List<Plan> lessonPlans;

    public static void PlanList(){
        lessonPlans = new ArrayList<>();
    }
    public static void addPlan(Plan plan){
        lessonPlans.add(plan);
    }
}
