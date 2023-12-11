package edu.augustana;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class CourseTest {

    @Test
    public void numPlansTest(){
        Course course = new Course("Course");
        int num = 1;
        while (num <= 5){
            Plan plan = new Plan("Plan " + num);
            course.addPlan(plan);
            num++;
        }
        assertEquals(5, course.getNumPlans());
    }

    @Test
    public void nameOfPlansTest(){
        Plan plan1 = new Plan("Week1Fall23");
        Plan plan2 = new Plan("Week2Fall23");
        Plan plan3 = new Plan("Week3Fall23");
        List<Plan> planList = new ArrayList<Plan>();
        planList.add(plan1);
        planList.add(plan2);
        planList.add(plan3);
        Course course = new Course("FallCourse23");
        course.addPlan(plan1);
        course.addPlan(plan2);
        course.addPlan(plan3);
        assertEquals(planList, Course.getPlanList());
    }
  
}