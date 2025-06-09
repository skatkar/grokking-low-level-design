package org.lld.trafficmgmt;

public interface AuctionMediator {
    void addBidder(Colleague colleague);
    void placeBid(Colleague bidder, int bidAmount);
}
