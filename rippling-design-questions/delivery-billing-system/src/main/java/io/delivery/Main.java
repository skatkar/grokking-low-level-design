package io.delivery;

public class Main {
    public static void main(String[] args) {

        DeliveryBillingSystem system = new DeliveryBillingSystem();

        // Add drivers
        system.addDriver(1, 30.0);
        system.addDriver(2, 40.0);
        system.addDriver(3, 50.0);

        /*
         * Timestamps are in seconds.
         *
         * Driver 1:
         *   10:00 - 12:00  -> 2 hours
         *   11:00 - 13:00  -> overlaps with first delivery
         *
         * Driver 2:
         *   11:30 - 14:30  -> 3 hours
         *
         * Driver 3:
         *   12:30 - 15:00  -> 2.5 hours
         */

        long driver1Start1 = 10 * 3600;
        long driver1End1 = 12 * 3600;

        long driver1Start2 = 11 * 3600;
        long driver1End2 = 13 * 3600;

        long driver2Start = 11 * 3600 + 30 * 60;
        long driver2End = 14 * 3600 + 30 * 60;

        long driver3Start = 12 * 3600 + 30 * 60;
        long driver3End = 15 * 3600;

        system.recordDelivery(
                1,
                driver1Start1,
                driver1End1
        );

        system.recordDelivery(
                1,
                driver1Start2,
                driver1End2
        );

        system.recordDelivery(
                2,
                driver2Start,
                driver2End
        );

        system.recordDelivery(
                3,
                driver3Start,
                driver3End
        );


        // =========================
        // Part 1: Total Cost
        // =========================

        System.out.println(
                "Total cost: $" + system.getTotalCost()
        );


        // =========================
        // Part 2: Payment Tracking
        // =========================

        System.out.println(
                "Unpaid amount: $" + system.getUnpaidAmount()
        );

        // Pay all deliveries that ended by 13:00
        long cutoffTime = 13 * 3600;

        system.payUpTo(cutoffTime);

        System.out.println(
                "Unpaid amount after payment: $"
                        + system.getUnpaidAmount()
        );


        // =========================
        // Part 3: Concurrent Drivers
        // =========================

        // Check the period ending at 15:00
        long now = 15 * 3600;

//        int maxSimultaneousDrivers =
//                system.maxSimultaneousDriversInPast24Hours(now);
//
//        System.out.println(
//                "Max simultaneous drivers: "
//                        + maxSimultaneousDrivers
//        );
    }
}