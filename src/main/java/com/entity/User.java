package com.entity;

import org.mindrot.jbcrypt.BCrypt;
import javax.persistence.*;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by Aymen Ben Othmen on 16/07/16.
 */

@Entity
@Table(name = "user")
public class User { // extends ConnectDB implements IEntity

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "username", nullable = false, length = 15, unique = true)
    @Length(min = 3, max = 15, message = "username: length must be between 2 and 15")
    private String username;

    @Column(name = "email", nullable = false, length = 50, unique = true)
    @Email(message = "email: not a well-formed email address")
    private String email;

    @Column(name = "firstname", nullable = false, length = 31)
    @Length(min = 2, max = 15, message = "firstname: length must be between 2 and 15")
    public String firstname;

    @Column(name = "lastname", nullable = false, length = 31)
    @Length(min = 2, max = 15, message = "lastname: length must be between 2 and 15")
    private String lastname;

    @Column(name = "gender", nullable = false, length = 6)
    @NotBlank(message = "gender: this property must not be blank")
    private String gender;

    @Column(name = "password", nullable = false, length = 24)
    @NotBlank(message = "password: this property must not be blank")
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

    public String getFullname() {
        return String.format("%s %s", firstname, lastname);
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

    public void setGender(String gender) {
        if(gender == "male" || gender == "female") {
            this.gender = gender;
        }else{
            this.gender = "unset";
        }
    }
}
