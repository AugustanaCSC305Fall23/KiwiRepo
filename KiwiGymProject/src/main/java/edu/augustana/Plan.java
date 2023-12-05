package edu.augustana;

import edu.augustana.cards.Card;

import java.util.ArrayList;
import java.util.List;

public class Plan {
    private String name;
    private List<String> eventList;
    private char gender;
    private List<String> planEquipment;
    private static List<Card> cardList;


    public Plan(String NewPlanName) {
        name = NewPlanName;
        cardList = new ArrayList<>();
        planEquipment = new ArrayList<>();
        eventList = new ArrayList<>();
    }
    public void addCard(Card cardToAdd){
        cardList.add(cardToAdd);
        eventList.add(cardToAdd.getEvent());
        addEquipment(cardToAdd);
    }
    public void addEquipment(Card card){
        List<String> equipmentToAdd = card.getEquipment();
        for (String equipment : equipmentToAdd){
            if (!planEquipment.contains(equipment)){
                planEquipment.add(equipment);
            }
        }
    }
    public String getName() {
        return name;
    }

    public List<String> getEvent() {
        return eventList;
    }

    public char getGender() {
        return gender;
    }

    public List<String> getPlanEquipment() {
        return planEquipment;
    }

    public List<Card> getCardList() {
        return cardList;
    }
    @Override
    public String toString(){
        return name;
    }
}
