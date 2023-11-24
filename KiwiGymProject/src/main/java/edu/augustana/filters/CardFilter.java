package edu.augustana.filters;

import edu.augustana.cards.Card;

public interface CardFilter {
    public boolean matches(Card candidate);


}
