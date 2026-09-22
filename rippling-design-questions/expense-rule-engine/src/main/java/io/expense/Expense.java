package io.expense;

import java.math.BigDecimal;

public class Expense {
    private final String expenseId;
    private final String expenseType;
    private final BigDecimal amountUsd;

    public Expense(String expenseId, String expenseType, BigDecimal amountUsd) {
        this.expenseId = expenseId;
        this.expenseType = expenseType;
        this.amountUsd = amountUsd;
    }

    public String getExpenseId() {
        return expenseId;
    }

    public BigDecimal getAmountUsd() {
        return amountUsd;
    }

    public String getExpenseType() {
        return expenseType;
    }

    public Object getFieldValue(String field) {
        return switch (field) {
            case "expense_id" -> expenseId;
            case "expense_type" -> expenseType;
            case "amount_usd" -> amountUsd;
            default -> null;
        };
    }
}
