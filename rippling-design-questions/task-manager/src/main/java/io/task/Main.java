package io.task;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Task> tasks1 = new ArrayList<>();

        tasks1.add(new Task(
                1,
                "Review payroll run",
                "2026-07-20",
                "2026-07-10T09:00:00",
                false,
                null,
                false,
                null
        ));

        tasks1.add(new Task(
                2,
                "Approve expense report",
                "2026-07-18",
                "2026-07-11T08:00:00",
                true,
                null,
                false,
                null
        ));

        tasks1.add(new Task(
                3,
                "Approve expense report",
                "2026-07-18",
                "2026-07-12T08:00:00",
                true,
                null,
                false,
                null
        ));

        tasks1.add(new Task(
                4,
                "Onboard new hire",
                "2026-07-18",
                "2026-07-09T10:00:00",
                false,
                null,
                false,
                null
        ));

        tasks1.add(new Task(
                5,
                "Fix failing sync",
                "2026-07-18",
                "2026-07-08T07:00:00",
                false,
                "alice",
                false,
                null
        ));

        tasks1.add(new Task(
                6,
                "Archive old records",
                "2026-07-25",
                "2026-07-05T07:00:00",
                false,
                null,
                true,
                null
        ));

        tasks1.add(new Task(
                7,
                "Sub-task of payroll",
                "2026-07-20",
                "2026-07-10T11:00:00",
                false,
                null,
                false,
                1
        ));

        List<Task> tasks2 = new ArrayList<>();

        tasks2.add(new Task(
                1,
                "Prepare payroll batch",
                "2026-07-20",
                "2026-07-10T09:00:00",
                false,
                null,
                false,
                null
        ));

        tasks2.add(new Task(
                2,
                "Approve expense report",
                "2026-07-18",
                "2026-07-11T08:00:00",
                true,
                null,
                false,
                null
        ));

        tasks2.add(new Task(
                4,
                "Onboard new hire",
                "2026-07-18",
                "2026-07-09T10:00:00",
                false,
                null,
                false,
                null
        ));

        tasks2.add(new Task(
                7,
                "Validate payroll batch",
                "2026-07-20",
                "2026-07-10T11:00:00",
                false,
                null,
                false,
                1
        ));

        tasks2.add(new Task(
                8,
                "Send payroll emails",
                "2026-07-18",
                "2026-07-12T09:00:00",
                false,
                null,
                false,
                7
        ));

        TaskRunner runner = new TaskRunner();
        System.out.println("Tasks independently => ");
        runner.runTasks(tasks1);

        System.out.println("Tasks after their parent => ");
        runner.runTasksWithParents(tasks2);
    }
}