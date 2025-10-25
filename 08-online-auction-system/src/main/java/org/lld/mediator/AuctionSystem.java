package org.lld.mediator;

public class AuctionSystem {
    public static void main(String[] args) {
        Auction auction = Auction.getInstance();

        Colleague bidder1 = new Bidder("U124", auction);
        Colleague bidder2 = new Bidder("U5493", auction);
        Colleague bidder3 = new Bidder("X9493", auction);
        Colleague bidder4 = new Bidder("P93923", auction);

        // Bidders are now placing the bid
        auction.placeBid(bidder1, 1000);
        System.out.println("**** First bid placed ***** ");

        auction.placeBid(bidder2, 1300);
        System.out.println("**** Second bid placed ***** ");

        auction.placeBid(bidder4, 1400);
        System.out.println("**** Third bid placed ***** ");

    }
}
