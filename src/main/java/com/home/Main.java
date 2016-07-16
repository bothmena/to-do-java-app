package com.home;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Created by Aymen Ben Othmen on 16/07/16.
 */

public class Main extends Application {

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    public void start(Stage stage) throws Exception {

        Parent rootNode = FXMLLoader.load(getClass().getResource("/home/home.fxml"));
        Scene scene = new Scene(rootNode, 600, 400);
        scene.getStylesheets().add("/styles/css/bootstrap.min.css");
        scene.getStylesheets().add("/styles/toDoApp.css");

        stage.setTitle("ToDo App Homepage");
        //stage.setResizable(false);
        //stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
    }

}
