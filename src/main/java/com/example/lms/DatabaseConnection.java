 package com.example.lms;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import  java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class DatabaseConnection {
    public Connection databaseLink;

    public Connection getConnection() {
        String databaseName = "LMS";
        String databaseUser = "root";
        String databasePassword = "root";
        String url = "jdbc:mysql://localhost/" + databaseName;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url,databaseUser,databasePassword);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return databaseLink;
    }

    public ObservableList<Assignments> getDataAssignments() {
        Connection connection = getConnection();
        ObservableList<Assignments> list = FXCollections.observableArrayList();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Assignment");
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                list.add(new Assignments(resultSet.getString("courseName"),Integer.parseInt(resultSet.getString("assignmentNumber"))));
            }
        }

        catch(Exception e) {

        }

        return list;
    }
}
