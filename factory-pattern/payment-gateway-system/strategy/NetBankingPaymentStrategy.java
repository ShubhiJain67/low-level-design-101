package strategy;

import model.Payment;

public class NetBankingPaymentStrategy
        implements IPaymentStrategy {

    @Override
    public void pay(Payment payment) {
        System.out.println("Processing Net Banking payment: " + payment.getAmount());
    }

    @Override
    public void refund(Payment payment) {
        System.out.println("Refunding Net Banking payment" + payment.getAmount());
    }
}
