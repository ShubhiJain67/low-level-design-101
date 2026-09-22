package projects.splitwise.model;

import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

public class Group {
    private final String id;
    private final String name;
    private final List<User> members;
    private final List<Expense> expenses;
    private final Map<User, BalanceSheet> balanceSheets;
    private final ReentrantLock lock;

    public Group(String name){
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.members = new ArrayList<>();
        this.expenses = new ArrayList<>();
        this.balanceSheets = new HashMap<>();
        this.lock = new ReentrantLock();
    }

    public String getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public List<User> getMembers(){
        return Collections.unmodifiableList(this.members);
    }

    public List<Expense> getExpenses(){
        return Collections.unmodifiableList(this.expenses);
    }

    public void addMember(User member){
        this.members.add(member);
    }

    public void addExpenses(Expense expense){
        this.expenses.add(expense);
    }

    public Map<User, BalanceSheet> getBalanceSheets(){
        return Collections.unmodifiableMap(this.balanceSheets);
    }

    public void updateBalanceSheet(User user, BalanceSheet balanceSheet) {
        this.balanceSheets.put(user, balanceSheet);
    }

    public BalanceSheet getUserBalanceSheet(User user) {
        return this.balanceSheets.computeIfAbsent(user, u -> new BalanceSheet());
    }

    public ReentrantLock getLock(){
        return this.lock;
    }
}
