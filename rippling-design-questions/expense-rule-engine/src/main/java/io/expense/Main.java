package io.expense;

import java.math.BigDecimal;
import java.util.List;

public class Main{
    public static void main(String[] args) {

        Condition mealCondition =
                new Condition(
                        "expense_type",
                        Operator.EQUALS,
                        "MEAL"
                );

        Condition amountCondition =
                new Condition(
                        "amount_usd",
                        Operator.GREATER_THAN,
                        new BigDecimal("100")
                );

        Rule mealRule =
                new Rule(
                        "RULE-001",
                        "Meal expense greater than $100",
                        List.of(
                                mealCondition,
                                amountCondition
                        )
                );

        Condition travelCondition =
                new Condition(
                        "expense_type",
                        Operator.EQUALS,
                        "TRAVEL"
                );

        Condition travelAmountCondition =
                new Condition(
                        "amount_usd",
                        Operator.GREATER_THAN_OR_EQUAL,
                        new BigDecimal("500")
                );

        Rule travelRule =
                new Rule(
                        "RULE-002",
                        "Travel expense of $500 or more",
                        List.of(
                                travelCondition,
                                travelAmountCondition
                        )
                );

        List<Rule> rules =
                List.of(
                        mealRule,
                        travelRule
                );

        List<Expense> expenses =
                List.of(
                        new Expense(
                                "EXP-001",
                                "MEAL",
                                new BigDecimal("150")
                        ),

                        new Expense(
                                "EXP-002",
                                "MEAL",
                                new BigDecimal("50")
                        ),

                        new Expense(
                                "EXP-003",
                                "TRAVEL",
                                new BigDecimal("700")
                        ),

                        new Expense(
                                "EXP-004",
                                "TRAVEL",
                                new BigDecimal("300")
                        ),

                        new Expense(
                                "EXP-005",
                                "HOTEL",
                                new BigDecimal("1000")
                        )
                );

        ExpenseRuleEngine engine =
                new ExpenseRuleEngine(rules);

        List<Violation> violations =
                engine.evaluateRules(expenses);

        System.out.println("Violations:");

        for (Violation violation : violations) {
            System.out.println(violation);
        }
    }
}
