package org.lld.parking;

import org.lld.parking.util.Vehicle;
import org.lld.parking.util.VehicleType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ParkingLot {
    private static ParkingLot instance;
    private List<Level> levels;

    private ParkingLot() {
        levels = new ArrayList<>();
    }

    public static synchronized ParkingLot getInstance() {
        if(instance == null){
            instance = new ParkingLot();
        }

        return instance;
    }

    public void addLevel(Level level) {
        levels.add(level);
    }

    public synchronized boolean parkVehicle(Vehicle vehicle) throws IllegalAccessException {
        for(Level level : levels) {
            if(level.parkVehicle(vehicle)){
                return true;
            }
        }
        return false;
    }

    public synchronized boolean unParkVehicle(Vehicle vehicle) throws IllegalAccessException {
        for(Level level: levels) {
            if(level.unParkVehicle(vehicle)){
                return true;
            }
        }

        return false;
    }

    public void displayAvailability() {
        for (Level level : levels) {
            Map<VehicleType, Integer> availability = level.getAvailability();
            System.out.println("Level #" + level.getFloor());
            availability.forEach((k, v) ->
                    System.out.println(k + " : " + v));
            System.out.println("*****************************");
        }
    }
}
