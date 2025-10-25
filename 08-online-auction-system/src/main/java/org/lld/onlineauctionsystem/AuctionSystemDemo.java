package org.lld.onlineauctionsystem;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class AuctionSystemDemo {
    public static void main(String[] args) {
        AuctionSystem auctionSystem = AuctionSystem.getInstance();

        // Register users
        User user1 = auctionSystem.registerUser("u1", "Alice");
        User user2 = auctionSystem.registerUser("u2", "Bob");
        User user3 = auctionSystem.registerUser("u3", "Carol");

        Item item1 = new Item("Vintage Guitar", "1960s electric guitar");
        Item item2 = new Item("Macbook air 2017", "fully working, no defects");

        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDateTime later5 = localDateTime.plus(5000, ChronoUnit.MILLIS);
        LocalDateTime later6 = localDateTime.plus(6000, ChronoUnit.MILLIS);

        // Create auctions
        Auction auction1 = auctionSystem.createAuction(user1, item1, 400.0, localDateTime, later5);
        Auction auction2 = auctionSystem.createAuction(user1, item2, 700.0, localDateTime, later6);

        // Place bids
        auctionSystem.placeBid(auction1.getId(), new Bid(user2, 450.0));
        auctionSystem.placeBid(auction1.getId(), new Bid(user3, 300.0));

        try {
            Thread.sleep(6000); // let auctions expire
            auctionSystem.shutdown();
        } catch (InterruptedException e) {
            System.out.println("Interrupted Exception: " + e.getMessage());
        }
    }
}
