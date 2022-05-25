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
        String databaseName = "sql4495241";
        String databaseUser = "sql4495241";
        String databasePassword = "iezAHSzfeZ";
        String url = "jdbc:mysql://sql4.freemysqlhosting.net:3306/sql4495241?" + "user=sql4495241&password=iezAHSzfeZ";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url);
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
