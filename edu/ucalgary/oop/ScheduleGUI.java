package edu.ucalgary.oop;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.*;
import java.awt.event.*;
import java.awt.FlowLayout;

public class ScheduleGUI extends JFrame implements ActionListener, MouseListener{
    private JLabel instructions;
    private int confirmClose = 0; 

    public ScheduleGUI(){
        super("Create schedule");
        setupGUI();
        setSize(500,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);       
    }

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
    
    public void actionPerformed(ActionEvent event){
        Animal[] animals = new Animal[8];
        animals[0] = new Animal(1, "Loner", "coyote");
        animals[1] = new Animal(2, "Biter", "coyote");
        animals[2] = new Animal(3, "Bitter", "coyote");
        animals[3] = new Animal(4, "Pencil", "coyote");
        animals[4] = new Animal(5, "Eraser", "coyote");
        animals[5] = new Animal(6, "Annie, Oliver and Mowgli", "fox");
        animals[6] = new Animal(7, "Slinky", "fox");
        animals[7] = new Animal(8, "Spike", "porcupine");

        MedicalTask[] tasks = new MedicalTask[3];
        tasks[0] = new MedicalTask(1, "Kit feeding", 30, 2);
        tasks[1] = new MedicalTask(2, "Rebandage leg wound", 20, 1);
        tasks[2] = new MedicalTask(3, "Apply burn ointment back", 10, 3);

        Treatment[] treatments = new Treatment[5];
        treatments[0] = new Treatment(animals[2], tasks[0],13);
        treatments[1] = new Treatment(animals[5], tasks[0], 14);
        treatments[2] = new Treatment(animals[5], tasks[0], 15);
        treatments[3] = new Treatment(animals[1], tasks[0], 16);
        treatments[4] = new Treatment(animals[4], tasks[0], 17);
        try {
            AssignTime schedule = new AssignTime(animals, treatments);
            int backup = 0;
            for (int i=0; i<24; i++) {
                if (schedule.getAvailableTime()[i] == 120) {
                    backup = 1;
                }
            }
            if (backup == 1) {
                confirmClose++;
                JOptionPane.showMessageDialog(this, "Schedule created, backup volunteers needed. Please confirm they are called");
                confirmClose--;

                confirmClose++;
                JOptionPane.showMessageDialog(this, "Schedule created");
                confirmClose--;
            }
    
            else {
                confirmClose++;
                JOptionPane.showMessageDialog(this, "Schedule created");
                confirmClose--;
            }
        }
        catch (IllegalSchedule e) {
            //if illegal schedule
            confirmClose++;
            JOptionPane.showMessageDialog(this, "Not possible to create schedule");
            confirmClose--;
        }
        //SchedulePrint();

        if (confirmClose == 0) {
            System.exit(0);
        }
    }
    
    public void mouseClicked(MouseEvent event){    
    }
    
    public void mouseEntered(MouseEvent event){
    }

    public void mouseExited(MouseEvent event){
    }

    public void mousePressed(MouseEvent event){
    }

    public void mouseReleased(MouseEvent event){
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            new ScheduleGUI().setVisible(true);        
        });
    }
}