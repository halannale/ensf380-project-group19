package edu.ucalgary.oop;
import java.util.Arrays;

public class Schedule {
    private Schedule[][] scheduleArr;

    public Schedule(Schedule [][] scheduleArr){
        this.scheduleArr = scheduleArr;
    }

    public boolean backupVolunteer(){
        boolean volunteer = false;
        return volunteer;
    }

    public Animal[] getAnimals(){

    }

    public MedicalTask[] getMedical(){

    }

    public void printSchedule(){
        for (int i = 0; i < scheduleArr.length; i++) {
            System.out.println(Arrays.toString(scheduleArr[i]));
        }
    }

}

