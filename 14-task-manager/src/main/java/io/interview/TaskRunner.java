package io.interview;

import java.util.*;

public class TaskRunner {
    /**
     * Filter and sort the tasks, then print them in priority order.
     * @param tasks
     * @return
     */
    public static List<Task> filterAndDedup(List<Task> tasks) {
        Set<String> seen = new HashSet<>();
        List<Task> result = new ArrayList<>();

        for(Task task : tasks) {
            if(task.getAssignee() != null)
                continue;
            if(task.isDone()){
                continue;
            }

            // Primary key to make the records unique in the collection
            String taskKey = task.getId() + " | " + task.getDescription();

            // Observed this key before
            if(seen.contains(taskKey)) {
                continue;
            }

            seen.add(taskKey);
            result.add(task);
        }

        return result;
    }

    public static List<String> runTasks(List<Task> tasks) {

        // Keep track of the tasks
        Map<Integer, Task> taskById = new HashMap<>();
        for(Task task : tasks) {
            taskById.put(task.getId(), task);
        }

        // Filter out the tasks based on the criteria
        List<Task> eligible = filterAndDedup(tasks);

        // Sort them by the criteria given
        eligible.sort(Comparator.comparing(Task::getDueDate)
                .thenComparing(Task::isHighPriority, Comparator.reverseOrder())
                .thenComparing(Task::getCreatedAt));

        // Now print the tasks
        List<String> lines = new ArrayList<>();
        for(Task task : eligible) {
            String line = "Task ID: " + task.getId()
                    + ", Description: " + task.getDescription();

            if(task.getParentId() != null) {
                Task parentTask = taskById.get(task.getParentId());
                if(parentTask != null) {
                    line += ", parent: " + parentTask.getDescription();
                }
            }

            lines.add(line);
            System.out.println(line);
        }

        return lines;
    }
}
