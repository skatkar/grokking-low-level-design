package io.expense;

import java.math.BigDecimal;

public class Condition {
    private final String field;
    private final Operator operator;
    private final Object value;

    private BigDecimal numericValue;

    public Condition(String field, Operator operator, Object value) {
        this.field = field;
        this.operator = operator;
        this.value = value;
        this.numericValue = operator.isNumericOperator() ? toBigDecimal(value) : null;
    }

    public boolean matches(Expense expense) {
        Object expenseValue = expense.getFieldValue(field);
        if(expenseValue == null){
            return false;
        }

        if(operator.isNumericOperator()){
            BigDecimal expenseNumber = toBigDecimal(expenseValue);
            if(expenseNumber == null)
                return false;

            int comparison = expenseNumber.compareTo(numericValue);

            return switch(operator){
                case GREATER_THAN -> comparison > 0;
                case LESS_THAN -> comparison < 0;
                case GREATER_THAN_OR_EQUAL -> comparison >= 0;
                case LESS_THAN_OR_EQUAL -> comparison <= 0;
                default -> false;
            };
        }

        return switch (operator) {
            case EQUALS -> expenseValue.equals(value);
            case NOT_EQUALS -> !expenseValue.equals(value);
            default -> false;
        };
    }

    private BigDecimal toBigDecimal(Object value) {
        if(value == null)
            return null;
        try {
            return new BigDecimal(value.toString());
        }catch (NumberFormatException e){
            throw e;
        }
    }

    public String getField() {
        return field;
    }

    public Operator getOperator() {
        return operator;
    }

    public Object getValue() {
        return value;
    }

    public BigDecimal getNumericValue() {
        return numericValue;
    }
}
