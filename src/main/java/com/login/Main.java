package com.login;

import com.entity.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by Aymen Ben Othmen on 16/07/16.
 */

public class Main extends Application {

    //Hibernate SessionFactory
    private static final SessionFactory sessionFactory = new Configuration()
            .configure("/hibernate.cfg.xml").buildSessionFactory();

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    public void start(Stage stage) throws Exception {

        Session session = sessionFactory.getCurrentSession();

        User user = new User("username", "email", "firstname", "lastname");
        user.setGender("female");
        user.setPassword("user-pass");

        try {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        }catch(Exception ex) {

        }finally {
            session.close();
        }

//        Parent rootNode = FXMLLoader.load(getClass().getResource("/login/login.fxml"));
        Parent rootNode = FXMLLoader.load(getClass().getResource("/home/home.fxml"));
        Scene scene = new Scene(rootNode, 600, 400);
        scene.getStylesheets().add("/styles/toDoApp.css");

        stage.setTitle("ToDo App Homepage");
        //stage.setResizable(false);
        //stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }


}
