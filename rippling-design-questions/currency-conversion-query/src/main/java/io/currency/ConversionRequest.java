package io.currency;

public class ConversionRequest {
    private final String source;
    private final String target;
    private final double amount;

    public ConversionRequest(String source, String target, double amount) {
        this.source = source;
        this.target = target;
        this.amount = amount;
    }

    public String getSource() {
        return source;
    }

    public double getAmount() {
        return amount;
    }

    public String getTarget() {
        return target;
    }
}

