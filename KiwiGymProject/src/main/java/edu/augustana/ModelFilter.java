package edu.augustana;

public class ModelFilter implements CardFilter{
    private char desiredModel;

    public ModelFilter(char desiredModel){
        this.desiredModel = desiredModel;
    }
    @Override
    public boolean matches(Card candidate) {
        return candidate.getModelSex() == desiredModel;
    }
}
