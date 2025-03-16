package org.lld.vendingmachine;

public enum Coin {
    PENNY(0.01),
    NICKEL(0.05),
    DIME(0.10),
    QUARTER(0.25);

    private double value;

    Coin(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}
