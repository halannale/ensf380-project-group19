package edu.ucalgary.oop;
import java.util.*;

public class Animal {
    private String name;
    private String species;
    private MedicalTask[] medical;
    private int animalID;

    public Animal(String name, int id, String species) throws IllegalArgumentException{
        this.name = name;
        this.animalID = id;
        if (!checkAnimalSpecies(species)) {
            throw new IllegalArgumentException("Invalid species");
        }
        this.species = species;
    }

    public String getName() {
        return this.name;
    }

    public String getSpecies() {
        return this.species;
    }

    public MedicalTask[] getTasks() {
        return this.medical;
    }

    public int getID() {
        return this.animalID;
    }

    private boolean checkAnimalSpecies( String species) {
        for (int i=0; i < AnimalSpecies.values().length; i++) {
            if (Objects.equals(species, AnimalSpecies.values()[i].toString())) {
                return true;
            }
        }
        return false;
    }
}
