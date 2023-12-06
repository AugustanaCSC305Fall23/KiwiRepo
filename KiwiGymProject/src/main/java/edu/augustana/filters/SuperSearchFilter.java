package edu.augustana.filters;

import edu.augustana.cards.Card;
import org.w3c.dom.events.Event;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SuperSearchFilter implements CardFilter{

    private final String desiredResult;

    public SuperSearchFilter(String desiredResult){
        this.desiredResult = desiredResult;
    }
    @Override
    public boolean matches(Card candidate) {
        EventFilter eventFilter = new EventFilter(desiredResult);
        CodeFilter codeFilter = new CodeFilter(desiredResult);
        TitleFilter titleFilter = new TitleFilter(desiredResult);
        CategoryFilter catFilter = new CategoryFilter(desiredResult);
        KeyWordFilter keyFilter = new KeyWordFilter(desiredResult);
        EquipmentFilter equipmentFilter = new EquipmentFilter(desiredResult);
        List<CardFilter> filterArray = new ArrayList<>(Arrays.asList(eventFilter, codeFilter, titleFilter, catFilter, keyFilter, equipmentFilter));
        for (CardFilter filter : filterArray){
            if (!filter.matches(candidate)){
                return false;
            } else {
                break;
            }
        }
        return true;
    }
}
