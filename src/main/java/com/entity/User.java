package com.entity;

import com.database.ConnectDB;
import org.mindrot.jbcrypt.BCrypt;

import javax.persistence.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Aymen Ben Othmen on 16/07/16.
 */

@Entity
@Table(name = "user")
public class User { // extends ConnectDB implements IEntity

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "id")
    private int id;
    @Column(name = "username", nullable = false, length = 15, unique = true)
    private String username;
    @Column(name = "email", nullable = false, length = 50, unique = true)
    private String email;
    @Column(name = "firstname", nullable = false, length = 31)
    public String firstname;
    @Column(name = "lastname", nullable = false, length = 31)
    private String lastname;
    @Column(name = "gender", nullable = false, length = 6)
    private String gender;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "salt", nullable = false)
    private String salt;

    public User() {
        this("", "", "", "");
    }

    public User(String username) {
        this(username, "", "", "");
    }

    public User(String username, String email) {
        this(username, email, "", "");
    }

    public User(String username, String email, String firstname, String lastname) {
        this.username = username;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        /*if ( this.username != "" || this.email != "" ) {
            fillClass();
        }*/
    }

    public boolean checkPassword(String password) {
        return BCrypt.checkpw(password, this.password);
    }

    public void setPassword(String password) {
        setSalt();
        this.password = BCrypt.hashpw(password, salt);
    }

    public String getSalt() {
        return salt;
    }

    private void setSalt() {
        this.salt = BCrypt.gensalt(12);
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getGender() {
        return gender == "male" ? "Male" : "Female";
    }

    public boolean setGender(String gender) {
        if(gender == "male" || gender == "female") {
            this.gender = gender;
            return true;
        }
        return false;
    }

    /*public boolean createTable() {

        try{
            String statement = "CREATE TABLE IF NOT EXISTS user ("+
                    "id INT NOT NULL AUTO_INCREMENT,"+
                    "username varchar(255) NOT NULL,"+
                    "email varchar(255) NOT NULL,"+
                    "firstname varchar(31) NOT NULL,"+
                    "lastname varchar(31) NOT NULL,"+
                    "gender varchar(6) DEFAULT NULL,"+
                    "salt varchar(255) NOT NULL,"+
                    "password varchar(255) NOT NULL,"+
                    "PRIMARY KEY (id) )";
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            preparedStatement.executeUpdate();
            return true;
        }catch (SQLException ex){
            System.out.printf("Error occurred when creating user table:%n%s", ex);
            return false;
        }finally {
            System.out.println("Operation completed.");
        }
    }

    public boolean update() {
        String statement = String.format(
                "UPDATE user SET username = '%s', email = '%s', firstname = '%s', lastname = '%s', gender = '%s', salt = "+
                        "'%s', password = '%s' WHERE id = %d", username, email, firstname, lastname, gender, salt, password, id
        );
        System.out.printf("Update Query: %s%n", statement);
        return flush(statement);
    }

    public boolean insert() {
        String statement = String.format(
                "INSERT INTO user (username, email, firstname, lastname, gender, salt, password)"+
                        " VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s')", username, email, firstname, lastname,
                gender, salt, password);
        System.out.printf("Query To Insert a New User:%n%s%n", statement);
        return flush(statement);
    }

    public boolean flush(String statement) {
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            preparedStatement.executeUpdate();
            return true;
        }catch (SQLException ex){
            return false;
        }
    }

    public void fillClass() {
        try {
            ResultSet user = statement.executeQuery(String.format(
                    "SELECT * FROM user WHERE username = '%s' OR email = '%s' LIMIT 1", username , email
            ));
            if ( user.next() ) {
                id = user.getInt("id");
                username = user.getString("username");
                firstname = user.getString("firstname");
                lastname = user.getString("lastname");
                email = user.getString("email");
                gender = user.getString("gender");
                password = user.getString("password");
                salt = user.getString("salt");
            }
        }catch (SQLException ex){
            System.out.printf("Error occurred when fetching data from com.database:%n%s", ex);
        }
    }

    public ResultSet executeQuery(String query) {
        try {
            ResultSet result = statement.executeQuery(query);
            return result;
        }catch (SQLException ex){
            System.out.printf("Error occurred when fetching data from com.database:%n%s", ex);
            return null;
        }
    }*/
}
