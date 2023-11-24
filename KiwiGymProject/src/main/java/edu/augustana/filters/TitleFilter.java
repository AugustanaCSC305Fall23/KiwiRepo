package edu.augustana.filters;

import edu.augustana.cards.Card;
import edu.augustana.filters.CardFilter;

public class TitleFilter implements CardFilter {
    private String desiredTitle;

    public TitleFilter(String desiredTitle){
        this.desiredTitle = desiredTitle;
    }
    @Override
    public boolean matches(Card candidate) {
        return candidate.getTitle().equalsIgnoreCase(desiredTitle);
    }
}
