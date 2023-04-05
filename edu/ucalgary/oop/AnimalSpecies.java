/**
@author 
    Halanna Le
    Grace Jang
    Christy Guirguis
    Gillian Habermehl
@version 1.1
@since 1.0
*/

/*
This enum represents the different species of animals that can be admitted to the centre.
Each enum value has methods to get information about how to care for and feed the animal.
*/
package edu.ucalgary.oop;

public enum AnimalSpecies {
    COYOTE,
    FOX,
    PORCUPINE,
    BEAVER,
    RACOON;

    /**
     * Returns the time to clean the cage of an animal of this species.
     * 
     * @return the time to clean the cage of an animal of this species
     */
    public int cage() {
        switch(this) {
            case COYOTE:
                return 5;
            case FOX:
                return 5;
            case PORCUPINE:
                return 10;
            case BEAVER:
                return 5;
            default: //value checked before already, so there should not be an invalid enum, remaining would be RACOON
                return 5;
        }
    }

    /**
     * Returns the time needed to feed an animal of this species.
     * 
     * @return the time needed to feed an animal of this species
     */
    public int feed() {
        switch(this) {
            case COYOTE:
                return 5;
            case FOX:
                return 5;
            case PORCUPINE:
                return 5;
            case BEAVER:
                return 5;
            default: //value checked before already, so there should not be an invalid enum, remaining would be RACOON
                return 5;
        }
    }

    /**
     * Returns the amount of preparation time needed to prepare an animal of this species for feeding.
     * 
     * @return the amount of preparation time needed to prepare an animal of this species for feeding
     */
    public int prep() {
        switch(this) {
            case COYOTE:
                return 50;
            case FOX:
                return 5;
            case PORCUPINE:
                return 0;
            case BEAVER:
                return 0;
            default: //value checked before already, so there should not be an invalid enum, remaining would be RACOON
                return 0;
        }
    }

    /**
     * Returns an array containing the feeding window for an animal of this species, represented as the start
     * and end hours during which the animal can be fed.
     * 
     * @return an array containing the feeding window for an animal of this species
     */
    public int[] feedWindow() {
        switch(this) {
            case COYOTE:
                return new int[]{19,21};
            case FOX:
                return new int[]{0,2};
            case PORCUPINE:
                return new int[]{19,21};
            case BEAVER:
                return new int[]{8,11};
            default: //value checked before already, so there should not be an invalid enum, remaining would be RACOON
                return new int[]{0,2};
        }
    }

    public static void main(String[] args){
        MedicalTask task1 = new MedicalTask(9, "Eyedrops", 25, 1);
        MedicalTask task2 = new MedicalTask(10, "Inspect broken leg", 5, 2);
    
        Animal animal1 = new Animal(1, "Loner", "coyote");
        Animal animal2 = new Animal(2, "Biter", "coyote");
        //test IllegalArgumentException
    
        Treatment treatment1 = new Treatment(animal1, task1, 22);
        Treatment treatment2 = new Treatment(animal1, task2, 10);
    
        Animal[] animals = {animal1, animal2};
        Treatment[] treatments = {treatment1, treatment2};
        MedicalTask[] tasks = {task1, task2};

        AnimalSpecies coyote = COYOTE;
        

        for(int j = 0; j < 2; j++){
            System.out.println(coyote.feedWindow()[j]);
        }
        AnimalSpecies fox = FOX;
        for(int j = 0; j < 2; j++){
            System.out.println(fox.feedWindow()[j]);
        }
        AnimalSpecies porcupine = PORCUPINE;
        for(int j = 0; j < 2; j++){
            System.out.println(porcupine.feedWindow()[j]);
        }
        AnimalSpecies beaver = BEAVER;
        for(int j = 0; j < 2; j++){
            System.out.println(beaver.feedWindow()[j]);
        }
        AnimalSpecies racoon = RACOON;
        for(int j = 0; j < 2; j++){
            System.out.println(racoon.feedWindow()[j]);
        }
        
        System.out.println(AnimalSpecies.values()[0]);
    
        
    }
}
