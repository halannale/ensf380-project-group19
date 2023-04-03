package edu.ucalgary.oop;

import java.sql.*;
import java.util.ArrayList;

public class SQLInfo {

    private Connection dbConnect;
    private ResultSet results;
    private ArrayList<String[]> animals = new ArrayList<String[]>();
    private ArrayList<String[]> treatments = new ArrayList<String[]>();
    private ArrayList<String[]> animalsTreatment = new ArrayList<String[]>();


    public SQLInfo() {
    }

    public void createConnection() {

        try {
            dbConnect = DriverManager.getConnection("jdbc:mysql://localhost/treatments", "root", "");
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
            
            while (results.next()) {
                String[] treatmentData = new String[4];
                treatmentData[0] = Integer.toString(results.getInt("TreatmentID"));
                int animalID = results.getInt("AnimalID");
                treatmentData[1] = Integer.toString(animalID);
                treatmentData[2] = Integer.toString(results.getInt("TaskID"));
                treatmentData[3] = Integer.toString(results.getInt("StartHour"));
                treatments.add(treatmentData);
                
                // Find the animal with the corresponding animalID
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
        return treatments;
    }
    
    public void close() {
        try {
            results.close();
            dbConnect.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        SQLInfo mySQL = new SQLInfo();

        mySQL.createConnection();

        ArrayList<String[]> allAnimals = mySQL.selectAnimals();
        System.out.println("Here is a list of animals: ");
        for (String[] animalData : allAnimals) {
            System.out.println("AnimalID: " + animalData[0] + ", AnimalNickname: " + animalData[1] + ", AnimalSpecies: " + animalData[2]);
        }

        ArrayList<String[]> allTreatments = mySQL.selectTreatments();
        System.out.println("Here is a list of treatments: ");
        for (String[] treatmentData : allTreatments) {
            System.out.println("TreatmentID: " + treatmentData[0] + ", AnimalID: " + treatmentData[1] + ", TaskID: " + treatmentData[2] + ", StartHour: " + treatmentData[3]);
        }

        mySQL.close();
    }
}
