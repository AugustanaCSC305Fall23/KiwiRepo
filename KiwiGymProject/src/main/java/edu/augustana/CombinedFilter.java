package edu.augustana;

import java.util.ArrayList;
import java.util.List;

public class CombinedFilter implements CardFilter{

    private List<CardFilter> filters = new ArrayList<>();

    public CombinedFilter(List<CardFilter> filters){
        this.filters.addAll(filters);
    }
    @Override
    public boolean matches(Card candidate) {
        for (CardFilter filter : filters){
            if (!filter.matches(candidate)){
                return false;
            }
        }
        return true;
    }
}
