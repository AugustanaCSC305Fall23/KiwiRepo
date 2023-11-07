package edu.augustana;

public class LevelFilter implements CardFilter{

    String desiredLevel;

    public LevelFilter(String desiredLevel){
        this.desiredLevel = desiredLevel;
    }
    @Override
    public boolean matches(Card candidate) {
        return candidate.getLevel().equalsIgnoreCase(desiredLevel);
    }
}
