package edu.augustana;

import java.util.ArrayList;
import java.util.List;

public class Plan {
    private String name;
    private String event;
    private char gender;
    private List<String> equipment;
    private static List<Card> cardList;


    public Plan(String NewPlanName) {
        name = NewPlanName;
        cardList = new ArrayList<>();
        equipment = new ArrayList<>();
    }
    public void addCard(Card cardToAdd){
        cardList.add(cardToAdd);
    }
    public String getName() {
        return name;
    }

    public String getEvent() {
        return event;
    }

    public char getGender() {
        return gender;
    }

    public List<String> getEquipment() {
        return equipment;
    }

    public List<Card> getCardList() {
        return cardList;
    }
    @Override
    public String toString(){
        return name;
    }
}
