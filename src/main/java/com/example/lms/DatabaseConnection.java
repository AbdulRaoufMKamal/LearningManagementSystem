package com.example.lms;

import javafx.scene.control.Alert;

import java.sql.Connection;
import  java.sql.DriverManager;

public class DatabaseConnection {
    public Connection databaseLink;
    public Connection getConnection() {
        String databaseName = "sql6499152";
        String databaseUser = "sql6499152";
        String databasePassword = "dnX5qAfvxY";
        String url = "jdbc:mysql://sql6.freesqldatabase.com/" +databaseName;
        // Comment lines 11,12,13,14
        // Uncomment lines 18,19,20,21
        // We recommend you to use local database rather than the online one for better performance
        //String databaseName = "Enter your local database name here"
        //String databaseUser = "Enter your username fo MySQL here"
        //String databasePassword = "Enter your password for MySQL here"
        //String url = "jdbc:mysql://localhost/" +databaseName;


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url,databaseUser,databasePassword);

        }
        catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Failed to connect to database");
            alert.showAndWait();

        }

        return databaseLink;
    }
}
