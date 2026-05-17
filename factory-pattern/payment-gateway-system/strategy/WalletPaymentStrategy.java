package strategy;

import model.Payment;

public class WalletPaymentStrategy
        implements IPaymentStrategy {

    @Override
    public void pay(Payment payment) {
        System.out.println("Processing Wallet payment: " + payment.getAmount());
    }

    @Override
    public void refund(Payment payment) {
        System.out.println("Refunding Wallet payment" + payment.getAmount());
    }
}
