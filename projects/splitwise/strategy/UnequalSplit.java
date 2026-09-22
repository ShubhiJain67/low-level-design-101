package projects.splitwise.strategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import projects.splitwise.model.*;

public class UnequalSplit implements ISplitStrategy {
    @Override
    public List<Split> split(double amount, List<User> participants, Map<User, Double> metadata) {
        List<Split> splits = new ArrayList<>();
        for (User participant : metadata.keySet()) {
            splits.add(new Split(participant, metadata.get(participant)));
        }
        return splits;
    }
}
