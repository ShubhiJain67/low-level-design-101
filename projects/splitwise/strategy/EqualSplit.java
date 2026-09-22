package projects.splitwise.strategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import projects.splitwise.model.*;

public class EqualSplit implements ISplitStrategy {
    @Override
    public List<Split> split(double amount, List<User> participants, Map<User, Double> metadata) {
        List<Split> splits = new ArrayList<>();
        double amountPerUser = amount / participants.size();
        for (User participant : participants) {
            splits.add(new Split(participant, amountPerUser));
        }
        return splits;
    }
}
