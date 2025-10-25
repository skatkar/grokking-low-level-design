package org.lld.mediator;

import java.util.ArrayList;
import java.util.List;

public class Auction implements  AuctionMediator{
    private static Auction instance;
    List<Colleague> colleagues;

    private Auction(){
        this.colleagues = new ArrayList<>();
    }

    public static synchronized Auction getInstance() {
        if(instance == null){
            instance = new Auction();
        }
        return instance;
    }

    @Override
    public void addBidder(Colleague colleague) {
        colleagues.add(colleague);
    }

    @Override
    public void placeBid(Colleague bidder, int bidAmount) {
        for(Colleague colleague : colleagues) {
            if(!colleague.getName().equals(bidder.getName())){
                colleague.recieveBidNotification(bidAmount);
            }
        }
    }
}
