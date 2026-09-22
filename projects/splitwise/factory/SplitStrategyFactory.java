package projects.splitwise.factory;

import java.util.HashMap;
import java.util.Map;
import projects.splitwise.enums.SplitType;
import projects.splitwise.strategy.EqualSplit;
import projects.splitwise.strategy.ISplitStrategy;
import projects.splitwise.strategy.UnequalSplit;

public class SplitStrategyFactory {
    private static final Map<SplitType, ISplitStrategy> repo = new HashMap<>();

    public static ISplitStrategy getSplitStrategy(SplitType splitType) {
        if(!repo.containsKey(splitType)) {
            repo.put(splitType, createSplitStrategy(splitType));
        }
        return repo.get(splitType);
    }

    private static ISplitStrategy createSplitStrategy(SplitType splitType){
        switch (splitType) {
            case EQUAL -> {
                return new EqualSplit();
            }
            case UNEQUAL -> {
                return new UnequalSplit();
            }
            default -> throw new UnsupportedOperationException("No split strategy registered for " + splitType);
        }
    }
}
