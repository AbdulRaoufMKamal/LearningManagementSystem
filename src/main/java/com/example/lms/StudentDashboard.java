package com.example.lms;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class StudentDashboard{

    @FXML
    private ImageView assignment;

    @FXML
    private ImageView lecture;

    @FXML
    private ImageView adminimg;

    @FXML
    private ImageView quiz;

    @FXML
    private Button back_btn;

    public String email;

    public String access_lvl;

    public void clickLectures(MouseEvent event) {
        try {
            FXMLLoader loader;
            if(this.access_lvl.equals("lecturer")){ loader = new FXMLLoader(getClass().getResource(("LecturesControllerAdmin.fxml"))); }
            else { loader = new FXMLLoader(getClass().getResource(("LecturesController.fxml"))); }
            Parent root = loader.load();
            LecturesController controller = loader.getController();
            controller.init(email, access_lvl);
            Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
            Scene scene = new Scene(root);
            stage.setTitle("Lectures");
            stage.setScene(scene);
            stage.show();
        }

        catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Failed to upload scene");
            alert.showAndWait();
        }
    }

    public void clickAssignments(MouseEvent event) {
        try {
            FXMLLoader loader;
            if(this.access_lvl.equals("lecturer")){ loader = new FXMLLoader(getClass().getResource(("AssignmentsControllerAdmin.fxml"))); }
            else { loader = new FXMLLoader(getClass().getResource(("AssignmentsController.fxml"))); }
            Parent root = loader.load();
            AssignmentsController controller = loader.getController();
            controller.init(email, access_lvl);
            Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
            Scene scene = new Scene(root);
            stage.setTitle("Assignments");
            stage.setScene(scene);
            stage.show();
        }

        catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Failed to upload scene");
            alert.showAndWait();
        }
    }

    public void clickQuizzes(MouseEvent event) {
        try {
            FXMLLoader loader;
            if(this.access_lvl.equals("lecturer")){ loader = new FXMLLoader(getClass().getResource(("QuizzesControllerAdmin.fxml"))); }
            else { loader = new FXMLLoader(getClass().getResource(("QuizzesController.fxml"))); }
            Parent root = loader.load();
            QuizzesController controller = loader.getController();
            controller.init(email, access_lvl);
            Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
            Scene scene = new Scene(root);
            stage.setTitle("Quizzes");
            stage.setScene(scene);
            stage.show();
        }

        catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Failed to upload scene");
            alert.showAndWait();
        }
    }

    public void back_click(ActionEvent event){
        try {
            if(access_lvl.equals("lecturer")) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(("LoginForm.fxml")));
                Parent root = loader.load();
                LoginForm controller = loader.getController();
                Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
                Scene scene = new Scene(root);
                stage.setTitle("Learning Management System");
                stage.setScene(scene);
                stage.show();
            }
            else {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(("ChooseCoursesController.fxml")));
                Parent root = loader.load();
                ChooseCoursesController controller = loader.getController();
                controller.init(email,access_lvl);
                Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
                Scene scene = new Scene(root);
                stage.setTitle("Learning Management System");
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

    public void init(String email, String access_lvl){
        this.access_lvl = access_lvl;
        this.email = email;
        if(this.access_lvl.equals("lecturer")){ adminimg.setVisible(true);}
        else {adminimg.setVisible(false);}
        if(access_lvl.equals("lecturer"))
            back_btn.setText("SIGN OUT");
    }

}
