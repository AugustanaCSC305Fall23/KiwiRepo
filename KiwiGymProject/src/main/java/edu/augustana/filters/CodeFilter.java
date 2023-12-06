package edu.augustana.filters;

import edu.augustana.cards.Card;
import edu.augustana.filters.CardFilter;

public class CodeFilter implements CardFilter {
    private final String desiredCode;

    public CodeFilter(String desiredCode){
        this.desiredCode = desiredCode;
    }
    @Override
    public boolean matches(Card candidate) {
        return candidate.getCode().equalsIgnoreCase(desiredCode);
    }
}
