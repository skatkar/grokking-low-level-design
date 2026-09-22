package io.hr;

import java.time.LocalDate;

public class SalaryChange {
    private final String changeId;
    private final String employeeId;
    private final Money salary;
    private final LocalDate effectiveOn;
    private final LocalDate createdOn;
    private final String reason;

    private boolean cancelled;

    public SalaryChange(String changeId, String employeeId, Money salary, LocalDate effectiveOn, LocalDate createdOn, String reason) {
        this.changeId = changeId;
        this.employeeId = employeeId;
        this.salary = salary;
        this.effectiveOn = effectiveOn;
        this.createdOn = createdOn;
        this.reason = reason;
    }

    public void cancel(){
        this.cancelled = true;
    }

    public boolean isCancelled(){
        return cancelled;
    }

    public SalaryStatusChange status(LocalDate date){
        if(cancelled)
            return SalaryStatusChange.CANCELLED;

        if(!effectiveOn.isAfter(date))
            return SalaryStatusChange.EFFECTIVE;
        return SalaryStatusChange.SCHEDULED;
    }

    public String getChangeId() {
        return changeId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public Money getSalary() {
        return salary;
    }

    public LocalDate getEffectiveOn() {
        return effectiveOn;
    }

    public LocalDate getCreatedOn() {
        return createdOn;
    }

    public String getReason() {
        return reason;
    }
}
