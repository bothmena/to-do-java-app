package com.entity;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by Aymen Ben Othmen on 17/07/16.
 */

@Entity
@Table(name = "user")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "title", nullable = false, length = 35, unique = true)
    @Length(min = 3, max = 35, message = "title: length must be between 3 and 35")
    private String title;

    @Column(name = "location", nullable = false, length = 35, unique = true)
    @Length(min = 3, max = 35, message = "location: length must be between 3 and 35")
    private String location;

    @Column(name = "notes", nullable = false)
    @NotBlank(message = "notes: this property must not be blank")
    private String notes;

    @Column(name = "date", nullable = false)
    @NotNull(message = "notes: this property must not be null")
    private Date date;

    @Column(name = "stat", nullable = false)
    private boolean completed;

    public Task(int id, String title, String location, String notes, Date date) {
        this.id = id;
        this.title = title;
        this.location = location;
        this.notes = notes;
        this.date = date;
        this.completed = false;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
