package org.lld.vendingmachine;

import java.time.LocalDateTime;

public class Payment {
    private double amount;
    private LocalDateTime paymentDate;

    public Payment(double amount) {
        this.amount = amount;
        this.paymentDate = LocalDateTime.now();
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }
}
