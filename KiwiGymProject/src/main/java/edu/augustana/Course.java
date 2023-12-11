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

    /** adds a plan to the list of plans in the course
     *
     * @param plan the plan that is being added to the course
     */
    public void addPlan(Plan plan){
        listOfPlans.add(plan);
        listPlanNames.add(plan.getName());
    }


    /** removes the plan that is wanted to be removed
     *
     * @param planName the name of the plan that is going to be removed
     */

    public void removePlan(String planName){
        listOfPlans.remove(listPlanNames.indexOf(planName));
        listPlanNames.remove(planName);
    }

    /** adds a card to the specified plan
     *
     * @param plan the plan that the card is being added to
     * @param card the card that is being added to the plan
     */
    public void addCardToPlan(String plan, Card card){
        Plan selectedPlan = listOfPlans.get(listPlanNames.indexOf(plan));
        selectedPlan.addCard(card);
    }

    public String getName(){
        return name;
    }

    /** changes the name of the old plan to the new name
     *
     * @param newName the new name that is inputted
     * @param oldName the old name of the plan
     */
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

    /** reads a file and converts the contents to a course
     *
     * @param courseFile the file that is being read
     * @return a course object from the file
     * @throws IOException if the file is not correct
     */
    public static Course loadFromFile(File courseFile) throws IOException {
        FileReader reader = new FileReader(courseFile);
        Gson gson = new Gson();
        return gson.fromJson(reader,Course.class);
    }

    /** saves the current course to a file
     *
     * @param courseFile the file that it is being saved to
     * @throws IOException
     */
    public void saveToFile(File courseFile) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Map<String, List<Plan>> courseMap = new HashMap<>();
        courseMap.put(name, listOfPlans);
        String serializedGymCourse = gson.toJson(courseMap);
        PrintWriter writer = new PrintWriter(new FileWriter(courseFile));
        writer.print(serializedGymCourse);
        writer.close();
    }

}
