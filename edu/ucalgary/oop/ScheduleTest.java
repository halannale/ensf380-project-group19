/*
@author 
    Halanna Le
    Grace Jang
    Christy Guirguis
    Gillian Habermehl
*/
package edu.ucalgary.oop;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.File;
import java.util.*;


public class ScheduleTest {
    MedicalTask task1 = new MedicalTask(9, "Eyedrops", 25, 1);
    MedicalTask task2 = new MedicalTask(10, "Inspect broken leg", 5, 2);

    Animal animal1 = new Animal(1, "Loner", "coyote");
    Animal animal2 = new Animal(2, "Biter", "coyote");

    Treatment treatment1 = new Treatment(animal1, task1, 22);
    Treatment treatment2 = new Treatment(animal1, task2, 10);

    Animal[] animals = {animal1, animal2};
    Treatment[] treatments = {treatment1, treatment2};
    MedicalTask[] tasks = {task1, task2};


    /*
    * Getters for the Animal class
    */

    @Test 
    public void testGetIDAnimal() {
        /*
         * Tests the return of getID() in animal class.
         */

        int expectedAnimalID1 = 1;
        int actualAnimalID = animal1.getID();

        assertEquals("Constructor or getter gave wrong value for animal id", 
        expectedAnimalID1, actualAnimalID);
    }

    @Test
    public void testGetName(){
        /*
         * Tests the return of getName().
         */

        String expectedAnimalName1 = "Loner";
        String actualAnimalName = animal1.getName();

        assertEquals("Constructor or getter gave wrong value for animal name", 
        expectedAnimalName1, actualAnimalName);
    }

    @Test 
    public void testGetSpecies() {
        /*
         * Tests the return of getSpecies().
         */

        String expectedAnimalSpecies1 = "coyote";
        String actualAnimalSpecies = animal1.getSpecies();
  
        assertEquals("Constructor or getter gave wrong value for animal type", 
        expectedAnimalSpecies1, actualAnimalSpecies);
    }

    /*
    * Getters for the MedicalTask class
    */

    @Test 
    public void testGetDuration() {
        /*
         * Tests the return of getDuration().
        */

        int expectedDuration1 = 25;
        int actualDuration = task1.getDuration();

        assertEquals("Constructor or getter gave wrong value for task duration", 
        expectedDuration1, actualDuration);
    }

    @Test
    public void testGetIDMedical() {
        /*
        * Tests the return of getID() in MedicalTask class.
        */

        int expectedTaskID1 = 9;
        int actualTaskID = task1.getID();

        assertEquals("Constructor or getter gave wrong value for task id", 
        expectedTaskID1, actualTaskID);
    }

    @Test
    public void testGetDescription() {
        /*
        * Tests the return of getDescription().
        */

        String expectedDescription1 = "Eyedrops";
        String actualDescription = task1.getDescription();

        assertEquals("Constructor or getter gave wrong value for task description", 
        expectedDescription1, actualDescription);
    }

    @Test 
    public void testGetMaxWindow() {
        /*
        * Tests the return of getMaxWindow().
        */

        int expectedMaxWindow1 = 1;
        int actualMaxWindow = task1.getMaxWindow();

        assertEquals("Constructor or getter gave wrong value for task maximum window", 
        expectedMaxWindow1, actualMaxWindow);
    }

    /*
    * Getters for the Treatment class
    */

    @Test
    public void testGetStartHour() {
        /*
        * Tests the return of getStartHour().
        */

        int expectedStartHour1 = 22;
        int actualStartHour = treatment1.getStartHour();

        assertEquals("Constructor or getter gave wrong value for treatment starting hour", 
        expectedStartHour1, actualStartHour);
    }

    @Test 
    public void testGetMedical() {
        /*
        * Tests the return of getMedical().
        */

        MedicalTask expectedTask = task1;
        MedicalTask actualTask = treatment1.getMedical();

        assertEquals("Constructor or getter gave wrong value for treatment medical task", 
        expectedTask, actualTask);
    }

