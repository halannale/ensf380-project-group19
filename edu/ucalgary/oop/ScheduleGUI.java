/**
@author 
    Halanna Le
    Grace Jang
    Christy Guirguis
    Gillian Habermehl
@version 3.0
@since 1.0
*/

/*
A graphical user interface for generating a schedule for animal care tasks.
This class extends JFrame and implements the ActionListener and MouseListener interfaces.
*/
package edu.ucalgary.oop;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.awt.FlowLayout;

public class ScheduleGUI extends JFrame implements ActionListener{
    private JLabel instructions;
    private int confirmClose = 0; 
    private static ArrayList<String> tasksToChange;
    private int newStartHour;

    /**
     * Sets the static variable to tasks to be changed.
     * @param tasksToChange the tasks to be changed with specifications of hour in conflict, description, original start hour, animal id, and task id
     */
    public static void setTasksToChange(ArrayList<String> tasksToChange) {
        ScheduleGUI.tasksToChange = tasksToChange;
    }

    public static ArrayList<String> getTasksToChange() {
        return ScheduleGUI.tasksToChange;
    }

    /**
     * Constructs a ScheduleGUI object with the title "Create schedule", sets up the GUI.
    */
    public ScheduleGUI(){
        super("Create schedule");
        setupGUI();
        setSize(500,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);       
    }

    /**
     * Sets up the graphical user interface with a JLabel for instructions and a JButton to generate the schedule.
     * The JLabel and JButton are added to separate JPanels which are then added to the JFrame using a BorderLayout.
     */
    public void setupGUI(){
        instructions = new JLabel("This program will generate a schedule.");
        JButton generate = new JButton("Generate Schedule");
        generate.addActionListener(this);
        
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new FlowLayout());
        JPanel generatePanel = new JPanel();
        generatePanel.setLayout(new FlowLayout());
        
        headerPanel.add(instructions);
        generatePanel.add(generate);
        this.add(headerPanel, BorderLayout.NORTH);
        this.add(generatePanel, BorderLayout.CENTER);
    }
    
    /**
     * The actionPerformed method is called when the generate schedule JButton is clicked. 
     * If a schedule is successfully generated, a confirmation prompt is displayed to the user.
     * If an IllegalSchedule exception is caught, a JDialog is displayed to inform user that the schedule cannot be generated.
     * @param event the ActionEvent object that triggered method.
     */
    public void actionPerformed(ActionEvent event){
        int continueSchedule = 1;
        while (continueSchedule == 1) {
        try {
            SchedulePrint schedulePrint = new SchedulePrint(SQLInfo.getAnimals(), SQLInfo.getTreatments());
            schedulePrint.printSchedule();
            int backup = -1;
            for (int i=0; i<24; i++) {
                if (schedulePrint.getAssignTime().getAvailableTime()[i] == 120) {
                    backup = i;
                }
                if (backup != -1) {
                    confirmClose++;
                    JOptionPane.showMessageDialog(this, String.format("Schedule created, backup volunteer needed at %1$d:00. Confirm they are called by pressing OK", backup));
                    confirmClose--;
                }
                backup  = -1;
            }
            confirmClose++;
            JOptionPane.showMessageDialog(this, "Schedule created");
            confirmClose--;
            continueSchedule = 0;
        }
        catch (IllegalSchedule e) {
            //if illegal schedule
            JDialog illegalDialog = new JDialog(this, "Cannot Generate Schedule", true);
            JLabel illegalLabel = new JLabel("Cannot generate schedule.");
            JLabel illegalInstruction = new JLabel(String.format("Please change the following treatment start time (Original start hour: %1$s:00):", tasksToChange.get(1)));
            JPanel headerPanel = new JPanel();
            headerPanel.setLayout(new FlowLayout());
            headerPanel.add(illegalLabel);
            JPanel inputPanel = new JPanel();
            inputPanel.setLayout(new FlowLayout());
            inputPanel.add(illegalInstruction);
            JLabel task = new JLabel(String.format("%1$s", tasksToChange.get(0)));
            inputPanel.add(task);
            JTextField input = new JTextField("", 2);
            inputPanel.add(input);
            JPanel submitPanel = new JPanel();
            submitPanel.setLayout(new FlowLayout());
            JButton submitButton = new JButton("Submit");
            submitPanel.add(submitButton);
            submitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String userInputValue = input.getText();
                    int oldStartHour = Integer.parseInt(tasksToChange.get(1));
                    newStartHour = Integer.parseInt(userInputValue);
                    int animalID = Integer.parseInt(tasksToChange.get(2));
                    int medicalID = Integer.parseInt(tasksToChange.get(3));
 
                    if (validateInput()) {
                        SQLInfo mySQL = new SQLInfo();
                        mySQL.createConnection();
                        SQLInfo.deleteTreatment(animalID, medicalID, oldStartHour);
                        SQLInfo.insertNewStart(animalID, medicalID, newStartHour);
                        mySQL.close();
                    }
                    illegalDialog.dispose();
                }
            });

            illegalDialog.add(headerPanel, BorderLayout.NORTH);
            illegalDialog.add(inputPanel, BorderLayout.CENTER);
            illegalDialog.add(submitPanel, BorderLayout.SOUTH);
            illegalDialog.setSize(500, 300);
            illegalDialog.setLocationRelativeTo(this);
            illegalDialog.setVisible(true);


            SQLInfo mySQL = new SQLInfo();
            mySQL.createConnection();
            mySQL.selectAnimals();
            mySQL.selectTreatments();
            mySQL.selectTasks();
            mySQL.createAnimalsList();
            mySQL.createTasksList();
            mySQL.createTreatmentList();
            EventQueue.invokeLater(() -> {
                new ScheduleGUI().setVisible(true);
            });
            mySQL.close();
        }
        if (confirmClose == 0 && continueSchedule == 0) {
            System.exit(0);
        }
        }
    }
    private boolean validateInput(){
        
        boolean allInputValid = true;
        
        if(newStartHour < 0 || newStartHour > 23){
            allInputValid = false;
            JOptionPane.showMessageDialog(this, newStartHour + " is an invalid hour input.");
        }
        return allInputValid;
    }
    public static void main(String[] args){
        SQLInfo mySQL = new SQLInfo();
            mySQL.createConnection();
            mySQL.selectAnimals();
            mySQL.selectTreatments();
            mySQL.selectTasks();
            mySQL.createAnimalsList();
            mySQL.createTasksList();
            mySQL.createTreatmentList();
            EventQueue.invokeLater(() -> {
                new ScheduleGUI().setVisible(true);
            });
            mySQL.close();
            for(int i = 0; i < mySQL.getAnimals().length; i++){
                System.out.println(mySQL.getAnimals()[i].getID());
                System.out.println(mySQL.getAnimals()[i].getName());
                System.out.println(mySQL.getAnimals()[i].getSpecies());
            }
    }
}