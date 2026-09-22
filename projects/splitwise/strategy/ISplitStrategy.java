package projects.splitwise.strategy;

import java.util.List;
import java.util.Map;
import projects.splitwise.model.Split;
import projects.splitwise.model.User;

public interface ISplitStrategy {
    abstract List<Split> split(double amount, List<User> participants, Map<User, Double> metadata);
}
