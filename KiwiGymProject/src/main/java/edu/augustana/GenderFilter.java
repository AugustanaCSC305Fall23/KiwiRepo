package edu.augustana;

public class GenderFilter implements CardFilter{
    private char desiredGender;

    public GenderFilter(char desiredGender){
        this.desiredGender = desiredGender;
    }
    @Override
    public boolean matches(Card candidate) {
        return candidate.getGender() == desiredGender;
    }
}
