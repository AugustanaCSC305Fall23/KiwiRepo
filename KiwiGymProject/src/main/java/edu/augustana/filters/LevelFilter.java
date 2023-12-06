package edu.augustana.filters;

import edu.augustana.cards.Card;
import edu.augustana.filters.CardFilter;

public class LevelFilter implements CardFilter {

    private final String desiredLevel;

    public LevelFilter(String desiredLevel){
        this.desiredLevel = desiredLevel;
    }
    @Override
    public boolean matches(Card candidate) {
        return candidate.getLevel().contains(desiredLevel) || candidate.getLevel().contains("ALL");
    }
}
