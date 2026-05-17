package strategy;

import model.Payment;

public interface IPaymentStrategy {
   void pay(Payment payment);
   void refund(Payment payment);
}
