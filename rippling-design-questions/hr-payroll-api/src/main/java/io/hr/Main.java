package io.hr;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        SalaryService salaryService = new SalaryService();

        // Add employee with starting salary
        Employee employee = salaryService.addEmployee(
                "E001",
                "USD",
                new Money(
                        new BigDecimal("100000"),
                        "USD"
                ),
                LocalDate.of(2025, 1, 1)
        );

        System.out.println("Employee: " + employee);

        // Get current salary
        Money currentSalary = salaryService.getSalary("E001", null);
        System.out.println("Current salary: " + currentSalary);

        // Schedule a salary change
        SalaryChange promotion = salaryService.scheduleSalaryChange(
                "E001",
                new Money(
                        new BigDecimal("110000"),
                        "USD"
                ),
                LocalDate.of(2026, 10, 1),
                "Promotion"
        );

        System.out.println("\nScheduled change:");
        System.out.println("ID: " + promotion.getChangeId());
        System.out.println("Salary: " + promotion.getReason());
        System.out.println("Effective date: " + promotion.getEffectiveOn());
        System.out.println("Status: " +
                promotion.status(LocalDate.of(2026, 9, 21)));

        // Schedule another salary change
        SalaryChange raise = salaryService.scheduleSalaryChange(
                "E001",
                new Money(
                        new BigDecimal("120000"),
                        "USD"
                ),
                LocalDate.of(2027, 1, 1),
                "Annual raise"
        );

        System.out.println("\nScheduled change:");
        System.out.println("ID: " + raise.getChangeId());
        System.out.println("Salary: " + raise.getSalary());
        System.out.println("Effective date: " + raise.getEffectiveOn());

        // Get salary as of different dates
        System.out.println("\nSalary history:");

        System.out.println(
                "As of 2025-06-01: " +
                        salaryService.getSalary(
                                "E001",
                                LocalDate.of(2025, 6, 1)
                        )
        );

        System.out.println(
                "As of 2026-09-21: " +
                        salaryService.getSalary(
                                "E001",
                                LocalDate.of(2026, 9, 21)
                        )
        );

        System.out.println(
                "As of 2026-11-01: " +
                        salaryService.getSalary(
                                "E001",
                                LocalDate.of(2026, 11, 1)
                        )
        );

        System.out.println(
                "As of 2027-02-01: " +
                        salaryService.getSalary(
                                "E001",
                                LocalDate.of(2027, 2, 1)
                        )
        );

        // List active salary changes
        System.out.println("\nActive salary changes:");

        List<SalaryChange> activeChanges =
                salaryService.listSalaryChanges(
                        "E001",
                        false
                );

        for (SalaryChange change : activeChanges) {
            System.out.println(
                    change.getEffectiveOn() +
                            " -> " +
                            change.getSalary() +
                            " (" +
                            change.getReason() +
                            ")"
            );
        }

        // Try scheduling another change on the same date
        System.out.println("\nTesting salary change conflict:");

        try {
            salaryService.scheduleSalaryChange(
                    "E001",
                    new Money(
                            new BigDecimal("115000"),
                            "USD"
                    ),
                    LocalDate.of(2026, 10, 1),
                    "Another change"
            );
        } catch (PayrollException.SalaryChangeConflict e) {
            System.out.println("Expected conflict: " + e.getMessage());
        }

        // Cancel the future raise
        System.out.println("\nCancelling future salary change:");

        SalaryChange cancelled =
                salaryService.cancelSalaryChange(
                        "E001",
                        raise.getChangeId()
                );

        System.out.println(
                "Cancelled: " +
                        cancelled.getChangeId()
        );

        System.out.println(
                "Status: " +
                        cancelled.status(LocalDate.of(2026, 9, 21))
        );

        // List all history including cancelled changes
        System.out.println("\nComplete salary history:");

        List<SalaryChange> history =
                salaryService.listSalaryChanges(
                        "E001",
                        true
                );

        for (SalaryChange change : history) {
            System.out.println(
                    change.getEffectiveOn() +
                            " -> " +
                            change.getSalary() +
                            " | status=" +
                            change.status(LocalDate.of(2026, 9, 21)) +
                            " | reason=" +
                            change.getReason()
            );
        }
    }
}