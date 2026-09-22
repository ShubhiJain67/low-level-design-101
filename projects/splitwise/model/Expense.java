package projects.splitwise.model;

import java.util.*;
import projects.splitwise.enums.SplitType;

public class Expense {
    private final String id;
    private final String title;
    private final double amount;
    private final User lender;
    private final List<Split> splits;
    private final SplitType splitType;

    public Expense(String title, double amount, User lender, List<User> users, SplitType splitType, List<Split> splits) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.amount = amount;
        this.lender = lender;
        this.splitType = splitType;
        this.splits = splits;
    }

    public String getId(){
        return this.id;
    }

    public String getTitle(){
        return this.title;
    }

    public double getAmount(){
        return this.amount;
    }
    public User getLender(){
        return this.lender;
    }

    public List<Split> getSplits(){
        return Collections.unmodifiableList(this.splits);
    }

    public SplitType getSplitType(){
        return this.splitType;
    }
}
