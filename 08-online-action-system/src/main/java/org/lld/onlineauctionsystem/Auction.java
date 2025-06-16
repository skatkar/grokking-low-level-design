package org.lld.onlineauctionsystem;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

public class Auction {
    private String id;
    private Item item;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private AuctionStatus status;
    private Bid highestBid;
    private List<Bid> bidHistory;

    public Auction(User seller, Item item, double startingPrice, LocalDateTime startTime, LocalDateTime endTime) {
        this.id = UUID.randomUUID().toString();
        this.item = item;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = AuctionStatus.ACTIVE;
        this.highestBid = new Bid(seller, startingPrice);
        this.bidHistory = new CopyOnWriteArrayList<>();
    }

    public synchronized void placeBid(Bid bid) {
        if(status == AuctionStatus.CLOSED)
            throw new IllegalStateException("Bid is closed");

        if(bid.getAmount() <= highestBid.getAmount())
            throw new IllegalArgumentException("Bid is too low");

        highestBid = bid;
        bidHistory.add(bid);

        System.out.println("New highest bid = " + bid.getAmount() + " by " + bid.getBidder().getUsername());
    }

    public synchronized void closeAuction() {
        if(status == AuctionStatus.CLOSED) return;

        status = AuctionStatus.CLOSED;
        System.out.println("Bid closed for = " + item.getName() + ". Winner is " + highestBid.getBidder().getUsername() + " with " + highestBid.getAmount());
    }

    public boolean isActive() {
        return status == AuctionStatus.ACTIVE && LocalDateTime.now().isBefore(endTime);
    }

    public String getId() {
        return id;
    }

    public Item getItem() {
        return item;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public AuctionStatus getStatus() {
        return status;
    }

    public Bid getHighestBid() {
        return highestBid;
    }

    public List<Bid> getBidHistory() {
        return bidHistory;
    }
}
