/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package todolistmanagerapp;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Ovinda
 */
public class Task {

    private int id;
    private String title;
    private String description;
    private Date dueDate;
    private boolean completed;

    public Task(int id, String title, String description, Date dueDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.completed = false; 
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public void markAsCompleted() {
        completed = true;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dueDateString = dateFormat.format(dueDate);
        String status = completed ? "Completed" : "Not Completed";

        return "Task ID: " + id + "\n"
                + "Title: " + title + "\n"
                + "Description: " + description + "\n"
                + "Due Date: " + dueDateString + "\n"
                + "Status: " + status + "\n";

    }
}
