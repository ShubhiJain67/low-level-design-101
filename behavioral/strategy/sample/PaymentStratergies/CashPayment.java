package behavioral.strategy.sample.PaymentStratergies;

import behavioral.strategy.sample.IPaymentStratergy;

public class CashPayment implements IPaymentStratergy {
    @Override 
    public void pay(double amount) {
        System.out.println("Paying " + amount + " using Cash.");
    }
}
