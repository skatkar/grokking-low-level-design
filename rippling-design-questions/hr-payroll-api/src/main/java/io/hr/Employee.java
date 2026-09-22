package io.hr;

public class Employee {
    private final String employeeId;
    private final String currency;

    public Employee(String employeeId, String currency) {
        this.employeeId = employeeId;
        this.currency = currency;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public String getCurrency() {
        return currency;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId='" + employeeId + '\'' +
                ", currency='" + currency + '\'' +
                '}';
    }
}
