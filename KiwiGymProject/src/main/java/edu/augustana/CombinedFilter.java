package edu.augustana;

import java.util.ArrayList;
import java.util.List;

public class CombinedFilter implements CardFilter{

    private List<CardFilter> filters = new ArrayList<CardFilter>();
    @Override
    public boolean matches(Card candidate) {
        return false;
    }
}
