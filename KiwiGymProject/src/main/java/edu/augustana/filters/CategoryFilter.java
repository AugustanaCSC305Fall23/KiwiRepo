package edu.augustana.filters;

import edu.augustana.cards.Card;
import edu.augustana.filters.CardFilter;

public class CategoryFilter implements CardFilter {
    private final String desiredCategory;

    public CategoryFilter(String desiredCategory){
        this.desiredCategory = desiredCategory;
    }
    @Override
    public boolean matches(Card candidate) {
        return candidate.getCategory().equalsIgnoreCase(desiredCategory);
    }
}
