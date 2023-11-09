package edu.augustana;

public class ModelSexFilter implements CardFilter{
    private char desiredModel;

    public ModelSexFilter(char desiredModel){
        this.desiredModel = desiredModel;
    }
    @Override
    public boolean matches(Card candidate) {
        return candidate.getModelSex() == desiredModel;
    }
}
