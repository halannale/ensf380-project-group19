package edu.ucalgary.oop;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.*;
import java.util.regex.*;

public class ScheduleTest {
    String expectedAnimalName2 = "Biter";
    String expectedAnimaSpecies2 = "Coyote";
    int expectedAnimalID2 = 2;
    Animal animal1 = Animal("Loner", 1, "Coyote");
    Animal animal2 = Animal("Biter", 2, "Coyote");
    Treatment treatment1 = Treatment(1, 9, 22);
    Treatment treatment2 = Treatment(1, 10, 10);
    Animal[] expectedAnimals = [animal1, animal2];
    MedicalTask task1 = MedicalTask(9, "Eyedrops", 25, 1);
    MedicalTask task2 = MedicalTask(10, "Inspect broken leg", 5, 2);

    @Test
    public void testGetters() {
        // Animal
        //getTasks: MedicalTask[]
        MedicalTask[] expectedTasks = [task1, task2];

        MedicalTask[] actualTasks = animal1.getTasks();

        assertEquals("Constructor or getter gave wrong value for tasks", 
        expectedTasks, actualTasks);

        //getID: int
        int expectedAnimalID1 = 1;

        int actualAnimalID = animal1.getAnimalID();

        assertEquals("Constructor or getter gave wrong value for animal id", 
        expectedAnimalID1, actualAnimalID);

        //getName: String
        String expectedAnimalName1 = "Loner";

        String actualAnimalName = animal1.getName();

        assertEquals("Constructor or getter gave wrong value for animal name", 
        expectedAnimalName1, actualAnimalName);

        //getSpecies: String
        String expectedAnimalSpecies1 = "Coyote";

        String actualAnimalSpecies = animal1.getType();

        assertEquals("Constructor or getter gave wrong value for animal type", 
        expectedAnimalSpecies1, actualAnimalSpecies);



        // MedicalTask
        //getDuration: int
        int expectedDuration1 = 25;

        int actualDuration = animal1.getDuration();

        assertEquals("Constructor or getter gave wrong value for duration", 
        expectedDuration1, actualDuration);

        //getID: int
        int expectedTaskID1 = 9;

        int actualTaskID = task1.getTaskID();

        assertEquals("Constructor or getter gave wrong value for task id", 
        expectedTaskID1, actualTaskID);

        //getDescription: String
        String expectedDescription1 = "Eyedrops";

        String actualDescription = task1.getDescription();

        assertEquals("Constructor or getter gave wrong value for description", 
        expectedDescription1, actualDescription);

        //getMaxWindow:int
        int expectedMaxWindow1 = 1;

        int actualMaxWindow = task1.getMaxWindow();

        assertEquals("Constructor or getter gave wrong value for maximum window", 
        expectedMaxWindow1, actualMaxWindow);


        // Treatment
        //getStartHour:int
        int expectedStartHour1 = 22;

        int actualStartHour = treatment1.getStartHour();

        assertEquals("Constructor or getter gave wrong value for starting hour", 
        expectedStartHour1, actualStartHour);

        //getMedical: MedicalTask
        MedicalTask expectedTask = task1;

        MedicalTask actualTask = treatment1.getMedical();

        assertEquals("Constructor or getter gave wrong value for medical task", 
        expectedTask, actualTask);

        //getAnimal: Animal
        Animal expectedAnimal = animal1;

        Animal actualAnimal = treatment1.getAnimal();
        



        // AssignTime
        //getAvailableTime: int[]
        //getSchedule: HashMap<Integer, ArrayList<String>>
        //getTreatmentFromDescription: Treatment


        //SchedulePrint
        //getAssignTime
    }

    @Test 
    public void testAssignTime() {

        //findLowestMaxWindowIndex: int
        //removeTreatment: Treatment[]
        //calculateDuration: int

    }

    @Test void testSchedulePrint() {
        //printSchedule: void
        //test with valid schedule
        //test with an illegalschedule throw
        //test with backup volunteer needed/not (more than 60 mins, less that 60 mins, 60 and 61 mins)
        //test with sql database, see if it equals output.txt

    }

    @Test void testEnum() {

        //cage: int
        //intstance with orphans
        //different number of cages and cleaning
        //feed: int
        //prep: int
        //feedWindow: int[]
        //test with multiple animal and feed windows


        // this is in animal
        //checkAnimalSpecies: boolean
        //test for valid and invalid enum number
        //test for all caps and lowercase 
    }

    //test commit

}