package edu.ucalgary.oop;

public class MedicalTask {
    private String description;
    private int duration;
    private int taskID;
    private int maxWindow;

    public MedicalTask(int id, String description, int duration, int maxWindow) {
        this.taskID = id;
        this.description = description;
        this.duration = duration;
        this.maxWindow = maxWindow;
    }
    
    public String getDescription() {
        return this.description;
    }

    public int getDuration() {
        return this.duration;
    }

    public int getID() {
        return this.taskID;
    }

    public int getMaxWindow() {
        return this.maxWindow;
    }
}
