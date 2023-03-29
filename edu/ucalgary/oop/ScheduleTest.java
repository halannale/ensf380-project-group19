package edu.ucalgary.oop;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.*;
import java.util.regex.*;

public class ScheduleTest {
    String expectedAnimalName1 = "Loner";
    String expectedAnimalType1 = "Coyote";
    int expectedAnimalID1 = 1;
    String expectedAnimalName2 = "Biter";
    String expectedAnimalType2 = "Coyote";
    int expectedAnimalID2 = 2;
    int expectedTaskID1 = 9;
    int expectedDuration1 = 25;
    int expectedStartHour1 = 22;
    String expectedDescription1 = "Eyedrops";
    int expectedMaxWindow1 = 1;
    Animal animal1 = Animal("Loner", 1, "Coyote");
    Animal animal2 = Animal("Biter", 2, "Coyote");
    MedicalTask task1 = MedicalTask(9, "Eyedrops", 25, 1);
    MedicalTask task2 = MedicalTask(10, "Inspect broken leg", 5, 2);
    Treatment treatment1 = Treatment(1, 9, 22);
    Treatment treatment2 = Treatment(1, 10, 10);
    MedicalTask[] expectedTasks = [task1, task2];
    Animal[] expectedAnimals = [animal1, animal2];
    Scedule schedule;

    @Test
    public void testGetters() {
        int actualAnimalID = animal1.getAnimalID();
        String actualAnimalName = animal1.getName();
        String actualAnimalType = animal1.getType();
        int actualDuration = animal1.getDuration();
        int actualTaskID = task1.getTaskID();
        String actualDescription = task1.getDescription();
        int actualStartHour = treatment1.getStartHour();
        int actualMaxWindow = task1.getMaxWindow();
        MedicalTask[] actualTasks = animal1.getTasks();

        //getTasks: Array[]
        assertEquals("Constructor or getter gave wrong value for tasks", 
        expectedTasks, actualTasks);
        //getAnimals:Animal[]
        assertEquals("Constructor or getter gave wrong value for animals", 
        expectedAnimals, actualAnimals);
        //getMedical:MedicalTask[]
        //getAnimalID: int
        assertEquals("Constructor or getter gave wrong value for animal id", 
        expectedAnimalID1, actualAnimalID);
        //getName: String
        assertEquals("Constructor or getter gave wrong value for animal name", 
        expectedAnimalName1, actualAnimalName);
        //getType: String
        assertEquals("Constructor or getter gave wrong value for animal type", 
        expectedAnimalType1, actualAnimalType);
        //getDuration: int
        assertEquals("Constructor or getter gave wrong value for duration", 
        expectedDuration1, actualDuration);
        //getTaskID: int
        assertEquals("Constructor or getter gave wrong value for task id", 
        expectedTaskID1, actualTaskID);
        //getDescription: String
        assertEquals("Constructor or getter gave wrong value for description", 
        expectedDescription1, actualDescription);
        //getStartHour:int
        assertEquals("Constructor or getter gave wrong value for starting hour", 
        expectedStartHour1, actualStartHour);
        //getMaxWindow:int
        assertEquals("Constructor or getter gave wrong value for maximum window", 
        expectedMaxWindow1, actualMaxWindow);
    }

}