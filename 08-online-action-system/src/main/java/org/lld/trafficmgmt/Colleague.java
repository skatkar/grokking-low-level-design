package org.lld.trafficmgmt;

public interface Colleague {
    void placeBid(int bidAmount);
    void recieveBidNotification(int bidAmount);
    String getName();
}
