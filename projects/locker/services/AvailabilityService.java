package projects.locker.services;

import java.util.*;
import projects.locker.enums.*;
import projects.locker.models.*;
import projects.locker.strategies.*;

public class AvailabilityService {
    private final Map<LockerSelectionStrategyType, ILockerSelectionStrategy> strategies;

    public AvailabilityService() {
        this.strategies = new HashMap<>();
    }
    
    public Locker selectAvailableLocker(List<Locker> lockers, Parcel parcel, User customer){
        LockerSelectionStrategyType strategyType = this.getStrategyType(customer);
        ILockerSelectionStrategy strategy = this.getStrategy(strategyType);
        return strategy.select(lockers, parcel);
    }

    private LockerSelectionStrategyType getStrategyType(User customer){
        switch (customer.getLevel()) {
            case UserLevel.SILVER -> {
                return LockerSelectionStrategyType.SAME_SIZE;
            }
            case UserLevel.GOLD -> {
                return LockerSelectionStrategyType.SAME_SIZE;
            }
            case UserLevel.DIAMOND -> {
                return LockerSelectionStrategyType.BEST_SIZE;
            }
            default -> throw new AssertionError("found invalid user level: " + customer.getLevel());
        }
    }

    private ILockerSelectionStrategy getStrategy(LockerSelectionStrategyType type){
        ILockerSelectionStrategy strategy = this.strategies.get(type);
        if(strategy == null){
            strategy = this.createStrategy(type);
            this.strategies.put(type, strategy);
        }
        return strategy;
    }

    private ILockerSelectionStrategy createStrategy(LockerSelectionStrategyType type){
        switch (type) {
            case LockerSelectionStrategyType.SAME_SIZE -> {
                return new SameSizeLockerStrategy();
            }
            case LockerSelectionStrategyType.BEST_SIZE -> {
                return new BestSizeLockerStrategy();
            }
            default -> throw new AssertionError("found invalid locker selection strategy type");
        }
    }
}
