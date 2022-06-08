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
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ResourceBundle;

public class LecturesController implements Initializable {

    @FXML
    private TableView<TableItemCustom> lect_table;
    @FXML
    private TableColumn<TableItemCustom, String> file_name_col;

    public String email;
    private String access_lvl;

    private boolean noFileSelected = true;

    public void refresh_table(){
        lect_table.getItems().clear();
        DatabaseConnection connect = new DatabaseConnection();
        Connection connectDB = connect.getConnection();
        try {
            ResultSet rs;
            if(access_lvl.equals("lecturer")){
                rs = connectDB.createStatement().executeQuery("select file_name from file where lecturer = '" + email + "' and file_type =\"lecture\";");
            }
            else {
                rs = connectDB.createStatement().executeQuery("select file_name from file where course_name = '" + ChooseCoursesController.courseName +"' and file_type =\"lecture\";");
            }
            while (rs.next()){
                TableItemCustom tableItemCustom = new TableItemCustom();
                tableItemCustom.setFile_name(rs.getString("file_name"));
                lect_table.getItems().add(tableItemCustom);
            }
        }
        catch (SQLException s){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Failed to refresh table");
            alert.showAndWait();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        file_name_col.setCellValueFactory(new PropertyValueFactory<TableItemCustom, String>("file_name"));
    }

    public byte[] readFile(File file) throws IOException {
        Path filePath = Paths.get(file.getAbsolutePath());
        byte[] arr = Files.readAllBytes(filePath);
        return arr;
    }

    public void writeFile(byte[] data, String fileName) throws IOException {
        DirectoryChooser dc = new DirectoryChooser();
        File file = dc.showDialog(null);
        String path = file.getAbsolutePath() + "\\" + fileName;
        OutputStream out = new FileOutputStream(path);
        out.write(data);
        out.close();
    }

    public void Download(ActionEvent event) throws SQLException, IOException {
        try {
            TableItemCustom tableItemCustom = lect_table.getSelectionModel().getSelectedItem();
            byte[] arr = new byte[1028];
            DatabaseConnection connect = new DatabaseConnection();
            Connection connectDB = connect.getConnection();
            Statement statement = connectDB.createStatement();
            ResultSet rs = statement.executeQuery("select file.file_data from file where file_name = \"" + tableItemCustom.getFile_name() + "\"");
            noFileSelected = false;
            while (rs.next()) {
                arr = rs.getBytes("file_data");
            }
            writeFile(arr, tableItemCustom.getFile_name());
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
        FileChooser fileChooser  = new FileChooser();
        File file = fileChooser.showOpenDialog(null);
        String course_name = "";
        try {
            byte[] data = readFile(file);
            DatabaseConnection connect = new DatabaseConnection();
            Connection connectDB = connect.getConnection();
            PreparedStatement preparedStatement = connect.databaseLink.prepareStatement("select Course_name from course where course.lecturer = '"+email+"' limit 1");
            PreparedStatement preparedStatement4 = connect.databaseLink.prepareStatement("insert into file(file_name,file_data,file_type,Course_name,lecturer) values (?,?,?,?,?);");
            preparedStatement.execute();
            ResultSet rs = preparedStatement.getResultSet();
            while (rs.next()) { course_name += rs.getString("course_name"); }
            preparedStatement4.setString(1,file.getName());
            preparedStatement4.setBytes(2,data);
            preparedStatement4.setString(3, "lecture");
            preparedStatement4.setString(4, course_name);
            preparedStatement4.setString(5, email);
            preparedStatement4.execute();
            System.out.println("course_name:"+course_name);
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
            alert.setContentText("Failed to load scene");
            alert.showAndWait();
        }
    }

    public void init(String email, String access_lvl){
        this.email = email;
        this.access_lvl = access_lvl;
        refresh_table();
    }


}

