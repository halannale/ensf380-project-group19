package edu.ucalgary.oop;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.time.LocalDate;


public class SchedulePrint {
    private AssignTime assignTime;

    public SchedulePrint(Animal[] animals, Treatment[] treatments) throws IllegalSchedule {
        AssignTime assignTime = new AssignTime(animals, treatments);
        
        this.assignTime = assignTime;
    }

    public void printSchedule() {
        LocalDate currentDate = LocalDate.now();
        String dateText = currentDate.toString();


        int startTime = 0;
        try{
            FileWriter writer = new FileWriter("schedule.txt", true);
            writer.write("Schedule for " + dateText + "\n");
            writer.close();

        }


        catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 24; i++) { 
            ArrayList<String> tasksInHour = assignTime.getSchedule().get(i);
            int[] hour = assignTime.getAvailableTime();
            int indexhour = hour[i];
            if (indexhour == 120){
                startTime = i;
            }
            try {
                FileWriter writer = new FileWriter("schedule.txt", true);
                if(indexhour == 120){
                    writer.write(startTime+":00" + " [+ backup volunteer]" +"\n");
                }
                else {
                    if(tasksInHour.size() !=0 ){
                        writer.write(i+":00" +"\n");
                    }
                }
                
                for (String task : tasksInHour) {
                    
                    writer.write("* " + task + "\n");
                }
                writer.close();
            } catch (IOException e) {

                e.printStackTrace();
            }
        }
        System.out.println("Schedule written to schedule.txt");
    }

    public AssignTime getAssignTime() {
        return this.assignTime;
    }
}
