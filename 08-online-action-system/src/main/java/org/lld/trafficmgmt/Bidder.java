package org.lld.trafficmgmt;

public class Bidder implements Colleague{
    String name;
    AuctionMediator auctionMediator;

    public Bidder(String name, AuctionMediator auctionMediator) {
        this.name = name;
        this.auctionMediator = auctionMediator;
        auctionMediator.addBidder(this);
    }


    @Override
    public void placeBid(int bidAmount) {
        auctionMediator.placeBid(this, bidAmount);
    }

    @Override
    public void recieveBidNotification(int bidAmount) {
        System.out.println("Bidder : " + this.name + " Bid Amount = " + bidAmount);
    }

    @Override
    public String getName() {
        return name;
    }
}
