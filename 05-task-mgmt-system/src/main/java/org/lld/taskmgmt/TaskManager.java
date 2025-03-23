package org.lld.taskmgmt;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * This class is similar to a Jira board where stories are assigned to multiple developers
 * The Jira dashboard shows the tasks per user
 * This class holds the collection of the tasks and the tasks assigned to a specific user.
 */
public class TaskManager {
    private static TaskManager instance;
    private Map<String, Task> tasks;
    private Map<String, List<Task>> userTasks;

    public TaskManager() {
        this.tasks = new ConcurrentHashMap<>();
        this.userTasks = new ConcurrentHashMap<>();
    }

    public static synchronized TaskManager getInstance() {
        if(instance == null){
            instance = new TaskManager();
        }
        return instance;
    }

    // 1. Create tasks & assign it to the user
    public void createTask(Task task) {
        tasks.put(task.getId(), task);
        assignTaskToTheUser(task.getAssignedUser(), task);
    }

    // 2. Update the task and reassign it to the user if that is a different user
    public void updateTask(Task updatedTask) {
        Task exisitngTask = tasks.get(updatedTask.getId());
        if(exisitngTask != null) {
            exisitngTask.setTitle(updatedTask.getTitle());
            exisitngTask.setDescription(updatedTask.getDescription());
            exisitngTask.setPriority(updatedTask.getPriority());
            exisitngTask.setDueDate(updatedTask.getDueDate());
            exisitngTask.setStatus(updatedTask.getStatus());

            User newUser = updatedTask.getAssignedUser();
            User previousUser = exisitngTask.getAssignedUser();
            if(previousUser != newUser){
                unassignTaskForTheUser(previousUser, exisitngTask);
                assignTaskToTheUser(newUser, exisitngTask);
            }
        }
    }

    // 3. Delete task and unassign it
    public void deleteTask(String taskId) {
        Task task = tasks.remove(taskId);
        if(task != null){
            unassignTaskForTheUser(task.getAssignedUser(), task);
        }

    }

    // 4. Search tasks based on the user specified keyword
    public List<Task> searchTasks(String keyword) {
        List<Task> matchingTasks = new ArrayList<>();
        for(Task task : tasks.values()) {
            if(task.getTitle().contains(keyword) || task.getDescription().contains(keyword)){
                matchingTasks.add(task);
            }
        }

        return matchingTasks;
    }

    // 5. Get task history for the user
    // Wrapping up in another arraylist as it will protect the original object from external modifications
    public List<Task> taskHistory(User user) {
        return new ArrayList<>(userTasks.getOrDefault(user.getId(), new ArrayList<>()));
    }

    // 6. Mark the status as completed
    public void markAsCompleted(String taskId) {
        Task task = tasks.get(taskId);
        if(task != null)
            task.setStatus(TaskStatus.COMPLETED);
    }

    // 7. Filter tasks based on the given criteria
    // Here it is an OR condition, can be replaced with AND condition
    public List<Task> filterTasks(TaskStatus status, LocalDateTime startDate, LocalDateTime endDate, int priority) {
        List<Task> filteredTasks = new ArrayList<>();
        for (Task task : tasks.values()) {
            if (task.getStatus() == status ||
                    !task.getDueDate().isBefore(startDate) ||
                    !task.getDueDate().isAfter(endDate) ||
                    task.getPriority() == priority) {
                filteredTasks.add(task);
            }
        }

        return filteredTasks;
    }

    private void unassignTaskForTheUser(User previousUser, Task task) {
        List<Task> tasks = userTasks.get(previousUser.getId());
        if (tasks != null) {
            tasks.remove(task);
        }
    }

    private void assignTaskToTheUser(User assignedUser, Task task) {
        userTasks.computeIfAbsent(assignedUser.getId(), k -> new CopyOnWriteArrayList<>()).add(task);
    }
}
