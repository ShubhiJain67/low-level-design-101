package strategy;

import model.Payment;

public class UPIPaymentStrategy
        implements IPaymentStrategy {

    @Override
    public void pay(Payment payment) {
        System.out.println("Processing UPI payment: " + payment.getAmount());
    }

    @Override
    public void refund(Payment payment) {
        System.out.println("Refunding UPI payment" + payment.getAmount());
    }
}
