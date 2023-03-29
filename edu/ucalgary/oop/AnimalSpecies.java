package edu.ucalgary.oop;

public enum AnimalSpecies {
    COYOTE,
    FOX,
    PORCUPINE,
    BEAVER,
    RACOON;

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

    public int prep() {
        switch(this) {
            case COYOTE:
                return 10;
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
}
