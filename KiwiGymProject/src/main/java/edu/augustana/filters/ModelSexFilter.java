package edu.augustana.filters;

import edu.augustana.cards.Card;
import edu.augustana.filters.CardFilter;

public class ModelSexFilter implements CardFilter {
    private final char desiredModel;

    public ModelSexFilter(char desiredModel){
        this.desiredModel = desiredModel;
    }
    @Override
    public boolean matches(Card candidate) {
        return candidate.getModelSex() == desiredModel;
    }
}
