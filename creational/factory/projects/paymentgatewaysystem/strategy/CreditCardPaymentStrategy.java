package creational.factory.projects.paymentgatewaysystem.strategy;

import creational.factory.projects.paymentgatewaysystem.model.Payment;

public class CreditCardPaymentStrategy
        implements IPaymentStrategy {

    @Override
    public void pay(Payment payment) {
        System.out.println("Processing Credit Card payment: " + payment.getAmount());
    }

    @Override
    public void refund(Payment payment) {
        System.out.println("Refunding Credit Card payment" + payment.getAmount());
    }
}
