package edu.ucalgary.oop;
import java.util.*;

public class Animal {
    private String name;
    private String species;
    private int animalID;

    public Animal(int id, String name, String species) throws IllegalArgumentException{
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

    public int getID() {
        return this.animalID;
    }

    private boolean checkAnimalSpecies( String species) {
        for (int i=0; i < AnimalSpecies.values().length; i++) {
            if (Objects.equals(species, AnimalSpecies.values()[i].toString().toLowerCase())) {
                return true;
            }
        }
        return false;
    }
}
