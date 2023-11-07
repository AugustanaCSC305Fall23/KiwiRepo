package edu.augustana;

public class TitleFilter implements CardFilter{
    private String desiredTitle;

    public TitleFilter(String desiredTitle){
        this.desiredTitle = desiredTitle;
    }
    @Override
    public boolean matches(Card candidate) {
        return candidate.getTitle().equalsIgnoreCase(desiredTitle);
    }
}
