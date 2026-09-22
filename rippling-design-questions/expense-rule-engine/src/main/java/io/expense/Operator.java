package io.expense;

enum Operator {
    EQUALS("=="),
    NOT_EQUALS("!="),
    GREATER_THAN(">"),
    LESS_THAN("<"),
    GREATER_THAN_OR_EQUAL(">="),
    LESS_THAN_OR_EQUAL("<=");

    private final String symbol;

    Operator(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public boolean isNumericOperator() {
        return this == GREATER_THAN
                || this == LESS_THAN
                || this == GREATER_THAN_OR_EQUAL
                || this == LESS_THAN_OR_EQUAL;
    }
}