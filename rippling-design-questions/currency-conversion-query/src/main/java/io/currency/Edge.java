package io.currency;

public class Edge {
    private final String currency;
    private final double rate;

    public Edge(String currency, double rate) {
        this.currency = currency;
        this.rate = rate;
    }

    public String getCurrency() {
        return currency;
    }

    public double getRate() {
        return rate;
    }
}
