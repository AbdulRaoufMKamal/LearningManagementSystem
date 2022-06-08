package com.example.lms;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ChooseCoursesController implements Initializable {

    public static String courseName;
    private String email, access_lvl;

    @FXML
    private TableView<TableItemCustom> courseTable;

    @FXML
    private TableColumn<TableItemCustom, String> course_name;

    @FXML
    void back_click(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(("LoginForm.fxml")));
            Parent root = loader.load();
            LoginForm controller = loader.getController();
            Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
            Scene scene = new Scene(root);
            stage.setTitle("Login");
            stage.setScene(scene);
            stage.show();
        }

        catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Failed to load scene");
            alert.showAndWait();
        }
    }

    @FXML
    void selectCourse(ActionEvent event) throws IOException {
        try {
            TableItemCustom tableItemCustom = courseTable.getSelectionModel().getSelectedItem();
            courseName = tableItemCustom.getCourse_name();
            System.out.println("Course Name: " + courseName);
            FXMLLoader loader = new FXMLLoader(getClass().getResource(("StudentDashboard.fxml")));
            Parent root = loader.load();
            StudentDashboard controller = loader.getController();
            controller.init(email, access_lvl);
            Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
            Scene scene = new Scene(root);
            stage.setTitle("Dashboard");
            stage.setScene(scene);
            stage.show();
        }
        catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("You must select a course");
            alert.showAndWait();

        }


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        course_name.setCellValueFactory(new PropertyValueFactory<TableItemCustom,String>("course_name"));

    }


    public void refresh_table(){
        courseTable.getItems().clear();
        DatabaseConnection connect = new DatabaseConnection();
        Connection connectDB = connect.getConnection();
        try {
            ResultSet rs;
            rs = connectDB.createStatement().executeQuery("select course_name from taken_courses where username = '" + email +"' ");
            while (rs.next()){
                TableItemCustom tableItemCustom = new TableItemCustom();
                tableItemCustom.setCourse_name(rs.getString("course_name"));
                courseTable.getItems().add(tableItemCustom);
            }
        }
        catch (SQLException s){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Failed to refresh table");
            alert.showAndWait();
        }
    }

    public void init(String email, String access_lvl){
        this.email = email;
        this.access_lvl = access_lvl;
        refresh_table();
    }
}

