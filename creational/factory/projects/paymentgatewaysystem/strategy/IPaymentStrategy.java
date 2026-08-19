package creational.factory.projects.paymentgatewaysystem.strategy;

import creational.factory.projects.paymentgatewaysystem.model.Payment;

public interface IPaymentStrategy {
   void pay(Payment payment);
   void refund(Payment payment);
}
