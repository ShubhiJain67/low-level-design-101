package behavioral.strategy.sample.PaymentStratergies;

import behavioral.strategy.sample.IPaymentStratergy;

public class CreditCardPayment implements IPaymentStratergy {
    @Override 
    public void pay(double amount) {
        System.out.println("Paying " + amount + " using Credit Card.");
    }
}
