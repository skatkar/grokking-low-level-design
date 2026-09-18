package io.interview;

public class Task {

    private int id;
    private String description;
    private String dueDate;
    private String createdAt;
    private boolean isHighPriority;
    private String assignee;
    private boolean isDone;
    private Integer parentId;

    public Task(
            int id,
            String description,
            String dueDate,
            String createdAt,
            boolean isHighPriority,
            String assignee,
            boolean isDone,
            Integer parentId
    ) {
        this.id = id;
        this.description = description;
        this.dueDate = dueDate;
        this.createdAt = createdAt;
        this.isHighPriority = isHighPriority;
        this.assignee = assignee;
        this.isDone = isDone;
        this.parentId = parentId;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getDueDate() {
        return dueDate;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public boolean isHighPriority() {
        return isHighPriority;
    }

    public String getAssignee() {
        return assignee;
    }

    public boolean isDone() {
        return isDone;
    }

    public Integer getParentId() {
        return parentId;
    }
}
