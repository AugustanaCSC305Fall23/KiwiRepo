package edu.augustana.filters;

import edu.augustana.cards.Card;
import edu.augustana.filters.CardFilter;

public class GenderFilter implements CardFilter {
    private final char desiredGender;

    public GenderFilter(char desiredGender){
        this.desiredGender = desiredGender;
    }
    @Override
    public boolean matches(Card candidate) {
        return candidate.getGender() == desiredGender || candidate.getGender() == 'N';
    }
}