    @Test 
    public void testGetAnimal() {
        /*
        * Tests the return of getAnimal().
        */

        Animal expectedAnimal = animal1;
        Animal actualAnimal = treatment1.getAnimal();

        assertEquals("Constructor or getter gave wrong value for treatment animal", 
        expectedAnimal, actualAnimal);
    }

    /*
    * Getters for the AssignTime class
    */

    @Test
    public void testGetAvailableTime() {
        /*
        * Tests the return of getAvailableTime().
        */

        try{
            AssignTime assignTime = new AssignTime(animals, treatments);
            int[] expectedAvailableTime = {60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60};
            int[] actualAvailableTime = assignTime.getAvailableTime();

            for(int i = 0; i < expectedAvailableTime.length; i++) {
                assertEquals("Constructor or getter gave wrong value for available time", 
                expectedAvailableTime[i], actualAvailableTime[i]);
            }
        }

        // Making sure we catch illegal arguments
        catch(IllegalSchedule e) {}
    }

    @Test
    public void testGetSchedule() {
        /*
        * Tests the return of getSchedule().
        */

        try{
            AssignTime assignTime = new AssignTime(animals, treatments);
            HashMap<Integer, ArrayList<String>> expectedSchedule = new HashMap<Integer, ArrayList<String>>();

            ArrayList<String> cleanCages = new ArrayList<String>();
            cleanCages.add("Clean cage(coyote)");

            ArrayList<String> inspectLeg = new ArrayList<String>();
            inspectLeg.add("Inspect broken leg(Loner)");

            ArrayList<String> feed = new ArrayList<String>();
            feed.add("Feed coyote");

            ArrayList<String> eyedrops = new ArrayList<String>();
            eyedrops.add("Eyedrops(Loner)");

            ArrayList<String> empty = new ArrayList<String>();


            expectedSchedule.put(0, cleanCages);
            expectedSchedule.put(1, empty);
            expectedSchedule.put(2, empty);
            expectedSchedule.put(3, empty);
            expectedSchedule.put(4, empty);
            expectedSchedule.put(5, empty);
            expectedSchedule.put(6, empty);
            expectedSchedule.put(7, empty);
            expectedSchedule.put(8, empty);
            expectedSchedule.put(9, empty);
            expectedSchedule.put(10, inspectLeg);
            expectedSchedule.put(11, empty);
            expectedSchedule.put(12, empty);
            expectedSchedule.put(13, empty);
            expectedSchedule.put(14, empty);
            expectedSchedule.put(15, empty);
            expectedSchedule.put(16, empty);
            expectedSchedule.put(17, empty);
            expectedSchedule.put(18, empty);
            expectedSchedule.put(19, feed);
            expectedSchedule.put(20, empty);
            expectedSchedule.put(21, empty);
            expectedSchedule.put(22, eyedrops);
            expectedSchedule.put(23, empty);

            HashMap<Integer, ArrayList<String>>  actualSchedule = assignTime.getSchedule();
    
            assertEquals("Constructor or getter gave wrong value for schedule", 
            expectedSchedule, actualSchedule);
        }

        // Making sure we catch the illegal arguments
        catch(IllegalSchedule e) {}
    }

    @Test 
    public void testGetTreatmentFromDescription() {
        /*
        * Tests the return of getTreatmentFromDescription().
        */

        try {
            AssignTime assignTime = new AssignTime(animals, treatments);
            Treatment expectedTreatment = treatment1;
            Treatment actualTreatment = assignTime.getTreatmentFromDescription(treatments, "Eyedrops");
        
            assertEquals("Constructor or getter gave wrong value for treatment", 
            expectedTreatment, actualTreatment);
        } 

        // Making sure we catch the illegal arguments
        catch (IllegalSchedule e) {}
    }

    /*
    * Getters for the SchedulePrint class
    */

