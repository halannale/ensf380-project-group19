package edu.ucalgary.oop;
import java.util.*;

public class AssignTime {
    private HashMap<Integer, ArrayList<Treatment>> schedule;
    private int[] availableTime = new int[24];

    // this would probably be called in the sql file since thats where animals and treatment lists are stored
    public AssignTime (Animal[] animals, Treatment[] currentTreatments) {
        HashMap<Integer, ArrayList<String>> schedule = new HashMap<>();
        ArrayList<ArrayList<String>> hourlyTasks = new ArrayList<>(); //initialize the hourly tasks array
        Treatment[] totalTreatments = new Treatment[currentTreatments.length];
        for (int i=0; i < totalTreatments.length; i++) {
            totalTreatments[i] = new Treatment(currentTreatments[i].getAnimal(), currentTreatments[i].getMedical(), currentTreatments[i].getStartHour());
        }
        for (int i = 0; i < 24; i++) {
            hourlyTasks.add(new ArrayList<String>());
            availableTime[i] = 60;
        }
        // run loop until all tasks are assigned
        // assign medical tasks with lowest maxwindow first since theres not many options for them to go, 
        while(currentTreatments.length != 0) { //after each treatment is assigned, remove it from array
            int taskIndex = findLowestMaxWindowIndex(currentTreatments); //currently the highest priority
            if (currentTreatments[taskIndex].getMedical().getMaxWindow() == 1) {
                int hour = currentTreatments[taskIndex].getStartHour();
                ArrayList<String> tasksInHour = hourlyTasks.get(hour);
                int totalDuration = 0;
                int duration = currentTreatments[taskIndex].getMedical().getDuration();
                for (String task : tasksInHour) {
                    if (task != null) {
                        totalDuration += getTreatmentFromDescription(totalTreatments, task).getMedical().getDuration();
                    }
                }

                if (totalDuration + duration <= availableTime[hour]) {
                    hourlyTasks.get(hour).add(currentTreatments[taskIndex].getMedical().getDescription() + "(" + currentTreatments[taskIndex].getAnimal().getName() + ")");
                    //remove the current treatment from the treatments array
                    currentTreatments = removeTreatment(currentTreatments, taskIndex);
                }
                else if (availableTime[hour] == 60){
                    availableTime[hour] += 60; // backup volunteer called, so extra time is added
                    if (totalDuration + duration <= availableTime[hour]) {
                        hourlyTasks.get(hour).add(currentTreatments[taskIndex].getMedical().getDescription() + "(" + currentTreatments[taskIndex].getAnimal().getName() + ")");
                        //remove the current treatment from the treatments array
                        currentTreatments = removeTreatment(currentTreatments, taskIndex);
                    }
                    else {
                        System.out.println("Cannot generate schedule");
                        System.exit(1);
                    }
                }
                else {
                    System.out.println("Cannot generate schedule");
                    System.exit(1);
                }

            }
            else {
                // assign earliest starting hour that has tasks that add up to less than 60 mins
                int hour = currentTreatments[taskIndex].getStartHour();
                int duration = currentTreatments[taskIndex].getMedical().getDuration();
                boolean hourFound = false;
                while (!hourFound && hour < 25) {
                    if (hour >= currentTreatments[taskIndex].getStartHour() && hour <= currentTreatments[taskIndex].getStartHour() + currentTreatments[taskIndex].getMedical().getMaxWindow() - 1) {
                        ArrayList<String> tasksInHour = hourlyTasks.get(hour);
                        int totalDuration = 0;
                        for (String task : tasksInHour) {
                            if (task != null) {
                                totalDuration += getTreatmentFromDescription(totalTreatments, task).getMedical().getDuration();
                            }
                        }
                        if (totalDuration + duration <= availableTime[hour]) {
                            tasksInHour.add(currentTreatments[taskIndex].getMedical().getDescription() + "(" + currentTreatments[taskIndex].getAnimal().getName() + ")");
                            currentTreatments = removeTreatment(currentTreatments, taskIndex);
                            hourFound = true;
                        }
                    }
                    hour++;
                }
                if (!hourFound) {
                    //backup volunteer required
                    hour = currentTreatments[taskIndex].getStartHour();
                    while (hour < 25 && availableTime[hour] == 60) {
                        if (hour >= currentTreatments[taskIndex].getStartHour() && hour <= currentTreatments[taskIndex].getStartHour() + currentTreatments[taskIndex].getMedical().getMaxWindow() - 1) {
                            availableTime[hour] += 60; // backup volunteer called, so extra time is added
                            ArrayList<String> tasksInHour = hourlyTasks.get(hour);
                            int totalDuration = 0;
                            for (String task : tasksInHour) {
                                if (task != null) {
                                    totalDuration += getTreatmentFromDescription(totalTreatments, task).getMedical().getDuration();
                                }
                            }
                            if (totalDuration + duration <= availableTime[hour]) {
                                tasksInHour.add(currentTreatments[taskIndex].getMedical().getDescription() + "(" + currentTreatments[taskIndex].getAnimal().getName() + ")");
                                currentTreatments = removeTreatment(currentTreatments, taskIndex);
                                hourFound = true;
                                break;
                            }
                        }
                        hour++;
                    }
                    if (!hourFound) {
                        System.out.println("Cannot generate schedule");
                        System.exit(1);
                    }
                }
            }
            /*
            // assign times for each animal and make list for each species
            ArrayList<Animal> coyotes = new ArrayList<Animal>();
            ArrayList<Animal> foxes = new ArrayList<Animal>();
            ArrayList<Animal> porcupines = new ArrayList<Animal>();
            ArrayList<Animal> beavers = new ArrayList<Animal>();
            ArrayList<Animal> racoons = new ArrayList<Animal>();
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
            int hour = AnimalSpecies.COYOTE.feedWindow()[0];
            int duration = AnimalSpecies.COYOTE.feed();
            boolean hourFound = false;
            while (!hourFound && hour < 25) {
                if (hour >= AnimalSpecies.COYOTE.feedWindow()[0] && hour <= AnimalSpecies.COYOTE.feedWindow()[0] + 2) {
                    ArrayList<String> tasksInHour = hourlyTasks.get(hour-1);
                    int totalDuration = calculateDuration(treatments, tasksInHour, coyotes, foxes, porcupines, beavers, racoons);
                    if (totalDuration + duration <= availableTime[hour-1]) {
                        tasksInHour.add("Feed coyotes");
                        hourFound = true;
                    }
                }
                hour++;
            }
            if (!hourFound) {
                //backup volunteer required
                hour = AnimalSpecies.COYOTE.feedWindow()[0];
                while (hour < 25 && availableTime[hour-1] == 60) {
                    if (hour >= AnimalSpecies.COYOTE.feedWindow()[0] && hour <= AnimalSpecies.COYOTE.feedWindow()[0] + 2) {
                        availableTime[hour-1] += 60; // backup volunteer called, so extra time is added
                        ArrayList<String> tasksInHour = hourlyTasks.get(hour-1);
                        int totalDuration = calculateDuration(treatments, tasksInHour, coyotes, foxes, porcupines, beavers, racoons);
                        if (totalDuration + duration <= availableTime[hour-1]) {
                            tasksInHour.add("Feed coyotes");
                            hourFound = true;
                            break;
                        }
                    }
                    hour++;
                }
                if (!hourFound) {
                    //schedule not possible, throw error
                }
            }


            // assign cage cleaning

        }*/

        //VERY END ADD TO HASHMAP
        for (int i = 0; i < 24; i++) {
            schedule.put(i, hourlyTasks.get(i));
        }
        }
        for (int i=0; i < 24; i++) {
            System.out.println(schedule.get(i)); // remove. this is for double checking
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

    private Treatment getTreatmentFromDescription(Treatment[] treatments, String description) {
        String descriptionToMatch = description.split("\\(")[0];
        for (Treatment treatment : treatments) {
            if (treatment.getMedical().getDescription().equals(descriptionToMatch)) {
                return treatment;
            }
        }
        return null;
    }

    private int calculateDuration(Treatment[] treatments, ArrayList<String> tasksInHour, ArrayList<Animal> coyotes, ArrayList<Animal> foxes, ArrayList<Animal> porcupines, ArrayList<Animal> beavers, ArrayList<Animal> racoons) {
        int totalDuration = 0;
        for (String task : tasksInHour) {
            if(task == "Feed coyotes") {
                totalDuration += AnimalSpecies.COYOTE.feed() * coyotes.size();
            }
            else if(task == "Feed foxes") {
                totalDuration += AnimalSpecies.FOX.feed() * foxes.size();
            }
            else if(task == "Feed porcupines") {
                totalDuration += AnimalSpecies.PORCUPINE.feed() * porcupines.size();
            }
            else if(task == "Feed beavers") {
                totalDuration += AnimalSpecies.BEAVER.feed() * beavers.size();
            }
            else if(task == "Feed racoons") {
                totalDuration += AnimalSpecies.RACOON.feed() * racoons.size();
            }
            else {
                totalDuration += getTreatmentFromDescription(treatments, task).getMedical().getDuration();
            }  
        }
        return totalDuration;
    }
    public static void main(String[] args) {
        Animal[] animals = new Animal[10];
        animals[0] = new Animal(1, "Loner", "coyote");
        animals[1] = new Animal(2, "Biter", "coyote");
        animals[2] = new Animal(3, "Bitter", "coyote");
        animals[3] = new Animal(4, "Pencil", "coyote");
        animals[4] = new Animal(5, "Eraser", "coyote");
        animals[5] = new Animal(6, "Annie, Oliver and Mowgli", "fox");
        animals[6] = new Animal(7, "Slinky", "fox");
        animals[7] = new Animal(8, "Spike", "porcupine");

        MedicalTask[] tasks = new MedicalTask[3];
        tasks[0] = new MedicalTask(1, "Kit feeding", 30, 2);
        tasks[1] = new MedicalTask(2, "Rebandage leg wound", 20, 1);
        tasks[2] = new MedicalTask(3, "Apply burn ointment back", 10, 3);

        Treatment[] treatments = new Treatment[5];
        treatments[0] = new Treatment(animals[2], tasks[0],13);
        treatments[1] = new Treatment(animals[5], tasks[0], 13);
        treatments[2] = new Treatment(animals[5], tasks[0], 13);
        treatments[3] = new Treatment(animals[1], tasks[0], 13);
        treatments[4] = new Treatment(animals[4], tasks[0], 13);
        
        AssignTime schedule = new AssignTime(animals, treatments);
    }
}
