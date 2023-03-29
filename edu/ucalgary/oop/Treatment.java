package edu.ucalgary.oop;

public class Treatment {

    private int animal;
    private int medical;
    private int startHour;

    //constructor
    Treatment(int aID, int tID, int startHour){
        this.animal = aID;
        this.medical = tID;
        this.startHour = startHour;
    }

    //AnimalTask is not constructor, needs to be 
    //fixed with right return variable/code

    public void AnimalTask(int aID, int tID, int startHour){
        this.animal = aID;
        this.medical = tID;
        this.startHour = startHour;
    }


    public int getStartHour() {
        return this.startHour;
    }

    public int getMedical() {
        return this.medical;
    }

    public int getAnimal() {
        return this.animal;
    }

    
}