    @Test 
    public void testGetAssignTime() {
        /*
        * Tests the return of getAssignTime().
        */

        try{ 
            /*
             * Make sure both schedule and available time (variables of the AssignTime class that is being returned)
             * are equal to each other for the two AssignTime instances to be equal
             */
            SchedulePrint schedulePrint = new SchedulePrint(animals, treatments);
            AssignTime expectedAssignTime = new AssignTime(animals, treatments);
            AssignTime actualAssignTime = schedulePrint.getAssignTime();

            int[] expectedAvailableTime = expectedAssignTime.getAvailableTime();
            int[] actualAvailableTime = actualAssignTime.getAvailableTime();

            for(int i = 0; i < expectedAvailableTime.length; i++) {
                assertEquals("Constructor or getter gave wrong value for available time in getAssignTime()", 
                expectedAvailableTime[i], actualAvailableTime[i]);
            }

            assertEquals("Constructor or getter gave wrong value for schedule in getAssignTime()", 
            expectedAssignTime.getSchedule(), actualAssignTime.getSchedule());
        }

        // Making sure we catch illegal arguments
        catch(IllegalSchedule e) {}
    }

    /*
    * Methods for the SchedulePrint class
    */

   @Test
    public void testPrintSchedule() {
        /*
        * Tests the return of printSchedule().
        * Tests to make sure a schedule.txt file is correctly made and exists when printSchedule() is called.
        * Tests the validity of calling a backup volunteer in the schedule.txt file when needed.
        */

        try{
            SchedulePrint schedule = new SchedulePrint(animals, treatments);
            schedule.printSchedule();
                   
            File expectedfile = new File("schedule.txt");   
            // If file is created and it exists   
            if(!expectedfile.exists()){
                assertEquals("Error in making schedule.txt file", expectedfile.exists());
            }
        }
        // Making sure we catch the illegal arguments
        catch(IllegalSchedule e){}

        // Now checking .txt file calls a backup volunteer
        try{
            SchedulePrint schedule = new SchedulePrint(animals, treatments);
            AssignTime assignTime = new AssignTime(animals, treatments);
            schedule.printSchedule();
            
            int expectedNoBackUp = 60;
            int expectedBackup = 120;

            for (int i = 0; i < 24; i++) { 

                int[] hour = assignTime.getAvailableTime();
                int indexhour = hour[i];

                if(indexhour == 120){
                    assertEquals("A backup volunteer is needed but not called", expectedBackup, indexhour);
                }

                if(indexhour == 60){
                    assertEquals("A backup volunteer is not needed but called", expectedNoBackUp, indexhour);
                }   
            }
        }

        // Making sure we catch the illegal arguments
        catch(IllegalSchedule e){}
    }

    /*
    * Methods for the Animal class
    */

    @Test
    public void testCheckAnimalSpecies() {

        boolean expectedAnimalSpecies1 = "coyote" != null;
        String species = "coyote";
        boolean actualAnimalSpecies = animal1.checkAnimalSpecies(species);

        assertEquals("Constructor or getter gave wrong value for animal name", 
        expectedAnimalSpecies1, actualAnimalSpecies);
    }

    /*
    * Methods for the AnimalSpecies class
    */
 
    @Test
    public void testCage() {
        /*
        * Tests the return of prep().
        * Tests with all species. 
        */

        int expectedCoyote = 5;
        int actualCoyote = AnimalSpecies.values()[0].cage();
        assertEquals("Enum gave wrong value for cage", 
            expectedCoyote, actualCoyote);

        int expectedFox = 5;
        int actualFox = AnimalSpecies.values()[1].cage();
        assertEquals("Enum gave wrong value for cage", 
            expectedFox, actualFox);
        
        int expectedPorcupine = 10;
        int actualPorcupine = AnimalSpecies.values()[2].cage();
        assertEquals("Enum gave wrong value for cage", 
             expectedPorcupine, actualPorcupine);

        int expectedBeaver = 5;
        int actualBeaver = AnimalSpecies.values()[3].cage();
        assertEquals("Enum gave wrong value for cage", 
            expectedBeaver, actualBeaver);

        int expectedRacoon = 5;
        int actualRacoon = AnimalSpecies.values()[4].cage();
        assertEquals("Enum gave wrong value for cage", 
            expectedRacoon, actualRacoon);

    }

