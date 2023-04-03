package edu.ucalgary.oop;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class SchedulePrint {
    private HashMap<Integer, ArrayList<String>> schedule;
    private AssignTime assignTime;

    public SchedulePrint(Animal[] animals, Treatment[] treatments) throws IllegalSchedule {
        AssignTime assignTime = new AssignTime(animals, treatments);
        HashMap<Integer, ArrayList<String>> schedule = assignTime.getSchedule();
        this.schedule = schedule;
        this.assignTime = assignTime;
    }

    public void printSchedule() {
        for (int i = 0; i < 24; i++) {
            ArrayList<String> tasksInHour = schedule.get(i);
            try {
                FileWriter writer = new FileWriter("schedule.txt", true);
                for (String task : tasksInHour) {
                    writer.write(task + "\n");
                }
                writer.close();
            } catch (IOException e) {

                e.printStackTrace();
            }
        }
        System.out.println("Schedule written to schedule.txt");
    }

    public HashMap<Integer, ArrayList<String>> getSchedule() {
        return this.schedule;
    }

    public AssignTime getAssignTime() {
        return this.assignTime;
    }
}
