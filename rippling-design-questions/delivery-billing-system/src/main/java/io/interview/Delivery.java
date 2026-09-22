package io.interview;

public class Delivery implements Comparable<Delivery>{
    private final long startTime;
    private final long endTime;
    private final int driverId;
    private final double payout;
    private boolean isPaid;

    public Delivery(long startTime, long endTime, int driverId, double payout) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.driverId = driverId;
        this.payout = payout;
        this.isPaid = false;
    }

    public long getStartTime() {
        return startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public int getDriverId() {
        return driverId;
    }

    public double getPayout() {
        return payout;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    @Override
    public int compareTo(Delivery other) {
        return Long.compare(this.endTime, other.endTime);
    }
}
