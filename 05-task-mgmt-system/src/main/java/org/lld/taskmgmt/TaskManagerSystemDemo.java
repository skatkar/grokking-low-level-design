package org.lld.taskmgmt;

import java.time.LocalDateTime;
import java.util.List;

public class TaskManagerSystemDemo {
    public static void main(String[] args) {
        TaskManager taskManager = TaskManager.getInstance();

        // Create users
        User user1 = new User("1", "John Doe", "john@example.com");
        User user2 = new User("2", "Jane Smith", "jane@example.com");

        // Create tasks
        Task task1 = new Task("1", "Task 1", "Description 1", LocalDateTime.now(), 1, user1);
        Task task2 = new Task("2", "Task 2", "Description 2", LocalDateTime.now(), 2, user2);
        Task task3 = new Task("3", "Task 3", "Description 3", LocalDateTime.now(), 1, user1);

        // Add tasks to the task manager
        taskManager.createTask(task1);
        taskManager.createTask(task2);
        taskManager.createTask(task3);

        // Update a task
        task2.setDescription("Updated description");
        taskManager.updateTask(task2);

        // Search tasks
        List<Task> searchResults = taskManager.searchTasks("Task");
        System.out.println("Search Results:");
        for (Task task : searchResults) {
            System.out.println(task.getTitle());
        }

        // Filter tasks
        List<Task> filteredTasks = taskManager.filterTasks(TaskStatus.PENDING, LocalDateTime.now().minusDays(2), LocalDateTime.now().plusDays(2), 1);
        System.out.println("Filtered Tasks:");
        for (Task task : filteredTasks) {
            System.out.println(task.getTitle());
        }

        // Mark a task as completed
        taskManager.markAsCompleted("1");

        // Get task history for a user
        List<Task> taskHistory = taskManager.taskHistory(user1);
        System.out.println("Task History for " + user1.getName() + ":");
        for (Task task : taskHistory) {
            System.out.println(task.getTitle());
        }

        // Delete a task
        taskManager.deleteTask("3");
    }
}