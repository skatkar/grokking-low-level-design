package io.task;

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

    public List<String> runTasks(List<Task> tasks) {

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

    /**
     * Now tasks can have a parent_id.
     * When you run a task, run its parent first, then run the child immediately after.
     * Output the running order.
     * @param tasks
     * @return
     */
    public List<String> runTasksWithParents(List<Task> tasks) {
        // Keep track of the tasks
        Map<Integer, Task> taskById = new HashMap<>();
        for(Task task : tasks){
            taskById.put(task.getId(), task);
        }

        // Filter out the tasks based on the criteria
        List<Task> eligible = filterAndDedup(tasks);

        // Sort them by the criteria given
        eligible.sort(Comparator.comparing(Task::getDueDate)
                .thenComparing(Task::isHighPriority, Comparator.reverseOrder())
                .thenComparing(Task::getCreatedAt));

        // IDs of eligible tasks
        Set<Integer> eligibleIds = new HashSet<>();
        for(Task task : eligible){
            eligibleIds.add(task.getId());
        }

        // Split the tasks into two sub lists -
        // 1. Tasks which can be executed independent - Parent tasks
        List<Task> roots = new ArrayList<>();

        // 2. Children tasks as they have dependencies on the parent tasks
        // Map parent id to child tasks
        Map<Integer, List<Task>> children = new HashMap<>();

        // Now, populate these collections
        for(Task task : eligible) {
            Integer parentId = task.getParentId();
            if(parentId != null && eligibleIds.contains(parentId)) {
                children
                        .computeIfAbsent(parentId, k -> new ArrayList<>())
                        .add(task);
            }else {
                roots.add(task);
            }
        }

        // Execution order
        List<Task> order = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();

        for(Task root : roots) {
            run(root, children, visited, order);
        }

        // Now, generate output
        List<String> lines = new ArrayList<>();

        for(Task task : order) {
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

    private void run(Task rootTask, Map<Integer, List<Task>> children, Set<Integer> visited, List<Task> order) {
        if(visited.contains(rootTask.getId())){
            return;
        }

        visited.add(rootTask.getId());
        order.add(rootTask);

        List<Task> childTasks = children.get(rootTask.getId());
        if(childTasks != null){
            for(Task childTask : childTasks){
                run(childTask, children, visited, order);
            }
        }
    }
}
