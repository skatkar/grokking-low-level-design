package io.expense;

import java.util.List;

public class Rule {
    private final String ruleId;
    private final String description;
    private final List<Condition> conditions;

    public Rule(String ruleId, String description, List<Condition> conditions) {
        this.ruleId = ruleId;
        this.description = description;
        this.conditions = conditions;
    }

    public boolean isViolatedBy(Expense expense) {
        for(Condition condition : conditions){
            if(!condition.matches(expense)){
                return false;
            }
        }
        return true;
    }

    public String getRuleId() {
        return ruleId;
    }

    public String getDescription() {
        return description;
    }

    public List<Condition> getConditions() {
        return conditions;
    }
}
