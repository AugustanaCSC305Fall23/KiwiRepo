package edu.augustana.filters;

import edu.augustana.cards.Card;
import edu.augustana.filters.CardFilter;

public class KeyWordFilter implements CardFilter {
    private final String desiredKeys;

    public KeyWordFilter(String desiredKeys){
        this.desiredKeys = desiredKeys;
    }
    @Override
    public boolean matches(Card candidate) {
        return candidate.getKeyWords().contains(desiredKeys);
    }
}
