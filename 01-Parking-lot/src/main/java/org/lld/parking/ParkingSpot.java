package org.lld.parking;

import org.lld.parking.util.Vehicle;
import org.lld.parking.util.VehicleType;

public class ParkingSpot {
    private int spotNumber;
    private Vehicle parkedVehicle;
    private VehicleType vehicleType;

    public ParkingSpot(int spotNumber, VehicleType vehicleType) {
        this.spotNumber = spotNumber;
        this.vehicleType = vehicleType;
    }

    public synchronized boolean isAvailable() {
        return parkedVehicle == null;
    }

    public synchronized void parkVehicle(Vehicle vehicle) throws IllegalAccessException {
        if(isAvailable() && vehicleType == vehicle.getVehicleType()){
            parkedVehicle = vehicle;
        }else
            throw new IllegalAccessException("This vehicle is not allowed here");
    }

    public synchronized void unParkVehicle() throws IllegalAccessException {
        if(parkedVehicle != null){
            parkedVehicle = null;
        }else{
            throw new IllegalAccessException("This spot is already empty");
        }
    }

    public Vehicle getParkedVehicle() {
        return parkedVehicle;
    }

    public int getSpotNumber() {
        return spotNumber;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }
}
