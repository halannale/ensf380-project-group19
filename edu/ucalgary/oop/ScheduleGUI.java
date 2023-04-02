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
        //call schedule
        //if illegal schedule
        confirmClose++;
        JOptionPane.showMessageDialog(this, "Not possible to create schedule");
        confirmClose--;

        //if schedule is created, backup volunteers needed
        confirmClose++;
        JOptionPane.showMessageDialog(this, "Schedule created, backup volunteers needed.");
        confirmClose--;

        //if schedule is created with no backup volunteers needed
        confirmClose++;
        JOptionPane.showMessageDialog(this, "Schedule created");
        confirmClose--;

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