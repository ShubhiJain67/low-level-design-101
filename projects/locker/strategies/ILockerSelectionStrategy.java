package projects.locker.strategies;

import java.util.List;
import projects.locker.models.*;

public interface ILockerSelectionStrategy {
    Locker select(List<Locker> lockers, Parcel parcel);
}
