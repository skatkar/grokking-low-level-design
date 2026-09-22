package io.expense;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpenseRuleEngine {
    private final Map<String, List<Rule>> rulesByExpenseType;
    private final List<Rule> genericRules;
    public ExpenseRuleEngine(List<Rule> rules) {
        this.rulesByExpenseType = new HashMap<>();
        this.genericRules = rules;

        buildIndex(rules);
    }

    public List<Violation> evaluateRules(List<Expense> expenses) {
        List<Violation> violations = new ArrayList<>();
        for(Expense expense : expenses) {
            // Apply the rules that apply to all expenses
            evaluateRuleList(genericRules, expense, violations);
            
            // Apply the rules to the indexed expenses
            List<Rule> indexedRules = rulesByExpenseType.get(expense.getExpenseType());
            if(indexedRules != null) {
                evaluateRuleList(indexedRules, expense, violations);
            }
        }

        return violations;
    }

    private void buildIndex(List<Rule> rules) {
        for(Rule rule : rules) {
            String expenseType = getExpenseTypeCondition(rule);
            if(expenseType == null) {
                genericRules.add(rule);
            }else {
                rulesByExpenseType
                        .computeIfAbsent(expenseType, k -> new ArrayList<>())
                        .add(rule);
            }
        }
    }

    private void evaluateRuleList(List<Rule> rules, Expense expense, List<Violation> violations) {
        for(Rule rule : rules) {
            if(rule.isViolatedBy(expense)) {
                violations.add(
                        new Violation(expense.getExpenseId(),
                                rule.getRuleId(),
                                rule.getDescription()));
            }
        }
    }

    private String getExpenseTypeCondition(Rule rule) {
        for(Condition condition : rule.getConditions()) {
            // consider only expense_type == MEAL
            // do not consider expense_type != MEAL
            if("expense_type".equals(condition.getField()) &&
                condition.getOperator() == Operator.EQUALS) {
                return condition.getValue().toString();
            }
        }
        return null;
    }
}
