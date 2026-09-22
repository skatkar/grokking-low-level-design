package io.delivery;

import java.util.*;

public class DeliveryBillingSystem {
    // Part 1 attributes
    private Map<Integer, Double> drivers;
    private double totalCost;

    // Part 2 attributes
    private PriorityQueue<Delivery> unPaidHeap;
    private List<Delivery> allDeliveries;
    private double totalPaid;

    public DeliveryBillingSystem() {
        this.drivers = new HashMap<>();
        this.totalCost = 0.0;
        this.totalPaid = 0.0;
        this.unPaidHeap = new PriorityQueue<>();
        this.allDeliveries = new ArrayList<>();
    }

    // =========================
    // Part 1: Core Billing
    // =========================
    /**
     * Registers a new driver with their hourly rate.
     * The driver will not yet exist in the system.
     * @param driverId
     * @param usdHourlyRate
     */
    public void addDriver(
            int driverId,
            double usdHourlyRate) {
        drivers.put(driverId, usdHourlyRate);
    }

    /**
     * Records a completed delivery for an existing driver.
     *         - Times are Unix epoch seconds
     *         - Delivery has already completed (end_time is in the past)
     *         - Driver is guaranteed to exist
     *         - 0 < (end_time - start_time) <= 10800 (3 hours max)
     * @param driverId
     * @param startTime
     * @param endTime
     */
    public void recordDelivery(
            int driverId,
            long startTime,
            long endTime) {
        if(!drivers.containsKey(driverId)) {
            throw new IllegalArgumentException("Invalid driver");
        }

        double duration = (endTime - startTime) / 3600.0;
        double hourlyRate = drivers.get(driverId);
        double payout = hourlyRate * duration;


        Delivery delivery = new Delivery(startTime,
                endTime,
                driverId,
                payout);

        unPaidHeap.offer(delivery);
        allDeliveries.add(delivery);
        totalCost += payout;
    }

    /**
     * Returns the total accumulated payout across all recorded deliveries.
     *         This is displayed on a live dashboard - should be O(1).
     * @return
     */
    public double getTotalCost() {
        return totalCost;
    }

    // =========================
    // Part 2: Payment Tracking
    // Description - Add support to mark deliveries as paid when they finish before a cutoff time.
    // =========================


    /**
     * Marks as PAID every delivery with delivery.end_time <= end_time.
     *     - A delivery can only be paid once (no double-counting)
     *     - Calls to pay_up_to are guaranteed to have increasing end_time values
     * @param cutoffTime
     */
    public void payUpTo(long cutoffTime) {

        while(!unPaidHeap.isEmpty() && unPaidHeap.peek().getEndTime() <= cutoffTime) {
            Delivery delivery = unPaidHeap.poll();

            if(!delivery.isPaid()) {
                delivery.setPaid(true);
                totalPaid += delivery.getPayout();
            }
        }
    }

    /**
     * Returns total unpaid amount = (total cost) - (total paid so far).
     *     Should be O(1) for the live dashboard.
     * @return
     */
    public double getUnpaidAmount() {
        return totalCost - totalPaid;
    }

    // =========================
    // Part 3: Concurrency
    // =========================

    /**
     * Returns the maximum number of distinct drivers who were active
     *     at the same time in the past 24 hours: [now - 86400, now).
     *     Key considerations:
     *     - A driver counts as 1 at any instant, even with multiple overlapping deliveries
     *     - Must handle the window [now - 24h, now)
     * Active means the driver is performing a delivery (between start_time and end_time).
     * @param now
     * @return
     */
//    public int maxSimultaneousDriversInPast24Hours(long now) {
//
//    }
}
