package org.lld.parking;

import org.lld.parking.util.Vehicle;
import org.lld.parking.util.VehicleType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Level {
    private int floor;
    private List<ParkingSpot> parkingSpots;
    private int spots;

    public Level(int floor, int spots) {
        this.floor = floor;
        parkingSpots = new ArrayList<>();
        this.spots = spots;

        // 50% of the spots will be allocated to motorcycles.
        // 40% of the spots will be allocated to cars and rest to the trucks
        int numMotorcycles = (int) (0.5 * spots);
        int numCars = (int) (0.4 * spots);

        // Now, initialize the level with the right type of spot.
        for(int i=1; i <= numMotorcycles; i++){
            parkingSpots.add(new ParkingSpot(i, VehicleType.MOTORCYCLE));
        }

        for(int i=numMotorcycles+1; i <= numMotorcycles + numCars; i++){
            parkingSpots.add(new ParkingSpot(i, VehicleType.CAR));
        }

        for(int i=numMotorcycles + numCars + 1; i <= spots; i++){
            parkingSpots.add(new ParkingSpot(i, VehicleType.TRUCK));
        }
    }

    public synchronized boolean parkVehicle(Vehicle vehicle) throws IllegalAccessException {
        for(ParkingSpot spot : parkingSpots){
            if(spot.isAvailable() && spot.getVehicleType() == vehicle.getVehicleType()){
                spot.parkVehicle(vehicle);
                return true;
            }
        }
        return false;
    }

    public synchronized boolean unParkVehicle(Vehicle vehicle) throws IllegalAccessException {
        for(ParkingSpot spot : parkingSpots) {
            if(!spot.isAvailable() && spot.getParkedVehicle() == vehicle){
                spot.unParkVehicle();
                return true;
            }
        }
        return false;
    }

    // This is a map like a display board on a parking lot
    public Map<VehicleType, Integer> getAvailability() {
        Map<VehicleType, Integer> map = new HashMap<>();
        for(ParkingSpot spot : parkingSpots) {
            if(spot.isAvailable()){
                VehicleType vehicleType = spot.getVehicleType();
                map.put(vehicleType, map.getOrDefault(vehicleType, 0) + 1);
            }
        }

        return map;
    }

    public int getFloor() {
        return floor;
    }
}
