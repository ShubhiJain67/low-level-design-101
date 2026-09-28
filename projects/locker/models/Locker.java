package projects.locker.models;

import java.util.UUID;
import projects.locker.enums.*;

public class Locker {
    private final String id;
    private final Size size;
    private Parcel parcel;
    private final LockerStation station;
    private AvailabilityStatus status;

    public Locker(Size size, LockerStation station) {
        this.id = UUID.randomUUID().toString();
        this.size = size;
        this.station = station;
        this.status = AvailabilityStatus.AVAILABLE;
    }

    public String getId(){
        return this.id;
    }

    public Size getSize(){
        return this.size;
    }

    public AvailabilityStatus getStatus(){
        return this.status;
    }


    public void setStatus(AvailabilityStatus status){
        this.status = status;
    }

    public Parcel getParcel(){
        return this.parcel;
    }

    public void assignParcel(Parcel parcel){
        this.parcel = parcel;
    }

    public void unassignParcel(){
        this.parcel = null;
    }

    public LockerStation getLockerStation(){
        return this.station;
    }

    public boolean isAvailable(){
        return this.checkStatus(AvailabilityStatus.AVAILABLE);
    }

    public boolean isOccupied(){
        return this.checkStatus(AvailabilityStatus.OCCUPIED);
    }

    public boolean checkStatus(AvailabilityStatus status){
        return this.status == status;
    }
}
