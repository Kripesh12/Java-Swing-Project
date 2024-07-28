package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/test";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static List<Student> getAllStudents() throws SQLException {
        String sql = "SELECT * FROM students";
        List<Student> students = new ArrayList<>();
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                int age = rs.getInt("age");
                String gender = rs.getString("gender");
                String remarks = rs.getString("remarks");
                students.add(new Student(id, name, email, age, gender, remarks));
            }
        }
        return students;
    }

    public static void insertStudent(Student student) throws SQLException {
        String sql = "INSERT INTO students (name, email, age, gender, remarks) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, student.getName());
            pstmt.setString(2, student.getEmail());
            pstmt.setInt(3, student.getAge());
            pstmt.setString(4, student.getGender());
            pstmt.setString(5, student.getRemarks());
            pstmt.executeUpdate();
        }
    }

    public static void updateStudent(Student student) throws SQLException {
        String sql = "UPDATE students SET name=?, email=?, age=?, gender=?, remarks=? WHERE id=?";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, student.getName());
            pstmt.setString(2, student.getEmail());
            pstmt.setInt(3, student.getAge());
            pstmt.setString(4, student.getGender());
            pstmt.setString(5, student.getRemarks());
            pstmt.setInt(6, student.getId());
            pstmt.executeUpdate();
        }
    }

    public static void deleteStudent(int studentId) throws SQLException {
        String sql = "DELETE FROM students WHERE id=?";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, studentId);
            pstmt.executeUpdate();
        }
    }
}
