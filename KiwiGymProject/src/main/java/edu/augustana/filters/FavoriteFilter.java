package edu.augustana.filters;

import edu.augustana.FavoriteCardCollection;
import edu.augustana.cards.Card;

public class FavoriteFilter implements CardFilter{

    public FavoriteFilter(){
    }
    @Override
    public boolean matches(Card candidate) {
        //FavoriteCardCollection.getFavorite().contains(candidate);
        return true;
    }
}
