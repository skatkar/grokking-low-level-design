package org.lld.vendingmachine;

public enum Bill {
    ONE(1),
    TWO(2),
    FIVE(5),
    TEN(10);

    private double value;

    Bill(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}
