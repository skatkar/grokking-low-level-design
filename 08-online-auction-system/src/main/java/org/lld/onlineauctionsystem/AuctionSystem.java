package org.lld.onlineauctionsystem;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class AuctionSystem {
    private static AuctionSystem instance;
    private Map<String, User> users;
    private Map<String, Auction> auctions;
    private ScheduledExecutorService scheduler;

    private AuctionSystem() {
        users = new ConcurrentHashMap<>();
        auctions = new ConcurrentHashMap<>();
        this.scheduler = Executors.newScheduledThreadPool(1);
    }

    public static synchronized AuctionSystem getInstance(){
        if(instance == null){
            instance = new AuctionSystem();
        }
        return instance;
    }

    public Auction createAuction(User seller, Item item, double startingPrice, LocalDateTime startTime, LocalDateTime endTime) {
        Auction auction = new Auction(seller, item, startingPrice, startTime, endTime);

        Duration duration = Duration.between(startTime, endTime);
        long delay = duration.getSeconds() * 1000;
        scheduler.schedule(auction::closeAuction, delay, TimeUnit.MILLISECONDS);

        System.out.println("Auction created = " + auction.getId());
        return auction;
    }

    public void placeBid(String auctionId, Bid bid) {
        Auction auction = auctions.get(auctionId);
        if(auction == null) throw new IllegalArgumentException("Auction does not exist");

        auction.placeBid(bid);
    }

    public User registerUser(String username, String name) {
        User user = new User(username, name);
        users.put(user.getUsername(), user);
        return user;
    }

    public User getUser(String username) {
        return users.get(username);
    }

    public List<Auction> viewActiveAuctions() {
        return auctions.values().stream()
                .filter(Auction::isActive)
                .collect(Collectors.toList());
    }

    public void shutdown(){
        scheduler.shutdown();
    }
}
