package behavioral.strategy.sample.PaymentStratergies;

import behavioral.strategy.sample.IPaymentStratergy;

public class UPIPayment implements IPaymentStratergy {
    @Override 
    public void pay(double amount) {
        System.out.println("Paying " + amount + " using UPI.");
    }
}
