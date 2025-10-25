package org.lld.onlineauctionsystem;

import java.time.LocalDateTime;
import java.util.UUID;

public class Bid {
    private String id;
    private User bidder;
    private double amount;
    private LocalDateTime dateTime;

    public Bid(User bidder, double amount) {
        this.id = UUID.randomUUID().toString();
        this.bidder = bidder;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public User getBidder() {
        return bidder;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }
}
