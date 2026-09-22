package projects.splitwise.model;

import java.util.*;

public class BalanceSheet {
    private double totalPaid;
    private double totalExpense;
    private Map<User, Double> balances;

    public BalanceSheet() {
        this.totalPaid = 0.0;
        this.totalExpense = 0.0;
        this.balances = new HashMap<>();
    }

    public double getTotalPaid(){
        return this.totalPaid;
    }

    public double getTotalExpense(){
        return this.totalExpense;
    }

    public Map<User, Double> getBalances(){
        return Collections.unmodifiableMap(this.balances);
    }

    public void addPaid(double amount){
        this.totalPaid += amount;
    }

    public void addExpense(double amount){
        this.totalExpense += amount;
    }

    public void addBalance(User user, double amount){
        this.balances.put(user, this.balances.getOrDefault(user, 0.0) + amount);
    }

    public void removeBalance(User user, double amount){
        double pendingAmount = this.balances.getOrDefault(user, 0.0);
        if(pendingAmount < amount){
            throw new IllegalArgumentException("This much amount is not required");
        }
        if(pendingAmount > amount){
            this.balances.put(user, pendingAmount - amount);
        } else {
            this.balances.remove(user);
        }
    }

    public void clear(){
        this.balances = new HashMap<>();
    }

}
