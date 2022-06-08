package com.example.lms;

import javafx.scene.control.Alert;

import java.sql.Connection;
import  java.sql.DriverManager;

public class DatabaseConnection {
    public Connection databaseLink;
    public Connection getConnection() {
        String databaseName = "lms";
        String databaseUser = "root";
        String databasePassword = "root";
        String url = "jdbc:mysql://localhost/" +databaseName;

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
