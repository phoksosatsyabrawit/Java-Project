package com.java.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.java.model.Student;

public class StudentService {
    private Connection connection;

    public StudentService(Connection connection) {
        this.connection = connection;
    }

    private Student getResultSet(ResultSet resultSet) throws SQLException{
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String gender = resultSet.getString("gender");
        int grade = resultSet.getInt("grade");
        Student students = new Student(id, name, gender, grade);
        return students;
    }

    public Student selectStudentById(int idToFind) {
        String sql = "SELECT * FROM students WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idToFind);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return getResultSet(resultSet);
            }else{
                System.out.println("No student found with id: " + idToFind);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void insertStudent(Student student) {
        String sql = "INSERT INTO students (name, gender, grade) VALUES (?, ?, ?)";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getGender());
            preparedStatement.setInt(3, student.getGrade());
            preparedStatement.executeUpdate();
            System.out.println("Insert affected rows.");
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
