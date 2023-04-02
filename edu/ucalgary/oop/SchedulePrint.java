package edu.ucalgary.oop;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class SchedulePrint {
    private HashMap<Integer, ArrayList<String>> schedule;

    public SchedulePrint(HashMap<Integer, ArrayList<String>> schedule) {
        this.schedule = schedule;
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

    public static void main(String[] args) {
        Animal[] animals = new Animal[8];
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
        treatments[0] = new Treatment(animals[2], tasks[0], 13);
        treatments[1] = new Treatment(animals[5], tasks[0], 13);
        treatments[2] = new Treatment(animals[5], tasks[0], 13);
        treatments[3] = new Treatment(animals[1], tasks[0], 13);
        treatments[4] = new Treatment(animals[4], tasks[0], 13);

        AssignTime assignTime = new AssignTime(animals, treatments);
        HashMap<Integer, ArrayList<String>> schedule = assignTime.getSchedule();
        SchedulePrint schedulePrint = new SchedulePrint(schedule);
        schedulePrint.printSchedule();
    }
}
