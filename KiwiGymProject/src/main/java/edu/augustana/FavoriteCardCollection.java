package edu.augustana;

import edu.augustana.cards.Card;

import java.util.ArrayList;
import java.util.List;

public class FavoriteCardCollection {
    private final String FAVORITE_FILE_NAME = "favorites.json";
    private static FavoriteCardCollection theFavoriteCollection = null;
    private List<Card> favoriteCards = new ArrayList<>();


    public void setFavorite(Card card){
        favoriteCards.add(card);
    }

    public void removeFav(Card card){
        favoriteCards.remove(card);
    }

    public List<Card> getFavorite(){
        return favoriteCards;
    }

    public static FavoriteCardCollection getTheFavoriteCollection() {
        if (theFavoriteCollection == null) {
            theFavoriteCollection =
        }
    }

    private static FavoriteCardCollection loadCollectionFromJSON() {

    }

}
