package edu.augustana;

public class EquipmentFilter implements CardFilter{
    private String desiredEquipment;

    public EquipmentFilter(String desiredEquipment){
        this.desiredEquipment = desiredEquipment;
    }
    @Override
    public boolean matches(Card candidate) {
        return candidate.getEquipment().contains(desiredEquipment);
    }
}
