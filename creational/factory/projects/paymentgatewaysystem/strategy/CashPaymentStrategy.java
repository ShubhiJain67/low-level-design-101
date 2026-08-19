package creational.factory.projects.paymentgatewaysystem.strategy;

import creational.factory.projects.paymentgatewaysystem.model.Payment;

public class CashPaymentStrategy
        implements IPaymentStrategy {

    @Override
    public void pay(Payment payment) {
        System.out.println("Processing Cash payment: " + payment.getAmount());
    }

    @Override
    public void refund(Payment payment) {
        System.out.println("Refunding Cash payment " + payment.getAmount());
    }
}
