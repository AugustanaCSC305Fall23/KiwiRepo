package edu.augustana;

public class EventFilter implements CardFilter{
    private String desiredEvent;

    public EventFilter(String desiredEvent){
        this.desiredEvent = desiredEvent;
    }
    @Override
    public boolean matches(Card candidate) {
        return candidate.getEvent().equalsIgnoreCase(desiredEvent);
    }
}
