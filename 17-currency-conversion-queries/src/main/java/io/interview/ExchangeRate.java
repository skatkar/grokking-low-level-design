package io.interview;

public class ExchangeRate {
    private final String source;
    private final String target;
    private final double rate;

    public ExchangeRate(String source, String target, double rate) {
        this.source = source;
        this.target = target;
        this.rate = rate;
    }

    public String getSource() {
        return source;
    }

    public double getRate() {
        return rate;
    }

    public String getTarget() {
        return target;
    }
}
