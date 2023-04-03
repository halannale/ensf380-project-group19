package edu.ucalgary.oop;

public class Treatment {

    private Animal animal;
    private MedicalTask medical;
    private int startHour;

    //constructor
    Treatment(Animal animal, MedicalTask task, int startHour){
        this.animal = animal;
        this.medical = task;
        this.startHour = startHour;
    }

    public int getStartHour() {
        return this.startHour;
    }

    public MedicalTask getMedical() {
        return this.medical;
    }

    public Animal getAnimal() {
        return this.animal;
    }
}
