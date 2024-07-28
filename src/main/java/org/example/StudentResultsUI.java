package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class StudentResultsUI extends JFrame {

    public StudentResultsUI(List<Student> students) {
        setTitle("Student Records");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        String[] columnNames = {"ID", "Name", "Email", "Age", "Gender", "Remarks"};
        String[][] data = new String[students.size()][6];
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            data[i][0] = String.valueOf(student.getId());
            data[i][1] = student.getName();
            data[i][2] = student.getEmail();
            data[i][3] = String.valueOf(student.getAge());
            data[i][4] = student.getGender();
            data[i][5] = student.getRemarks();
        }

        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }
}
