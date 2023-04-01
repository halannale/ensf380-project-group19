package edu.ucalgary.oop;
import java.util.*;

public class AssignTime {
    private HashMap<Integer, ArrayList<Treatment>> schedule;
    private int[] availableTime = new int[24];

    // this would probably be called in the sql file since thats where animals and treatment lists are stored
    public AssignTime (Animal[] animals, Treatment[] treatments) {
        HashMap<Integer, ArrayList<String>> schedule = new HashMap<>();
        ArrayList<ArrayList<String>> hourlyTasks = new ArrayList<>(); //initialize the hourly tasks array

        for (int i = 0; i < 24; i++) {
            hourlyTasks.add(new ArrayList<String>());
            availableTime[i] = 60;
        }
        // run loop until all tasks are assigned
        // assign medical tasks with lowest maxwindow first since theres not many options for them to go, 
        while(treatments.length != 0) { //after each treatment is assigned, remove it from array
            int taskIndex = findLowestMaxWindowIndex(treatments); //currently the highest priority
            if (treatments[taskIndex].getMedical().getMaxWindow() == 1) {
                int hour = treatments[taskIndex].getStartHour();
                hourlyTasks.get(hour-1).add(treatments[taskIndex].getMedical().getDescription());
                //remove the current treatment from the treatments array
                treatments = removeTreatment(treatments, taskIndex);
            }
            else {
                // assign earliest starting hour that has tasks that add up to less than 60 mins
                int hour = treatments[taskIndex].getStartHour();
                int duration = treatments[taskIndex].getMedical().getDuration();
                boolean hourFound = false;
                while (!hourFound && hour < 25) {
                    if (hour >= treatments[taskIndex].getStartHour() && hour <= treatments[taskIndex].getStartHour() + treatments[taskIndex].getMedical().getMaxWindow() - 1) {
                        ArrayList<String> tasksInHour = hourlyTasks.get(hour-1);
                        int totalDuration = 0;
                        for (String task : tasksInHour) {
                            totalDuration += getTreatmentFromDescription(treatments, task).getMedical().getDuration();
                        }
                        if (totalDuration + duration <= availableTime[hour-1]) {
                            tasksInHour.add(treatments[taskIndex].getMedical().getDescription());
                            treatments = removeTreatment(treatments, taskIndex);
                            hourFound = true;
                        }
                    }
                    hour++;
                }
                if (!hourFound) {
                    //backup volunteer required
                    hour = treatments[taskIndex].getStartHour();
                    while (hour < 25) {
                        if (hour >= treatments[taskIndex].getStartHour() && hour <= treatments[taskIndex].getStartHour() + treatments[taskIndex].getMedical().getMaxWindow() - 1) {
                            availableTime[hour-1] += 60; // backup volunteer called, so extra time is added
                            ArrayList<String> tasksInHour = hourlyTasks.get(hour-1);
                            int totalDuration = 0;
                            for (String task : tasksInHour) {
                                totalDuration += getTreatmentFromDescription(treatments, task).getMedical().getDuration();
                            }
                            if (totalDuration + duration <= availableTime[hour-1]) {
                                tasksInHour.add(treatments[taskIndex].getMedical().getDescription());
                                treatments = removeTreatment(treatments, taskIndex);
                                break;
                            }
                        }
                        hour++;
                    }
                    if (hour >=25) {
                        //schedule not possible, throw error
                    }
                }
            }


            // assign times for each animal and make list for each species
            ArrayList<Animal> coyotes = new ArrayList<Animal>();
            ArrayList<Animal> foxes = new ArrayList<Animal>();
            ArrayList<Animal> porcupines = new ArrayList<Animal>();
            ArrayList<Animal> beavers = new ArrayList<Animal>();
            ArrayList<Animal> racoons = new ArrayList<Animal>();
            int prepTime = 0;
            int feedTime = 0;
            int[] feedWindow;
            int cageTime = 0;
            for (int i=0; i<animals.length; i++) {
                if(animals[i].getSpecies() == "coyote") {
                    coyotes.add(animals[i]);
                }
                if(animals[i].getSpecies() == "fox") {
                    foxes.add(animals[i]);
                }
                if(animals[i].getSpecies() == "porcupine") {
                    porcupines.add(animals[i]);
                }
                if(animals[i].getSpecies() == "beaver") {
                    beavers.add(animals[i]);
                }
                if(animals[i].getSpecies() == "racoon") {
                    racoons.add(animals[i]);
                }
            }
            // assign feed+prep time for coyotes



            // assign cage cleaning

        }

        //VERY END ADD TO HASHMAP
        for (int i = 0; i < 24; i++) {
            schedule.put(i, hourlyTasks.get(i));
            System.out.println(hourlyTasks.get(i)); // remove. this is for double checking
        }
    }

    public int[] getAvailableTime() {
        return this.availableTime;
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

    private Treatment[] removeTreatment(Treatment[] treatments, int taskIndex) {
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
        return treatments;
    }

    private static Treatment getTreatmentFromDescription(Treatment[] treatments, String description) {
        for (Treatment treatment : treatments) {
            if (treatment.getMedical().getDescription().equals(description)) {
                return treatment;
            }
        }
        return null;
    }
}
