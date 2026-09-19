package io.interview;

public class Violation {
    private final String expenseId;
    private final String ruleId;
    private final String ruleDescription;

    public Violation(String expenseId, String ruleId, String ruleDescription) {
        this.expenseId = expenseId;
        this.ruleId = ruleId;
        this.ruleDescription = ruleDescription;
    }

    public String getExpenseId() {
        return expenseId;
    }

    public String getRuleId() {
        return ruleId;
    }

    public String getRuleDescription() {
        return ruleDescription;
    }

    @Override
    public String toString() {
        return "Violation{" +
                "expenseId='" + expenseId + '\'' +
                ", ruleId='" + ruleId + '\'' +
                ", ruleDescription='" + ruleDescription + '\'' +
                '}';
    }
}
