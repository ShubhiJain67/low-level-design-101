package projects.parkinglot.parking;

/**
 * ParkingSpot
 */
public class ParkingSpot {
    private final int id;
    private final VehicleTypeEnum type;
    
    public ParkingSpot(int id, VehicleTypeEnum vehicleType){
        this.id = id;
        this.type = vehicleType;
    }

    public int getId() {
        return this.id;
    }

    public VehicleTypeEnum getType() {
        return this.type;
    }
}
