package edu.ucalgary.oop;
import java.util.*;

public class AssignTime {
    private HashMap<Integer, ArrayList<Treatment>> schedule;

    // this would probably be called in the sql file since thats where animals and treatment lists are stored
    public AssignTime (Animal[] animals, Treatment[] treatments) {
        HashMap<Integer, ArrayList<Treatment>> schedule = new HashMap<>();
        ArrayList<ArrayList<Treatment>> hourlyTasks = new ArrayList<>(); //initialize the hourly tasks array
        for (int i = 0; i < 24; i++) {
            hourlyTasks.add(new ArrayList<Treatment>());
        }
        // idea is to run this loop until all tasks are assigned
        // also to assign tasks with lowest maxwindow first since theres not many options for them to go, 
        // and then move to feeding, then cage clearning tasks
        while(treatments.length != 0) { //after each treatment is assigned, remove it from array
            int taskIndex = findLowestMaxWindowIndex(treatments); //currently the highest priority
            if (treatments[taskIndex].getMedical().getMaxWindow() == 1) {
                int hour = treatments[taskIndex].getStartHour();
                hourlyTasks.get(hour).add(treatments[taskIndex]);
                //remove the current treatment from the treatments array
                Treatment[] newTreatments = new Treatment[treatments.length-1];
                int j = 0;
                for (int i = 0; i < treatments.length; i++) {
                    if (i != taskIndex) {
                        newTreatments[j] = treatments[i];
                        j++;
                    }
                }
                treatments = newTreatments;
            } 

            // what if maxWindow is more than 1?
        }

        //VERY END ADD TO HASHMAP
        //for (int i = 0; i < 24; i++) {
        //    schedule.put(i, tasks[i]);
        //}
    }

    private int findLowestMaxWindowIndex(Treatment[] treatments) {
        int lowestMaxWindowIndex = 0;
        int lowestMaxWindowValue = treatments[0].getMedical().getMaxWindow();

        for (int i = 1; i < treatments.length; i++) {
            int currentMaxWindow = treatments[i].getMedical().getMaxWindow();
            if (currentMaxWindow < lowestMaxWindowValue) {
                lowestMaxWindowValue = currentMaxWindow;
                lowestMaxWindowIndex = i;
            }
        }
        return lowestMaxWindowIndex;
    }
}
