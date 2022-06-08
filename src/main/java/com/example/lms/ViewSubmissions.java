package com.example.lms;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewSubmissions {

    @FXML
    private TableView<TableItemCustom> sub_table;

    @FXML
    private TableColumn<TableItemCustom,Integer> sub_id_col;;

    @FXML
    private TableColumn<TableItemCustom, String> file_name_col;

    @FXML
    private TableColumn<TableItemCustom, String> file_status_col;

    private String email, access_lvl, file_type;

    public void refresh_table(){
        sub_table.getItems().clear();
        DatabaseConnection connect = new DatabaseConnection();
        Connection connectDB = connect.getConnection();
        try {
            ResultSet rs;
                rs = connectDB.createStatement().executeQuery("select submission_id, file_name,file_status from file where username = '"+email+"' and course_name = \""+ChooseCoursesController.courseName+"\" and file_type ='"+this.file_type+"'");
                while (rs.next()){
                    TableItemCustom tableItemCustom = new TableItemCustom();
                    tableItemCustom.setSub_id(Integer.parseInt(rs.getString("submission_id")));
                    tableItemCustom.setFile_name(rs.getString("file_name"));
                    tableItemCustom.setFile_status(rs.getString("file_status"));
                    sub_table.getItems().add(tableItemCustom);
                }

        }
        catch (SQLException s){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Failed to refresh table");
            alert.showAndWait();
        }
    }

    public void init(String email, String access_lvl, String file_type){
        this.email = email;
        this.access_lvl = access_lvl;
        this.file_type = file_type;
        sub_id_col.setCellValueFactory(new PropertyValueFactory<TableItemCustom, Integer>("sub_id"));
        file_name_col.setCellValueFactory(new PropertyValueFactory<TableItemCustom, String>("file_name"));
        file_status_col.setCellValueFactory(new PropertyValueFactory<TableItemCustom,String>("file_status"));
        refresh_table();
    }

    public void back_click(ActionEvent event){
        try {
            if(file_type.equals("submit_assignment")) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(("AssignmentsController.fxml")));
                Parent root = loader.load();
                AssignmentsController controller = loader.getController();
                controller.init(email, access_lvl);
                Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
                Scene scene = new Scene(root);
                stage.setTitle("Assignments");
                stage.setScene(scene);
                stage.show();
            }
            else {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(("QuizzesController.fxml")));
                Parent root = loader.load();
                QuizzesController controller = loader.getController();
                controller.init(email, access_lvl);
                Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
                Scene scene = new Scene(root);
                stage.setTitle("Quizzes");
                stage.setScene(scene);
                stage.show();
            }
        }

        catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Failed to upload scene");
            alert.showAndWait();
        }
    }

    public void selectFile(ActionEvent event) throws IOException, SQLException {
        try {
            TableItemCustom tableItemCustom = sub_table.getSelectionModel().getSelectedItem();
            FXMLLoader loader = new FXMLLoader(getClass().getResource(("Feedback.fxml")));
            Parent root = loader.load();
            Feedback controller = loader.getController();
            controller.init(email, access_lvl, tableItemCustom.getFile_name(), file_type, tableItemCustom.getSub_id());
            Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
            Scene scene = new Scene(root);
            stage.setTitle("Feedback");
            stage.setScene(scene);
            stage.show();
        }
        catch(Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("You must select a file");
            alert.showAndWait();
        }
    }

}
