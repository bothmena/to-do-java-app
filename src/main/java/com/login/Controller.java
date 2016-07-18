package com.login;

import com.entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.NoResultException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.util.Set;

/**
 * Created by Aymen Ben Othmen on 16/07/16.
 */

public class Controller {

    SessionFactory sessionFactory = Main.getSessionFactory();

    @FXML private TextField usernameLg;
    @FXML private TextField passwordLg;
    @FXML private RadioButton rememberMeLg;
    @FXML private Label errorLg;

    @FXML private TextField usernameSg;
    @FXML private TextField passwordSg;
    @FXML private TextField emailSg;
    @FXML private Label errorSg;

    @FXML
    private void onLogin(ActionEvent event) {

        errorLg.setText(" ");
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        User user;

        try {
            user = (User) session
                .createQuery("from com.entity.User u where u.username = '" + usernameLg.getText() + "'")
                .getSingleResult();

        } catch (NoResultException ex) {
            user = null;
            System.out.printf("Wooow, I just catched an exception: %s", ex);
        }finally {
            session.close();
        }

        if ( user != null && user.checkPassword( passwordLg.getText() ) ) {
            System.out.println("Welcome " + user.getFullname() );
            goToHomepage(event);
        }else {
            errorLg.setText("Invalid credentials. Please try again.");
            usernameLg.clear();
            passwordLg.clear();
        }

    }

    @FXML
    private void onSignup( ActionEvent event ) {

        errorSg.setText(" ");
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        User user = new User( usernameSg.getText(), emailSg.getText(), "unset", "unset" );
        user.setPassword( passwordSg.getText() );
        user.setGender("unset");

        try {
            session.save(user);
            session.getTransaction().commit();
            goToHomepage(event);
        } catch (ConstraintViolationException ex) {
            System.out.println("Submitted values were rejected, please correct the following error(s):");
            Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
            int i = 0;
            for (ConstraintViolation constr : violations) {
                if(i++ == 0) errorSg.setText( constr.getMessage() );
                System.out.println( constr.getMessage() );
            }
        }finally {
            session.close();
        }
    }

    private void goToHomepage (ActionEvent event) {

        try {
            Parent home = FXMLLoader.load(getClass().getResource("/home/home.fxml"));
            Scene homeScene = new Scene(home);
            Stage stage = (Stage) ( (Node) event.getSource() ) . getScene().getWindow();
            stage.hide();
            stage.setScene(homeScene);
            stage.show();

        }catch( IOException ex ) {
            ex.printStackTrace();
        }

    }
}
