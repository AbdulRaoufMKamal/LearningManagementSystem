package com.example.lms;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;

public class QuizzesController {

    @FXML
    private TableView<TableItemCustom> quiz_table_user;

    @FXML
    private TableColumn<TableItemCustom, Integer> sub_id;

    @FXML
    private TableColumn<TableItemCustom, String> email_col;

    @FXML
    private TableColumn<TableItemCustom, String> file_name_col;

    @FXML
    private TableColumn<TableItemCustom, String> file_status;


    public String email;

    private String access_lvl;

    private boolean noFileSelected = true;

    public void refresh_table(){
        quiz_table_user.getItems().clear();
        DatabaseConnection connect = new DatabaseConnection();
        Connection connectDB = connect.getConnection();
        try {
            ResultSet rs;
            if(access_lvl.equals("lecturer")){
                rs = connectDB.createStatement().executeQuery("select submission_id, username, file_name, file_status from file where file.lecturer = \""+email+"\" and file_type =\"submit_quiz\"");
                while (rs.next()){
                    TableItemCustom tableItemCustom = new TableItemCustom();
                    tableItemCustom.setSub_id(Integer.parseInt(rs.getString("Submission_id")));
                    tableItemCustom.setUser_email(rs.getString("Username"));
                    tableItemCustom.setFile_name(rs.getString("file_name"));
                    tableItemCustom.setFile_status(rs.getString("file_status"));
                    quiz_table_user.getItems().add(tableItemCustom);
                }
            }
            else {
                rs = connectDB.createStatement().executeQuery("select file_name from file where course_name = \""+ChooseCoursesController.courseName+"\" and file_type =\"quiz\"");
                while (rs.next()){
                    TableItemCustom tableItemCustom = new TableItemCustom();
                    tableItemCustom.setFile_name(rs.getString("file_name"));
                    quiz_table_user.getItems().add(tableItemCustom);
                }
            }

        }
        catch (SQLException s){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Failed to refresh table");
            alert.showAndWait();
        }
    }

    public byte[] readFile(File file) throws IOException {
        Path filePath = Paths.get(file.getAbsolutePath());
        byte[] arr = Files.readAllBytes(filePath);
        return arr;
    }

    public File writeFile(byte[] data, String fileName, File file) throws IOException {
        String path = file.getAbsolutePath() + "\\" + fileName;
        OutputStream out = new FileOutputStream(path);
        out.write(data);
        out.close();
        return file;
    }

    public void Download(ActionEvent event) throws SQLException, IOException {
        try {
            TableItemCustom tableItemCustom = quiz_table_user.getSelectionModel().getSelectedItem();
            byte[] arr = new byte[1028];
            DatabaseConnection connect = new DatabaseConnection();
            Connection connectDB = connect.getConnection();
            Statement statement = connectDB.createStatement();
            ResultSet rs = statement.executeQuery("select file.file_data from file where file_name = \"" + tableItemCustom.getFile_name() + "\"");
            noFileSelected = false;
            while (rs.next()) {
                arr = rs.getBytes("file_data");
            }
            DirectoryChooser dc = new DirectoryChooser();
            File file = dc.showDialog(null);
            writeFile(arr, tableItemCustom.getFile_name(), file);
        }
        catch(Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            if(noFileSelected) {
                alert.setContentText("You must select a file");
            }
            else {
                alert.setContentText("You must choose a directory to save your file");
            }
            alert.showAndWait();
        }
    }

    public void Submit(ActionEvent event) throws IOException, SQLException {

        try {
            DatabaseConnection connect = new DatabaseConnection();
            Connection connectDB = connect.getConnection();
            if(access_lvl.equals("lecturer")) {
                FileChooser fileChooser  = new FileChooser();
                File file = fileChooser.showOpenDialog(null);
                byte[] data = readFile(file);
                String course_name = null;
                PreparedStatement pst1 = connect.databaseLink.prepareStatement("select Course_name from course where course.lecturer = '"+email+"' limit 1");
                PreparedStatement pst2 = connect.databaseLink.prepareStatement("insert into file(file_name,file_data,file_type,course_name,lecturer) values (?,?,?,?,?)");
                pst1.execute();
                ResultSet rs = pst1.getResultSet();
                while (rs.next()) { course_name = rs.getString("course_name"); }
                pst2.setString(1,file.getName());
                pst2.setBytes(2,data);
                pst2.setString(3, "quiz");
                pst2.setString(4, course_name);
                pst2.setString(5, email);
                pst2.execute();
            }
            else {
                TableItemCustom tableItemCustom = quiz_table_user.getSelectionModel().getSelectedItem();
                FileChooser fileChooser  = new FileChooser();
                File file = fileChooser.showOpenDialog(null);
                byte[] data = readFile(file);
                String lecturer_name = null;
                PreparedStatement pst1 = connect.databaseLink.prepareStatement("select lecturer from course where course.course_name = '"+ChooseCoursesController.courseName+"' limit 1");
                PreparedStatement pst2 = connect.databaseLink.prepareStatement("insert into file(file_name,file_data,file_type,course_name,lecturer,username,file_status) values (?,?,?,?,?,?,?)");
                pst1.execute();
                ResultSet rs = pst1.getResultSet();
                while(rs.next()) { lecturer_name = rs.getString("lecturer"); }
                pst2.setString(1,file.getName());
                pst2.setBytes(2,data);
                pst2.setString(3, "submit_quiz");
                pst2.setString(4, ChooseCoursesController.courseName);
                pst2.setString(5, lecturer_name);
                pst2.setString(6, email);
                pst2.setString(7,"Not reviewed");
                pst2.execute();

            }

        }
        catch(Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("You must select a file to upload");
            alert.showAndWait();
        }
        refresh_table();
    }

    public void back_click(ActionEvent event){
        try {
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
            alert.setContentText("Failed to upload scene");
            alert.showAndWait();
        }
    }

    public void init(String email, String access_lvl){
        this.email = email;
        this.access_lvl = access_lvl;
        if(access_lvl.equals("lecturer")) {
            sub_id.setCellValueFactory(new PropertyValueFactory<TableItemCustom, Integer>("sub_id"));
            email_col.setCellValueFactory(new PropertyValueFactory<TableItemCustom, String>("user_email"));
            file_name_col.setCellValueFactory(new PropertyValueFactory<TableItemCustom, String>("file_name"));
            file_status.setCellValueFactory(new PropertyValueFactory<TableItemCustom,String>("file_status"));
        }
        else {
            file_name_col.setCellValueFactory(new PropertyValueFactory<TableItemCustom, String>("file_name"));
        }
        refresh_table();
    }

    public void feedback(ActionEvent event) {
        try {
            TableItemCustom tableItemCustom = quiz_table_user.getSelectionModel().getSelectedItem();
            String file_type = "submit_quiz";
            if(access_lvl.equals("student")) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(("ViewSubmissions.fxml")));
                Parent root = loader.load();
                ViewSubmissions controller = loader.getController();
                controller.init(email, access_lvl, file_type);
                Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
                Scene scene = new Scene(root);
                stage.setTitle("Submissions");
                stage.setScene(scene);
                stage.show();
            }
            else {
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
        }

        catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("You must select a file");
            alert.showAndWait();
        }
    }
}
