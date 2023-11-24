package edu.augustana.filters;

import edu.augustana.cards.Card;
import edu.augustana.filters.CardFilter;

public class EventFilter implements CardFilter {
    private String desiredEvent;

    public EventFilter(String desiredEvent){
        this.desiredEvent = desiredEvent;
    }
    @Override
    public boolean matches(Card candidate) {
        return candidate.getEvent().equalsIgnoreCase(desiredEvent) || candidate.getEvent().equalsIgnoreCase("ALL");
    }
}
