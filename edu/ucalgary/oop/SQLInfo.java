package edu.ucalgary.oop;

import java.sql.*;
import java.util.ArrayList;
import java.awt.EventQueue;
import java.awt.List;

public class SQLInfo {

    private Connection dbConnect;
    private ResultSet results;

    private static ArrayList<String[]> animals = new ArrayList<String[]>();
    private static ArrayList<String[]> treatments = new ArrayList<String[]>();
    private static ArrayList<String[]> animalsTreatment = new ArrayList<String[]>();
    private static ArrayList<String[]> tasks = new ArrayList<String[]>();
    private Animal []  animals_obj;


    public Animal [] getAnimal(){
        return this.animals_obj;
    }


    public SQLInfo() {
    }

    public void createConnection() {
        try {
            dbConnect = DriverManager.getConnection("jdbc:mysql://localhost/treatments", "root", "mOckingjay");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String[]> selectAnimals() {

        try {
            Statement myStmt = dbConnect.createStatement();
            results = myStmt.executeQuery("SELECT * FROM ANIMALS");

            while (results.next()) {
                String[] animalData = new String[3];
                animalData[0] = Integer.toString(results.getInt("AnimalID"));
                animalData[1] = results.getString("AnimalNickname");
                animalData[2] = results.getString("AnimalSpecies");
                animals.add(animalData);
            }

            myStmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return animals;
    }

    public ArrayList<String[]> selectTreatments() {
        try {
            Statement myStmt = dbConnect.createStatement();
            results = myStmt.executeQuery("SELECT * FROM TREATMENTS");

            treatments.clear();

            
            while (results.next()) {
                String[] treatmentData = new String[4];
                treatmentData[0] = Integer.toString(results.getInt("TreatmentID"));
                int animalID = results.getInt("AnimalID");
                treatmentData[1] = Integer.toString(animalID);
                treatmentData[2] = Integer.toString(results.getInt("TaskID"));
                treatmentData[3] = Integer.toString(results.getInt("StartHour"));
                treatments.add(treatmentData);
            }
    
            myStmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return treatments;
    }

    public ArrayList<String[]> animalTreatments() {
        try {
            Statement myStmt = dbConnect.createStatement();
            results = myStmt.executeQuery("SELECT * FROM TREATMENTS");
            
            while (results.next()) {
                int animalID = results.getInt("AnimalID");
                
                for (String[] animalData : animals) {
                    if (Integer.parseInt(animalData[0]) == animalID) {
                        String[] animalTreatData = new String[3];
                        animalTreatData[0] = animalData[0];
                        animalTreatData[1] = animalData[1];
                        animalTreatData[2] = animalData[2];
                        animalsTreatment.add(animalTreatData);
                        break;
                    }
                }
            }
    
            myStmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return animalsTreatment;
    }

    public ArrayList<String[]> tasks() {
        ArrayList<String[]> tasksTreatment = new ArrayList<String[]>();
        try {
            Statement myStmt = dbConnect.createStatement();
            results = myStmt.executeQuery("SELECT * FROM TREATMENTS");
    
            while (results.next()) {
                int taskID = results.getInt("TaskID");
    
                PreparedStatement stmt = dbConnect.prepareStatement("SELECT * FROM TASKS WHERE TaskID = ?");
                stmt.setInt(1, taskID);
                ResultSet taskResults = stmt.executeQuery();
    
                while (taskResults.next()) {
                    String[] taskData = new String[4];
                    taskData[0] = taskResults.getString("TaskID");
                    taskData[1] = taskResults.getString("Description");
                    taskData[2] = taskResults.getString("Duration");
                    taskData[3] = taskResults.getString("MaxWindow");
                    tasksTreatment.add(taskData);
                }
            }
    
            myStmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return tasksTreatment;
    }
    
    public void close() {
        try {
            results.close();
            dbConnect.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public Animal[] createAnimalsList() {
        int numAnimals = animals.size();
        animals_obj = new Animal[numAnimals];
    
        for (int i = 0; i < numAnimals; i++) {
            String[] animalData = animals.get(i);
            try {
                int animalID = Integer.parseInt(animalData[0]);
                String animalNickname = animalData[1];
                String animalSpecies = animalData[2];
                Animal new_animal = new Animal(animalID, animalNickname, animalSpecies);
                animals_obj[i] = new_animal;
            } catch (NumberFormatException ex) {
                System.out.println("Invalid animal ID: " + animalData[0]);
            } catch (IllegalArgumentException ex) {
                System.out.println("Invalid animal species: " + animalData[2]);
            }
        }
    
        return animals_obj;
    }
    
    

    public static void main(String[] args) {
        
 
        EventQueue.invokeLater(() -> {
            new ScheduleGUI().setVisible(true);        
        });

        SQLInfo mySQL = new SQLInfo();

        mySQL.createConnection();

        animals = mySQL.selectAnimals();
        System.out.println("Here is a list of animals: ");
        for (String[] animalData : animals) {
            System.out.println("AnimalID: " + animalData[0] + ", AnimalNickname: " + animalData[1] + ", AnimalSpecies: " + animalData[2]);
        }
        
        treatments = mySQL.selectTreatments();
        System.out.println("Here is a list of treatments: ");
        for (String[] treatmentData : treatments) {
            System.out.println("TreatmentID: " + treatmentData[0] + ", AnimalID: " + treatmentData[1] + ", TaskID: " + treatmentData[2] + ", StartHour: " + treatmentData[3]);
        }

        animalsTreatment = mySQL.animalTreatments();
        System.out.println("Here is a list of ANIMAL treatments: ");
        for (String[] animalID : animalsTreatment) {
            System.out.println("AnimalID: " + animalID[0] + ", AnimalNickname: " + animalID[1] + ", AnimalSpecies: " + animalID[2]);
        }


        tasks = mySQL.tasks();
        System.out.println("Here is a list of TASKS: ");
        for (String[] taskID : tasks) {
            System.out.println("taskID: " + taskID[0] + ", Description: " + taskID[1] + ", Duration: " + taskID[2]);
        }
        
        SQLInfo obj = new SQLInfo();

        
        Animal[] animalArray = obj.createAnimalsList();

        
        for (Animal animal : animalArray) {
            System.out.println(animal.getID() + ", " + animal.getName() + ", " + animal.getSpecies());
        }

        mySQL.close();
    }
}
