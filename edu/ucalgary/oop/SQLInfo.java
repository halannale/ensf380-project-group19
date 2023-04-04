package edu.ucalgary.oop;

import java.sql.*;
import java.util.ArrayList;
import java.awt.EventQueue;

public class SQLInfo {

    private static Connection dbConnect;
    private ResultSet results;

    private ArrayList<String[]> animals = new ArrayList<String[]>();
    private ArrayList<String[]> treatments = new ArrayList<String[]>();
    private ArrayList<String[]> tasks = new ArrayList<String[]>();
    private Animal [] animals_obj;
    private Treatment [] treatment_obj;
    private MedicalTask [] tasks_obj;



    public Animal [] getAnimals(){
        return this.animals_obj;
    }

    public Treatment [] getTreatments(){
        return this.treatment_obj;
    }

    public SQLInfo() {
        /* 
        selectAnimals();
        selectTasks();
        selectTreatments();
        createTasksList();

        this.animals_obj = createAnimalsList();
        this.treatment_obj = createTreatmentList();
        this.tasks_obj = createTasksList();
        */
    }

    public void createConnection() {
        try {
            dbConnect = DriverManager.getConnection("jdbc:mysql://localhost/treatments", "oop", "password");
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

    public ArrayList<String[]> selectTasks() {
        try {
            Statement myStmt = dbConnect.createStatement();
            results = myStmt.executeQuery("SELECT * FROM TASKS");

            while (results.next()) {
                String[] taskData = new String[4];
                taskData[0] = Integer.toString(results.getInt("TaskID"));
                taskData[1] = results.getString("Description");
                taskData[2] = results.getString("Duration");
                taskData[3] = results.getString("MaxWindow");

                tasks.add(taskData);
            }

            myStmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return tasks;
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

    public MedicalTask[] createTasksList() {
        int numTasks = tasks.size();
        tasks_obj = new MedicalTask[numTasks];
    
        for (int i = 0; i < numTasks; i++) {
            String[] taskData = tasks.get(i);
            int taskID = Integer.parseInt(taskData[0]);
            String description = taskData[1];
            int duration = Integer.parseInt(taskData[2]);
            int maxWindow = Integer.parseInt(taskData[3]); 
            MedicalTask new_task = new MedicalTask(taskID, description, duration, maxWindow);
            tasks_obj[i] = new_task;
        }
    
        return tasks_obj;
    }

    public Treatment[] createTreatmentList() {
        int numTreatments = treatments.size();
        treatment_obj = new Treatment[numTreatments];

        for (int i = 0; i < numTreatments; i++){
            String[] treatmentData = treatments.get(i);
            int animalIndex = Integer.parseInt(treatmentData[1]) - 1;
            Animal animal = animals_obj[animalIndex];
            int taskIndex = Integer.parseInt(treatmentData[2]) - 1;
            MedicalTask task = tasks_obj[taskIndex];
            int startHour = Integer.parseInt(treatmentData[3]);
            
            Treatment new_treatment = new Treatment(animal, task, startHour);
            treatment_obj[i] = new_treatment;
            
        }
        return treatment_obj;
    }

    public static void insertNewStart(int animalID, int taskID, int startHour){

        try {
            String query = "INSERT INTO TREATMENTS (animalID, taskID, startHour) VALUES (?,?,?)";
            PreparedStatement myStmt = dbConnect.prepareStatement(query);
            
            myStmt.setInt(1, animalID);
            myStmt.setInt(2, taskID);
            myStmt.setInt(3, startHour);
            
            int rowCount = myStmt.executeUpdate();
            System.out.println("Rows affected: " + rowCount);
            
            myStmt.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }    

    
    public static void deleteTreatment(int animalID, int taskID, int startHour){
                    
        try {
            String query = "DELETE FROM TREATMENTS WHERE animalID = ? AND taskID = ? AND startHour = ?";
            PreparedStatement myStmt = dbConnect.prepareStatement(query);
            
            myStmt.setInt(1, animalID);
            myStmt.setInt(2, taskID);
            myStmt.setInt(3, startHour);
            
            int rowCount = myStmt.executeUpdate();
            System.out.println("Rows affected: " + rowCount);
            
            myStmt.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    public void close() {
        try {
            if (results != null) {
                results.close();
            }
            dbConnect.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        SQLInfo mySQL = new SQLInfo();

        mySQL.createConnection();

        mySQL.selectAnimals();
        mySQL.selectTreatments();
        mySQL.selectTasks();

        mySQL.createAnimalsList();
        mySQL.createTasksList();
        Treatment [] hi = mySQL.createTreatmentList();

        System.out.println(hi[0].getStartHour());
/* 
         EventQueue.invokeLater(() -> {
            new ScheduleGUI().setVisible(true);        
        });
*/
        mySQL.close();
    }
}
