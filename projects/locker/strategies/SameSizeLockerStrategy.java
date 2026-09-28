package projects.locker.strategies;

import java.util.List;
import projects.locker.models.Locker;
import projects.locker.models.Parcel;

public class SameSizeLockerStrategy implements ILockerSelectionStrategy{
    @Override
    public Locker select(List<Locker> lockers, Parcel parcel){
        if(lockers == null){
            throw new IllegalArgumentException("lockers list cannot be null");
        }
        if(parcel == null){
            throw new IllegalArgumentException("Found no parcel to select locker for");
        }
        if(lockers.isEmpty()){
            return null;
        }
        for (Locker locker : lockers) {
            if(locker.getSize() == parcel.getSize()){
                return locker;
            }
        }
        return null;
    }
}
