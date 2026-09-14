package projects.parkinglot.parking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ParkingFloor
 */
public class ParkingFloor {
    private final int id;
    private final Map<VehicleTypeEnum, List<ParkingSpot>> availableSpots;

    public ParkingFloor(int id){
        this.id = id;
        this.availableSpots = new HashMap<>();
    }

    public int getId() {
        return this.id;
    }

    public void addParkingSpot(ParkingSpot spot){
        VehicleTypeEnum newSpotType = spot.getType();
        this.availableSpots.putIfAbsent(newSpotType, new ArrayList<>());
        this.availableSpots.get(newSpotType).add(spot);
    }

    public synchronized boolean hasEmptySpot(VehicleTypeEnum type) {
        return this.availableSpots.containsKey(type) && !this.availableSpots.get(type).isEmpty();
    }

    public synchronized ParkingSpot parkVehicle(VehicleTypeEnum type){
        if(!this.hasEmptySpot(type)){
            return null;
        }
        ParkingSpot spot = this.availableSpots.get(type).removeFirst();
        return spot;
    }

    public synchronized void unparkVehicle(Ticket ticket){
        ParkingSpot spot = ticket.getSpot();
        this.availableSpots.get(ticket.getType()).add(spot);
    }
}
