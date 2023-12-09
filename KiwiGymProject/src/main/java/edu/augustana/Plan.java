package edu.augustana;

import edu.augustana.cards.Card;

import java.util.ArrayList;
import java.util.List;

public class Plan {
    private String name;
    private List<String> eventList;
    private char gender;
    private List<String> planEquipment;
    private List<Card> cardList;
    private List<String> cardTitles;

    /** constructs a new plan obejct
     *
     * @param NewPlanName the name of the new plan
     */
    public Plan(String NewPlanName) {
        name = NewPlanName;
        cardList = new ArrayList<>();
        cardTitles = new ArrayList<>();
        planEquipment = new ArrayList<>();
        eventList = new ArrayList<>();
    }

    /** adds a card to the plan
     *
     * @param cardToAdd the card being added to the plan
     */
    public void addCard(Card cardToAdd){
        cardList.add(cardToAdd);
        cardTitles.add(cardToAdd.getTitle());
        if (!eventList.contains(cardToAdd.getEvent())){
            eventList.add(cardToAdd.getEvent());
        }
        addEquipment(cardToAdd);
    }


    private void addEquipment(Card card){
        List<String> equipmentToAdd = card.getEquipment();
        for (String equipment : equipmentToAdd){
            if (!planEquipment.contains(equipment)){
                planEquipment.add(equipment);
            }
        }
    }

    /** removes an event from the list of events in the plan
     *
     * @param event the event that is being removed
     */
    public void removeEvent(String event){
        eventList.remove(event);
        List<Card> copyList = new ArrayList<>(cardList);
        for (Card card : copyList){
            if(card.getEvent().equals(event)){
                cardList.remove(card);
                cardTitles.remove(card.getTitle());
            }
        }
    }

    /** removes a card from the plan
     *
     * @param cardName the name of the card being removed
     */
    public void removeCard(String cardName){
        cardList.remove(cardTitles.indexOf(cardName));
        cardTitles.remove(cardName);
    }
    public String getName() {
        return name;
    }

    /** changes the name of the plan
     *
     * @param name the new name of the plan
     */
    public void changeName(String name){
        this.name = name;
    }

    public List<String> getEventList() {
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
        return "Name: " + name + '\'' +
                "Cards: " + cardList.toString();
    }
}
