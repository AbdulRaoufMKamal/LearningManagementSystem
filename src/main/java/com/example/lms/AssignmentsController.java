package com.example.lms;

import javafx.collections.ObservableList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;


import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;

import java.util.ResourceBundle;

public class AssignmentsController implements Initializable {

    @FXML
    private TableView<Assignments> assignmentTable;

    @FXML
    private TableColumn<Assignments, String> courseCol;

    @FXML
    private TableColumn<Assignments, Integer> assignmentCol;

    @FXML
    private TableColumn<Assignments, ?> fileCol;

    @FXML
    private Button downloadBtn;

    @FXML
    private Button submitBtn;

    public ObservableList<Assignments> list;
    public int idx = -1;

    Connection connection = null;
    //ResultSet resultSet = null;
    //PreparedStatement preparedStatement = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        courseCol.setCellValueFactory(new PropertyValueFactory<Assignments, String>("courseName"));
        assignmentCol.setCellValueFactory(new PropertyValueFactory<Assignments, Integer>("assignmentNumber"));
        //fileCol.setCellValueFactory(new PropertyValueFactory<Assignments,?>("?"));
        DatabaseConnection databaseConnection = new DatabaseConnection();
        connection = databaseConnection.getConnection();
        assignmentTable.setItems(list);
    }

    public byte[] readFile(File file) throws IOException {
        Path filePath = Paths.get(file.getAbsolutePath());
        byte[] arr = Files.readAllBytes(filePath);
        return arr;
    }

    public void writeFile(byte[] data, File des, String fileName) throws IOException {
        DirectoryChooser dc = new DirectoryChooser();
        File file = dc.showDialog(null);
        String path = file.getAbsolutePath();
        System.out.println(path);
        OutputStream out = new FileOutputStream(path+"\\"+fileName);
        out.write(data);
        out.close();
    }

    public void Download(ActionEvent event) throws SQLException, IOException {
        byte[] arr = new byte[1028];
        String fileName = new String("");
        DatabaseConnection connect = new DatabaseConnection();
        Connection connectDB = connect.getConnection();
        Statement statement = connectDB.createStatement();
        ResultSet rs = statement.executeQuery("select * from file");
        while(rs.next()) {
            fileName = rs.getString("file_name");
            arr = rs.getBytes("file_data");
            System.out.println(fileName + " " + arr);
        }
        File file = new File("src/main/resources/Downloads");
        writeFile(arr,file, fileName);

    }

    public void Submit(ActionEvent event) throws IOException, SQLException {
        FileChooser fileChooser  = new FileChooser();
        File file = fileChooser.showOpenDialog(null);
        try {
            byte[] data = readFile(file);
            DatabaseConnection connect = new DatabaseConnection();
            Connection connectDB = connect.getConnection();
            PreparedStatement preparedStatement = connect.databaseLink.prepareStatement("insert into file(file_name,file_data) values (?, ?)");
            preparedStatement.setString(1,file.getName());
            preparedStatement.setBytes(2,data);
            preparedStatement.execute();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }


}