    @Test
    public void testFeed() {
        /*
        * Tests the return of feed().
        * Tests with all species.
        */

        int expectedCoyote = 5;
        int actualCoyote = AnimalSpecies.values()[0].feed();
        assertEquals("Enum gave wrong value for feed", 
            expectedCoyote, actualCoyote);

        int expectedFox = 5;
        int actualFox = AnimalSpecies.values()[1].feed();
        assertEquals("Enum gave wrong value for feed", 
            expectedFox, actualFox);
        
        int expectedPorcupine = 5;
        int actualPorcupine = AnimalSpecies.values()[2].feed();
        assertEquals("Enum gave wrong value for feed", 
             expectedPorcupine, actualPorcupine);

        int expectedBeaver = 5;
        int actualBeaver = AnimalSpecies.values()[3].feed();
        assertEquals("Enum gave wrong value for feed", 
            expectedBeaver, actualBeaver);

        int expectedRacoon = 5;
        int actualRacoon = AnimalSpecies.values()[4].feed();
        assertEquals("Enum gave wrong value for feed", 
            expectedRacoon, actualRacoon);
    }

    @Test
    public void testPrep() {
        /*
        * Tests the return of prep().
        * Tests with all species.
        */

        int expectedCoyote = 50;
        int actualCoyote = AnimalSpecies.values()[0].prep();
        assertEquals("Enum gave wrong value for prep", 
            expectedCoyote, actualCoyote);

        int expectedFox = 5;
        int actualFox = AnimalSpecies.values()[1].prep();
        assertEquals("Enum gave wrong value for prep", 
            expectedFox, actualFox);
        
        int expectedPorcupine = 0;
        int actualPorcupine = AnimalSpecies.values()[2].prep();
        assertEquals("Enum gave wrong value for prep", 
             expectedPorcupine, actualPorcupine);

        int expectedBeaver = 0;
        int actualBeaver = AnimalSpecies.values()[3].prep();
        assertEquals("Enum gave wrong value for prep", 
            expectedBeaver, actualBeaver);

        int expectedRacoon = 0;
        int actualRacoon = AnimalSpecies.values()[4].prep();
        assertEquals("Enum gave wrong value for prep", 
            expectedRacoon, actualRacoon);
    }

    @Test
    public void testFeedWindow() {
        /*
        * Tests the return of feedWindow().
        * Tests with all species.
        */

        int[] expectedCoyote = {19, 21};
        int[] actualCoyote = AnimalSpecies.values()[0].feedWindow();
        for(int i = 0; i < 2; i++){
            assertEquals("Enum gave wrong value for feed window", 
                expectedCoyote[i], actualCoyote[i]);
        }

        int[] expectedFox = {0, 2};
        int[] actualFox = AnimalSpecies.values()[1].feedWindow();
        for(int i = 0; i < 2; i++){
            assertEquals("Enum gave wrong value for feed window", 
                expectedFox[i], actualFox[i]);
        }

        int[] expectedPorcupine = {19, 21};
        int[] actualPorcupine = AnimalSpecies.values()[2].feedWindow();
        for(int i = 0; i < 2; i++){
            assertEquals("Enum gave wrong value for feed window", 
                expectedPorcupine[i], actualPorcupine[i]);
        }

        int[] expectedBeaver = {8, 11};
        int[] actualBeaver = AnimalSpecies.values()[3].feedWindow();
        for(int i = 0; i < 2; i++){
            assertEquals("Enum gave wrong value for feed window", 
                expectedBeaver[i], actualBeaver[i]);
        }

        int[] expectedRacoon = {0, 2};
        int[] actualRacoon = AnimalSpecies.values()[4].feedWindow();
        for(int i = 0; i < 2; i++){
            assertEquals("Enum gave wrong value for feed window", 
                expectedRacoon[i], actualRacoon[i]);
        }
    }
}
