package projects.locker.models;

import java.util.*;
import projects.locker.enums.AvailabilityStatus;

public class LockerStation{
    private final String id;
    private final List<Locker> lockers;

    public LockerStation() {
        this.id = UUID.randomUUID().toString();
        this.lockers = new ArrayList<>();
    }

    public String getId(){
        return this.id;
    }

    public List<Locker> getAllLockers(){
        return this.lockers;
    }

    public void addLocker(Locker locker){
        if(this.containsLocker(locker)){
            throw new IllegalArgumentException("station already contains the locker");
        }
        this.lockers.add(locker);
    }

    public List<Locker> getAvailableLockers(){
        List<Locker> availableLockers = new ArrayList<>();
        for (Locker locker : this.lockers) {
            if(locker.isAvailable()){
                availableLockers.add(locker);
            }
            
        }
        return availableLockers;
    }

    public boolean containsLocker(Locker locker){
        if(locker == null){
            throw new IllegalArgumentException("Locker needs to be present");
        }
        return this.lockers.contains(locker);
    }

    private boolean isLockerInState(Locker locker, AvailabilityStatus status){
        if(!this.containsLocker(locker)){
            return false;
        }
        return locker.getStatus() == status;
    }

    public void occupyLocker(Locker locker){
        if(!this.containsLocker(locker)){
            return;
        }
        if(!this.isLockerInState(locker, AvailabilityStatus.AVAILABLE)){
            throw new IllegalStateException("Locker is not available to get occupied");
        }
        locker.setStatus(AvailabilityStatus.OCCUPIED);
    }

    public void emptyLocker(Locker locker){
        if(!this.containsLocker(locker)){
            return;
        }
        if(!this.isLockerInState(locker, AvailabilityStatus.OCCUPIED)){
            throw new IllegalStateException("Locker is not available to be emptied");
        }
        locker.setStatus(AvailabilityStatus.AVAILABLE);
    }

    public void putLockerUnderMaintenance(Locker locker){
        if(!this.containsLocker(locker)){
            return;
        }
        if(this.isLockerInState(locker, AvailabilityStatus.OCCUPIED)){
            throw new IllegalStateException("An occupied Locker cannot be put under maintenance");
        }
        locker.setStatus(AvailabilityStatus.UNDER_MAINTENANCE);
    }

    public void markLockerBroken(Locker locker){
        if(!this.containsLocker(locker)){
            return;
        }
        if(this.isLockerInState(locker, AvailabilityStatus.OCCUPIED)){
            throw new IllegalStateException("Empty the locker first to mark as broken");
        }
        locker.setStatus(AvailabilityStatus.BROKEN);
    }
}
