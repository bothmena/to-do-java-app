package com.home;

import com.entity.Task;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Aymen Ben Othmen on 17/07/16.
 */

public class Controller implements Initializable {

    List<Task> tasks;
    int selectedtaskId = 0;

    @FXML private Label taskTitle;
    @FXML private Label taskLocation;
    @FXML private Label taskNotes;
    @FXML private Label taskDate;
    @FXML private Label taskCompleted;

    @FXML private TextField taskTitleF;
    @FXML private TextField taskLocationF;
    @FXML private TextArea taskNotesF;
    @FXML private DatePicker taskDateF;
    @FXML private ToggleButton taskCompletedF;

    @FXML private VBox tasksContainer;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tasks = getTasks();
        taskList();
        selectTask( selectedtaskId );
        taskNotesF.setWrapText(true);
    }

    private List<Task> getTasks() {

        tasks.add(new Task(0, "Task 1", "Nabeul Tunisia", "notes: this property must not be null, location: length must be between 3 and 35", new Date(2016, 6, 15)));
        tasks.add(new Task(1, "Task 2", "Tunis Tunisia", "location: length must be between 3 and 35, location: length must be between 3 and 35", new Date(2017, 11, 2)));
        tasks.add(new Task(2, "Task 3", "LMMM Nabeul Tunisia", "notes: this property must not be null, location: length must be between 3 and 35", new Date(2016, 12, 7)));
        tasks.add(new Task(3, "Task 4", "Gabes Tunisia", "location: length must be between 3 and 35, location: length must be between 3 and 35", new Date(2017, 8, 26)));
        tasks.add(new Task(4, "Task 5", "Nabeul Tunisia", "notes: this property must not be null, location: length must be between 3 and 35", new Date(2016, 6, 15)));
        tasks.add(new Task(5, "Task 6", "Tunis Tunisia", "location: length must be between 3 and 35, location: length must be between 3 and 35", new Date(2017, 11, 2)));
        tasks.add(new Task(6, "Task 7", "LMMM Nabeul Tunisia", "notes: this property must not be null, location: length must be between 3 and 35", new Date(2016, 12, 7)));
        tasks.add(new Task(7, "Task 8", "Gabes Tunisia", "location: length must be between 3 and 35, location: length must be between 3 and 35", new Date(2017, 8, 26)));

        return tasks;
    }

    private void selectTask( int id ) {

        selectedtaskId = id;
        Task selectedtask = tasks.get(selectedtaskId);
        taskTitle.setText(selectedtask.getTitle());
        taskLocation.setText(selectedtask.getLocation());
        taskNotes.setText(selectedtask.getNotes());
        taskDate.setText(selectedtask.getDate().toString());
        taskCompleted.setText(selectedtask.isCompleted() ? "Completed" : "Still working on");

        taskTitleF.setText( selectedtask.getTitle() );
        taskLocationF.setText( selectedtask.getLocation() );
        taskNotesF.setText( selectedtask.getNotes() );
        //taskDateF.setValue( new LocalDate(selectedtask.getDate()));
        taskCompletedF.setSelected( selectedtask.isCompleted() );
    }

    @FXML
    private void save() {

        tasks.get(selectedtaskId).setTitle( taskTitleF.getText() );
        tasks.get(selectedtaskId).setLocation( taskLocationF.getText() );
        tasks.get(selectedtaskId).setNotes( taskNotesF.getText() );
        //tasks.get(selectedtaskId).setDate( taskDateF.getValue() );
        tasks.get(selectedtaskId).setCompleted( taskCompletedF.isSelected() );

        taskList();
        selectTask( selectedtaskId );
    }

    private void taskList() {

        tasksContainer.getChildren().clear();
        for ( Task task : tasks ){
            tasksContainer.getChildren().add( getTaskButton(task) );
        }
    }

    private Button getTaskButton ( Task task ) {

        Button button = new Button( task.getTitle() );
        button.wrapTextProperty();
        button.setOnAction( event -> selectTask( task.getId() ) );
        button.getStyleClass().addAll("btn-block");
        return button;
    }
}
