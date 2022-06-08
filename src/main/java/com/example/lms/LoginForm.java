package com.example.lms;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginForm {


    @FXML
    private TextField email;
    @FXML
    private Stage stage;
    private Scene scene;
    @FXML
    private Button login;

    @FXML
    private PasswordField password;

    @FXML
    private Label wrongInput;

    @FXML
    void userLogin(ActionEvent event) {
        if(!email.getText().isBlank() && !password.getText().isBlank())
            validateLogin(event);
        else {
            wrongInput.setTextFill(Color.RED);
            wrongInput.setText("Please enter email and password");
        }
    }



    public void validateLogin(ActionEvent event) {
        DatabaseConnection connect = new DatabaseConnection();
        Connection connectDB = connect.getConnection();

        String verifyLogin = "SELECT count(1),type FROM account WHERE username = '" + email.getText() + "' AND password = '" + password.getText() + "'";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while(queryResult.next()) {
                if(queryResult.getInt(1) == 1) {
                    if(queryResult.getString("type").equals("lecturer")) {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource(("StudentDashboard.fxml")));
                        Parent root = loader.load();
                        StudentDashboard controller = loader.getController();
                        ResultSet in = connectDB.createStatement().executeQuery("select * from account where username = '" + email.getText() + "' AND password = '" + password.getText() + "'");
                        in.next();
                        controller.init(in.getString(1), in.getString(3));
                        stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
                        scene = new Scene(root);
                        stage.setTitle("Dashboard");
                        stage.setScene(scene);
                        stage.show();
                    }
                    else {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("ChooseCoursesController.fxml"));
                        Parent root = loader.load();
                        ChooseCoursesController controller = loader.getController();
                        ResultSet in = connectDB.createStatement().executeQuery("select * from account where username = '" + email.getText() + "' AND password = '" + password.getText() + "'");
                        in.next();
                        controller.init(in.getString(1), in.getString(3));
                        stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
                        scene = new Scene(root);
                        stage.setTitle("Courses");
                        stage.setScene(scene);
                        stage.show();

                    }
                }

                else {
                    wrongInput.setTextFill(Color.RED);
                    wrongInput.setText("Invalid email or password");
                }
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Failed to load scene");
            alert.showAndWait();
        }
    }

}
