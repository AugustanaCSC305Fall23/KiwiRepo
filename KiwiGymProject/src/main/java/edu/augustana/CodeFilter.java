package edu.augustana;

public class CodeFilter implements CardFilter{
    private String desiredCode;

    public CodeFilter(String desiredCode){
        this.desiredCode = desiredCode;
    }
    @Override
    public boolean matches(Card candidate) {
        return candidate.getCode().equalsIgnoreCase(desiredCode);
    }
}
