package org.lld.parking;

import org.lld.parking.util.Car;
import org.lld.parking.util.Motorcycle;
import org.lld.parking.util.Truck;
import org.lld.parking.util.Vehicle;

public class ParkingLotDemo {
    public static void main(String[] args) throws IllegalAccessException {
        ParkingLot parkingLot = ParkingLot.getInstance();
        parkingLot.addLevel(new Level(1, 100));
        parkingLot.addLevel(new Level(2, 80));

        Vehicle car = new Car("ABC123");
        Vehicle truck = new Truck("XYZ789");
        Vehicle motorcycle = new Motorcycle("M1234");

        // Park vehicles
        parkingLot.parkVehicle(car);
        parkingLot.parkVehicle(truck);
        parkingLot.parkVehicle(motorcycle);

        // Display availability
        parkingLot.displayAvailability();


        System.out.println("*** Unparking event happened");
        // Un park vehicle
        parkingLot.unParkVehicle(motorcycle);

        // Display updated availability
        parkingLot.displayAvailability();
    }
}