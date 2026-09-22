package io.hr;

public class PayrollException {
    public static class SalaryError extends RuntimeException{
        public SalaryError(String message) {
            super(message);
        }
    }

    public static class SalaryChangeConflict extends SalaryError{
        public SalaryChangeConflict(String message){
            super(message);
        }
    }

    public static class SalaryChangeNotFound extends SalaryError{
        public SalaryChangeNotFound(String message){
            super(message);
        }
    }

    public static class EmployeeExist extends SalaryError {
        public EmployeeExist(String message) {
            super(message);
        }
    }

    public static class InvalidSalary extends SalaryError {
        public InvalidSalary(String message) {
            super(message);
        }
    }

    public static class EmployeeNotFound extends SalaryError {
        public EmployeeNotFound(String message){
            super(message);
        }
    }
}
