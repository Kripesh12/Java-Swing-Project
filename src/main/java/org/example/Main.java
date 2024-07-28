package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Student Management");
        frame.setSize(400, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null); // No layout manager

        // ID Label and TextField
        JLabel idLabel = new JLabel("ID:");
        idLabel.setBounds(10, 10, 100, 25);
        JTextField idField = new JTextField();
        idField.setBounds(120, 10, 250, 25);

        // Name Label and TextField
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(10, 45, 100, 25);
        JTextField nameField = new JTextField();
        nameField.setBounds(120, 45, 250, 25);

        // Email Label and TextField
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(10, 80, 100, 25);
        JTextField emailField = new JTextField();
        emailField.setBounds(120, 80, 250, 25);

        // Age Label and TextField
        JLabel ageLabel = new JLabel("Age:");
        ageLabel.setBounds(10, 115, 100, 25);
        JTextField ageField = new JTextField();
        ageField.setBounds(120, 115, 250, 25);

        // Gender Label and RadioButtons
        JLabel genderLabel = new JLabel("Gender:");
        genderLabel.setBounds(10, 150, 100, 25);
        JRadioButton maleRadio = new JRadioButton("Male");
        maleRadio.setBounds(120, 150, 80, 25);
        JRadioButton femaleRadio = new JRadioButton("Female");
        femaleRadio.setBounds(200, 150, 80, 25);
        JRadioButton otherRadio = new JRadioButton("Other");
        otherRadio.setBounds(280, 150, 80, 25);

        // Group the radio buttons for gender
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleRadio);
        genderGroup.add(femaleRadio);
        genderGroup.add(otherRadio);

        // Remarks Label and TextArea
        JLabel remarksLabel = new JLabel("Remarks:");
        remarksLabel.setBounds(10, 185, 100, 25);
        JTextArea remarksTextArea = new JTextArea();
        remarksTextArea.setBounds(120, 185, 250, 100);

        // Add Button
        JButton addButton = new JButton("Add");
        addButton.setBounds(10, 300, 100, 30);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String name = nameField.getText();
                    String email = emailField.getText();
                    int age = Integer.parseInt(ageField.getText());
                    String gender = maleRadio.isSelected() ? "Male" :
                            femaleRadio.isSelected() ? "Female" : "Other";
                    String remarks = remarksTextArea.getText();

                    Student student = new Student(0, name, email, age, gender, remarks);
                    DatabaseConnection.insertStudent(student);
                    JOptionPane.showMessageDialog(frame, "Student added successfully!");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(frame, "Error adding student: " + ex.getMessage());
                }
            }
        });

        // Update Button
        JButton updateButton = new JButton("Update");
        updateButton.setBounds(120, 300, 100, 30);
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(idField.getText());
                    String name = nameField.getText();
                    String email = emailField.getText();
                    int age = Integer.parseInt(ageField.getText());
                    String gender = maleRadio.isSelected() ? "Male" :
                            femaleRadio.isSelected() ? "Female" : "Other";
                    String remarks = remarksTextArea.getText();

                    Student student = new Student(id, name, email, age, gender, remarks);
                    DatabaseConnection.updateStudent(student);
                    JOptionPane.showMessageDialog(frame, "Student updated successfully!");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(frame, "Error updating student: " + ex.getMessage());
                }
            }
        });

        // Delete Button
        JButton deleteButton = new JButton("Delete");
        deleteButton.setBounds(230, 300, 100, 30);
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(idField.getText());
                    DatabaseConnection.deleteStudent(id);
                    JOptionPane.showMessageDialog(frame, "Student deleted successfully!");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(frame, "Error deleting student: " + ex.getMessage());
                }
            }
        });

        JButton viewButton = new JButton("View All Students");
        viewButton.setBounds(10, 350, 150, 30);
        frame.add(viewButton);

        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    List<Student> students = DatabaseConnection.getAllStudents();
                    new StudentResultsUI(students);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(frame, "Error retrieving student records: " + ex.getMessage());
                }
            }
        });

        // Add components to frame
        frame.add(idLabel);
        frame.add(idField);
        frame.add(nameLabel);
        frame.add(nameField);
        frame.add(emailLabel);
        frame.add(emailField);
        frame.add(ageLabel);
        frame.add(ageField);
        frame.add(genderLabel);
        frame.add(maleRadio);
        frame.add(femaleRadio);
        frame.add(otherRadio);
        frame.add(remarksLabel);
        frame.add(remarksTextArea);
        frame.add(addButton);
        frame.add(updateButton);
        frame.add(deleteButton);

        // Set frame visibility
        frame.setVisible(true);
    }
}
