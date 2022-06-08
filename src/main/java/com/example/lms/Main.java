package com.example.lms;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;


public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("LoginForm.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Learning Management System");
        stage.setScene(scene);
        stage.getIcons().add(new Image(Main.class.getResource("LoginForm.fxml")+"..\\..\\..\\..\\..\\img\\lms.png"));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}