package com.example.lms;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.sql.*;

public class Feedback {

    @FXML
    private Label charactersCount;

    @FXML
    private TextArea feedback;

    @FXML
    private Button submitBtn;

    private String email, access_lvl;
    private String file_name, file_type;
    private int id;


    private int maxChar = 100;

    public void init(String email, String access_lvl, String file_name, String file_type, int id) throws SQLException {

        this.email = email;
        this.access_lvl = access_lvl;
        this.file_name = file_name;
        this.file_type = file_type;
        this.id = id;
        System.out.println(id);
        if(this.access_lvl.equals("student")) {
            submitBtn.setDisable(false);
            submitBtn.setVisible(false);
            charactersCount.setVisible(false);
            feedback.setEditable(false);
            feedback.setPromptText(null);
            DatabaseConnection connect = new DatabaseConnection();
            Connection connectDB = connect.getConnection();
            Statement statement = connectDB.createStatement();
            ResultSet rs = statement.executeQuery("select feedback from file where submission_id = '" + id + "'");
            while(rs.next())
                feedback.setText(rs.getString("feedback"));
        }

        else {
            charactersCount.setText("Remaining Characters: " + maxChar);
        }

        feedback.setWrapText(true);
        feedback.setTextFormatter(new TextFormatter<String>(change ->  {
            change.setAnchor(change.getCaretPosition());
            return change ;
        }));

    }



    public void submitFeedback(ActionEvent event) throws SQLException {
        String fb = feedback.getText();
        if(fb.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("You have not entered any text");
            alert.showAndWait();
            return;
        }
        DatabaseConnection connect = new DatabaseConnection();
        Connection connectDB = connect.getConnection();
        PreparedStatement pst1 = connect.databaseLink.prepareStatement("update file set feedback = ? where submission_id = '"+this.id+"'");
        PreparedStatement pst2 = connect.databaseLink.prepareStatement("update file set file_status = \"Graded\" where submission_id = '"+this.id+"'");
        pst1.setString(1,fb);
        pst1.execute();
        pst2.execute();
    }

    public void counterLimit(KeyEvent keyEvent) {
        char ch ;
        String specialChars = "/*!@#$%^&*()\"{}_[]|\\?/<>,.-";

        boolean isUpperCaseFlag = false;
        boolean isLowerCaseFlag = false;
        boolean isNumberFlag = false;
        boolean isSpecialFlag = false;
        ch = (char)keyEvent.getCharacter().charAt(0);

        if(Character.isUpperCase(ch))
            isUpperCaseFlag = true;
        if(Character.isLowerCase(ch))
            isLowerCaseFlag = true;
        if(Character.isDigit(ch))
            isNumberFlag = true;

        for(int i = 0; i < specialChars.length(); i++) {
            if (ch == specialChars.charAt(i)) {
                isSpecialFlag = true;
                break;
            }
        }

        if(this.maxChar == 0) {
            feedback.setEditable(false);
            if((int)ch == 8) {
                feedback.setEditable(true);
                maxChar++;
                charactersCount.setText("Remaining Characters: " + maxChar);
            }
        }

        else if(this.maxChar > 0 && (isLowerCaseFlag || isUpperCaseFlag || isNumberFlag || isSpecialFlag))  {
            maxChar--;
            charactersCount.setText("Remaining Characters: " + maxChar);
        }
        else if((int)ch == 8 && maxChar < 100) {
            maxChar++;
            charactersCount.setText("Remaining Characters: " + maxChar);
        }




    }

    public void back_click(ActionEvent event){
        try {
            if(access_lvl.equals("lecturer") && file_type.equals("submit_assignment")) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(("AssignmentsControllerAdmin.fxml")));
                Parent root = loader.load();
                AssignmentsController controller = loader.getController();
                controller.init(email, access_lvl);
                Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
                Scene scene = new Scene(root);
                stage.setTitle("Assignments");
                stage.setScene(scene);
                stage.show();
            }
            else if(access_lvl.equals("lecturer") && file_type.equals("submit_quiz")) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(("QuizzesControllerAdmin.fxml")));
                Parent root = loader.load();
                QuizzesController controller = loader.getController();
                controller.init(email, access_lvl);
                Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
                Scene scene = new Scene(root);
                stage.setTitle("Quizzes");
                stage.setScene(scene);
                stage.show();
            }

            else {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(("ViewSubmissions.fxml")));
                Parent root = loader.load();
                ViewSubmissions controller = loader.getController();
                controller.init(email, access_lvl,file_type);
                Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
                Scene scene = new Scene(root);
                stage.setTitle("Assignments");
                stage.setScene(scene);
                stage.show();
            }

        }

        catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Failed to load scene");
            alert.showAndWait();
        }
    }

}
