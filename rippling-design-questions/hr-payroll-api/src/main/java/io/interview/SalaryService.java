package io.interview;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

public class SalaryService {

    // Maintain all employees
    Map<String, Employee> employees = new HashMap<>();

    // Maintain all timelines
    Map<String, SalaryTimeline> timelines = new HashMap<>();

    // Maintain all salary changes including canceled ones
    Map<String, SalaryChange> salaryChanges = new HashMap<>();

    /**
     * Add the employee details like its own details, timelines, salary changes to the collection
     * @param employeeId
     * @param currency
     * @param startingSalary
     * @param startDate
     * @return
     */
    public Employee addEmployee(
            String employeeId,
            String currency,
            Money startingSalary,
            LocalDate startDate) {
        if(employees.get(employeeId) != null)
            throw new PayrollException.EmployeeExist("Employee with " + employeeId + " already exist");

        Employee employee = new Employee(employeeId, currency);
        validateMoney(employee, startingSalary);

        employees.put(employeeId, employee);
        timelines.put(employeeId, new SalaryTimeline());

        recordSalaryChanges(employeeId, startingSalary, startDate, "starting salary");

        return employee;
    }

    public SalaryChange scheduleSalaryChange(String employeeId,
                                             Money newSalary,
                                             LocalDate effectiveOn,
                                             String reason) {
        Employee employee = requireEmployee(employeeId);

        validateMoney(employee, newSalary);

        if(effectiveOn.isBefore(LocalDate.now())){
            throw new PayrollException.InvalidSalary("Scheduled date is before the current date");
        }

        return recordSalaryChanges(employeeId, newSalary, effectiveOn, reason);
    }

    public Money getSalary(
            String employeeId,
            LocalDate asOf) {
        requireEmployee(employeeId);

        LocalDate effectiveDate =
                asOf != null ? asOf : LocalDate.now();

        return timelines
                .get(employeeId)
                .salaryAsOf(effectiveDate);
    }

    public List<SalaryChange> listSalaryChanges(String employeeId,boolean includeCancelled) {
        requireEmployee(employeeId);

        SalaryTimeline timeline = timelines.get(employeeId);

        if (!includeCancelled) {
            return timeline.activeChanges();
        }

        return salaryChanges.values()
                .stream()
                .filter(change ->
                        change.getEmployeeId().equals(employeeId))
                .sorted(
                        Comparator
                                .comparing(SalaryChange::getEffectiveOn)
                                .thenComparing(SalaryChange::getCreatedOn)
                )
                .toList();
    }

    public SalaryChange cancelSalaryChange(
            String employeeId,
            String changeId
    ) {
        requireEmployee(employeeId);

        SalaryChange change = salaryChanges.get(changeId);

        if (change == null ||
                !change.getEmployeeId().equals(employeeId)) {

            throw new PayrollException.SalaryChangeNotFound(
                    "no salary change " + changeId +
                            " for employee " + employeeId
            );
        }

        // Idempotent operation.
        if (change.isCancelled()) {
            return change;
        }

        LocalDate today = LocalDate.now();

        if (!change.getEffectiveOn().isAfter(today)) {
            throw new PayrollException.InvalidSalary(
                    "this change is already effective; " +
                            "schedule a new change to adjust the salary"
            );
        }

        timelines
                .get(employeeId)
                .remove(change);

        change.cancel();

        return change;
    }

    // ---------- helpers ----------
    private Employee requireEmployee(String employeeId) {
        Employee employee = employees.get(employeeId);

        if (employee == null) {
            throw new PayrollException.EmployeeNotFound(
                    "unknown employee " + employeeId
            );
        }

        return employee;
    }

    private void validateMoney(Employee employee, Money startingSalary) {
        if(startingSalary == null || startingSalary.getAmount().compareTo(BigDecimal.ZERO) <= 0){
            throw new PayrollException.InvalidSalary("Invalid salary amount ");
        }

        if(!Objects.equals(employee.getCurrency(), startingSalary.getCurrency())){
            throw new PayrollException.InvalidSalary("Currency not matching ");
        }
    }

    private SalaryChange recordSalaryChanges(String employeeId, Money salary, LocalDate effectiveOn, String reason){
        SalaryChange change = new SalaryChange(
                UUID.randomUUID().toString(),
                employeeId,
                salary,
                effectiveOn,
                LocalDate.now(),
                reason
        );

        timelines.get(employeeId).insert(change);
        salaryChanges.put(change.getChangeId(), change);
        return change;
    }
}
