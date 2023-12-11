package edu.augustana;

import edu.augustana.cards.Card;
import org.junit.Test;
import static org.junit.Assert.*;
public class Tests {
    @Test
    public void courseTest() {
        Course course = new Course("Favorites");
        int num = 5;
        while (num > 1){
            Plan plan = new Plan("plan" + num);
            course.addPlan(plan);
        }
    }
}
