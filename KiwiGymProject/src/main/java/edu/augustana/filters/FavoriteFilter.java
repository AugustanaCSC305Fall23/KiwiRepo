package edu.augustana.filters;

import edu.augustana.cards.Card;

public class FavoriteFilter implements CardFilter{

    public FavoriteFilter(){
    }
    @Override
    public boolean matches(Card candidate) {
        return FavoriteCardCollection.getFavorite().contains(candidate);
    }
}
