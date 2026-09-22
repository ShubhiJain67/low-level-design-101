package projects.splitwise.model;

import java.util.UUID;

public class Payment {
    private final String id;
    private final User payer;
    private final User payee;
    private final double amount;

    public Payment(User payer, User payee, double amount) {
        this.id = UUID.randomUUID().toString();
        this.payer = payer;
        this.payee = payee;
        this.amount = amount;
    }

    public String getId(){
        return this.id;
    }

    public User getPayer(){
        return this.payer;
    }

    public User getPayee(){
        return this.payee;
    }

    public double getAmount(){
        return this.amount;
    }
}
