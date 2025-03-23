package org.lld.taskmgmt;

import java.time.LocalDateTime;

public class Task {
    private String id;
    private String title;
    private String description;
    private LocalDateTime dueDate;
    private int priority;
    private TaskStatus status;
    private User assignedUser;

    public Task(String id, String title, String description, LocalDateTime dueDate, int priority, User assignedUser) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dueDate = LocalDateTime.now();
        this.priority = priority;
        this.status = TaskStatus.PENDING;
        this.assignedUser = assignedUser;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public User getAssignedUser() {
        return assignedUser;
    }

    public void setAssignedUser(User assignedUser) {
        this.assignedUser = assignedUser;
    }
}
