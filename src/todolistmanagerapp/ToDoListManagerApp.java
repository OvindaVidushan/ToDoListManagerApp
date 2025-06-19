/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package todolistmanagerapp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author Ovinda
 */
public class ToDoListManagerApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Initialize  TaskList implementation
        TaskListImplementation taskList = new TaskListImplementation(50);
        Scanner scanner = new Scanner(System.in);

        int choice;

        do {
            // Display a menu for user interaction
            System.out.println("To-Do List Manager");
            System.out.println("1. Add Task");
            System.out.println("2. Remove Task");
            System.out.println("3. Mark Task as Completed");
            System.out.println("4. Edit Task Details");
            System.out.println("5. List All Tasks");
            System.out.println("6. List Completed Tasks");
            System.out.println("7. List Tasks Due Today");
            System.out.println("8. Sort By Due Date");
            System.out.println("9. Exit\n");

            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addTask(taskList, scanner); //add a task
                    break;
                case 2:
                    removeTask(taskList, scanner);// Remove a task
                    break;
                case 3:
                    markTaskAsCompleted(taskList, scanner);// Mark a task as completed
                    break;
                case 4:
                    editTask(taskList, scanner); // Edit a task
                    break;
                case 5:
                    listTasks("All Tasks", taskList.getAllTasks());// List all tasks
                    break;
                case 6:
                    listTasks("Completed Tasks", taskList.getCompletedTasks());// List completed tasks
                    break;
                case 7:
                    listTasks("Tasks Due Today", taskList.getTasksDueToday());// List tasks due today
                    break;
                case 8:
                    taskList.sortByDueDate();
                    listTasks("Tasks sorted by due date", taskList.getAllTasks());// Sort tasks By due date
                    break;

                case 9:
                    System.out.println("Exiting...");// Exit the program
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice.Try again.");
            }
        } while (choice != 0);
    }

    private static int getUserChoice(Scanner scanner, int min, int max) {
        int choice = -1; 
        boolean validChoice = false;

        do {
            System.out.print("Select an option: ");
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                if (choice >= min && choice <= max) {
                    validChoice = true;
                } else {
                    System.out.println("Invalid option. Please enter a number between " + min + " and " + max + ".");
                }
            } else {
                scanner.next(); 
                System.out.println("Invalid input. Please enter a valid number.");
            }
        } while (!validChoice);

        return choice;
    }

    private static void addTask(TaskListImplementation taskList, Scanner scanner) {
        System.out.print("Enter task title: ");
        String title = scanner.next();
        System.out.print("Enter task description: ");
        String description = scanner.next();

        Date dueDate = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        boolean validDate = false;
        while (!validDate) {
            System.out.print("Enter due date (yyyy-MM-dd): ");
            String dueDateString = scanner.next();

            try {
                dueDate = dateFormat.parse(dueDateString);
                validDate = true;
            } catch (ParseException e) {
                System.out.println("Invalid date format. Please enter the date in yyyy-MM-dd format.");
            }
        }

        Task newTask = new Task(taskList.getSize() + 1, title, description, dueDate);
        taskList.addTask(newTask);
        System.out.println("Task added successfully.\n");
    }

    private static void removeTask(TaskListImplementation taskList, Scanner scanner) {
        System.out.print("Enter the task ID to remove: ");
        int taskId = scanner.nextInt();

        taskList.removeTask(taskId);
        System.out.println("Task removed.\n");
    }

    private static void markTaskAsCompleted(TaskListImplementation taskList, Scanner scanner) {
        System.out.print("Enter the task ID to mark as completed: ");
        int taskId = scanner.nextInt();

        taskList.markTaskAsCompleted(taskId);
        System.out.println("Task marked as completed.\n");
    }

    private static void editTask(TaskListImplementation taskList, Scanner scanner) {

        System.out.print("Enter the task ID to edit: ");
        int taskId = getUserChoice(scanner, 1, taskList.getSize());
        scanner.nextLine();

        System.out.print("Enter new task title: ");
        String newTitle = scanner.nextLine();
        System.out.print("Enter new task description: ");
        String newDescription = scanner.nextLine();

        Date newDueDate = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        boolean validDate = false;
        while (!validDate) {
            System.out.print("Enter new due date (yyyy-MM-dd): ");
            String dueDateString = scanner.nextLine();

            try {
                newDueDate = dateFormat.parse(dueDateString);
                validDate = true;
            } catch (ParseException e) {
                System.out.println("Invalid date format. Please enter the date in yyyy-MM-dd format.\n");
            }
        }

        taskList.editTask(taskId, newTitle, newDescription, newDueDate);
        System.out.println("Task updated successfully.\n");
    }

    private static void listTasks(String listName, Task[] tasks) {
        System.out.println(listName + ":");
        for (Task task : tasks) {
            if (task != null) {
                System.out.println(task);
            }
        }
    }
}
