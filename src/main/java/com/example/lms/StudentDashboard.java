package com.example.lms;

import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class StudentDashboard {

    @FXML
    private ImageView assignment;

    @FXML
    private ImageView lecture;

    @FXML
    private ImageView quiz;

    public void clickLectures(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(("LecturesController.fxml")));
            Parent root = loader.load();
            LecturesController controller = loader.getController();
            Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
            Scene scene = new Scene(root);
            stage.setTitle("Lectures");
            stage.setScene(scene);
            stage.show();
        }

        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clickAssignments(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(("AssignmentsController.fxml")));
            Parent root = loader.load();
            AssignmentsController controller = loader.getController();
            Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
            Scene scene = new Scene(root);
            stage.setTitle("Assignments");
            stage.setScene(scene);
            stage.show();
        }

        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clickQuizzes(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(("QuizzesController.fxml")));
            Parent root = loader.load();
            QuizzesController controller = loader.getController();
            Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
            Scene scene = new Scene(root);
            stage.setTitle("Quizzes");
            stage.setScene(scene);
            stage.show();
        }

        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
