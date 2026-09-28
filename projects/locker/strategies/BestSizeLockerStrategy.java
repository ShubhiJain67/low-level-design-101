package projects.locker.strategies;

import java.util.ArrayList;
import java.util.List;
import projects.locker.enums.*;
import projects.locker.models.*;

public class BestSizeLockerStrategy implements ILockerSelectionStrategy{
    private final List<Size> SIZE_HIERARCHY = new ArrayList<>(List.of(Size.SMALL, Size.MEDIUM, Size.LARGE));
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
        int index = this.SIZE_HIERARCHY.indexOf(parcel.getSize());
        while(index < SIZE_HIERARCHY.size()){
            for (Locker locker : lockers) {
                if(locker.getSize() == this.SIZE_HIERARCHY.get(index)){
                    return locker;
                }
            }
            index += 1;
        }
        return null;
    }
}
