package strategy;

import model.Payment;

public class DebitCardPaymentStrategy
        implements IPaymentStrategy {

    @Override
    public void pay(Payment payment) {
        System.out.println("Processing Debit Card payment: " + payment.getAmount());
    }

    @Override
    public void refund(Payment payment) {
        System.out.println("Refunding Debit Card payment" + payment.getAmount());
    }
}
