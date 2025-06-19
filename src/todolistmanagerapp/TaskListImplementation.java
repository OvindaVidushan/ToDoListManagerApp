/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package todolistmanagerapp;

import java.util.Date;

/**
 *
 * @author Ovinda
 */
public class TaskListImplementation {

    private final Task[] tasks;
    private int size;

    public TaskListImplementation(int capacity) {
        tasks = new Task[capacity];
        size = 0;
    }

    public void addTask(Task task) {
        if (size < tasks.length) {
            tasks[size] = task;
            size++;
        } else {
            System.out.println("Task list is full.");
        }
    }

    public void removeTask(int taskId) {
        int index = findTaskIndex(taskId);
        if (index >= 0) {

            for (int i = index; i < size - 1; i++) {
                tasks[i] = tasks[i + 1];
            }
            size--;
        } else {
            System.out.println("Task not found.");
        }
    }

    public void markTaskAsCompleted(int taskId) {
        int index = findTaskIndex(taskId);
        if (index >= 0) {
            tasks[index].markAsCompleted();
        } else {
            System.out.println("Task not found.");
        }
    }

    public void editTask(int taskId, String newTitle, String newDescription, Date newDueDate) {
        for (int i = 0; i < size; i++) {
            if (tasks[i].getId() == taskId) {
                tasks[i].setTitle(newTitle);
                tasks[i].setDescription(newDescription);
                tasks[i].setDueDate(newDueDate);
                break;
            }
        }
    }

    public Task[] getAllTasks() {
        return tasks;
    }

    public Task[] getCompletedTasks() {
        Task[] completedTasks = new Task[size];
        int count = 0;

        for (int i = 0; i < size; i++) {
            if (tasks[i].isCompleted()) {
                completedTasks[count] = tasks[i];
                count++;
            }
        }

        Task[] result = new Task[count];
        System.arraycopy(completedTasks, 0, result, 0, count);
        return result;
    }

    public Task[] getTasksDueToday() {
        Task[] dueTodayTasks = new Task[size];
        int count = 0;
        Date today = new Date();

        for (int i = 0; i < size; i++) {
            if (!tasks[i].isCompleted() && tasks[i].getDueDate().equals(today)) {
                dueTodayTasks[count] = tasks[i];
                count++;
            }
        }

        Task[] result = new Task[count];
        System.arraycopy(dueTodayTasks, 0, result, 0, count);
        return result;
    }

    private int findTaskIndex(int taskId) {
        for (int i = 0; i < size; i++) {
            if (tasks[i].getId() == taskId) {
                return i;
            }
        }
        return -1;
    }

    public void sortByDueDate() {
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (tasks[j].getDueDate().after(tasks[j + 1].getDueDate())) {
                    // Swap tasks[j] and tasks[j+1]
                    Task temp = tasks[j];
                    tasks[j] = tasks[j + 1];
                    tasks[j + 1] = temp;
                }
            }
        }
    }

    public int getSize() {
        return size;
    }
}
