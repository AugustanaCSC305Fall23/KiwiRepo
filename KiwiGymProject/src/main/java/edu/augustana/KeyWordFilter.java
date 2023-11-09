package edu.augustana;

import java.util.ArrayList;
import java.util.List;

public class KeyWordFilter implements CardFilter{
    private String desiredKeys;

    public KeyWordFilter(String desiredKeys){
        this.desiredKeys = desiredKeys;
    }
    @Override
    public boolean matches(Card candidate) {
        return candidate.getKeyWords().contains(desiredKeys);
    }
}
