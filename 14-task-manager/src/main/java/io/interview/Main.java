package io.interview;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Task> tasks = new ArrayList<>();

        tasks.add(new Task(
                1,
                "Complete report",
                "2026-07-20",
                "2026-07-10T09:00:00",
                true,
                null,
                false,
                null
        ));

        tasks.add(new Task(
                2,
                "Review report",
                "2026-07-18",
                "2026-07-11T10:00:00",
                false,
                null,
                false,
                1
        ));

        tasks.add(new Task(
                3,
                "Complete report",
                "2026-07-20",
                "2026-07-12T09:00:00",
                false,
                null,
                false,
                null
        ));

        tasks.add(new Task(
                4,
                "Submit invoice",
                "2026-07-15",
                "2026-07-10T08:00:00",
                false,
                "John",
                false,
                null
        ));

        tasks.add(new Task(
                5,
                "Prepare presentation",
                "2026-07-18",
                "2026-07-09T12:00:00",
                true,
                null,
                false,
                null
        ));

        tasks.add(new Task(
                6,
                "Old task",
                "2026-07-14",
                "2026-07-08T12:00:00",
                true,
                null,
                true,
                null
        ));

        List<String> result = TaskRunner.runTasks(tasks);
    }
}