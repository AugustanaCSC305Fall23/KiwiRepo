package edu.augustana.filters;

import edu.augustana.cards.Card;
import edu.augustana.filters.CardFilter;

public class EquipmentFilter implements CardFilter {
    private String desiredEquipment;

    public EquipmentFilter(String desiredEquipment){
        this.desiredEquipment = desiredEquipment;
    }
    @Override
    public boolean matches(Card candidate) {
        return candidate.getEquipment().contains(desiredEquipment);
    }
}
