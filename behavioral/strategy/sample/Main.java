package behavioral.strategy.sample;

import behavioral.strategy.sample.PaymentStratergies.CashPayment;

public class Main {
    public static void main(String[] args) {
        IPaymentStratergy strategy = new CashPayment();
        PaymentService paymentService = new PaymentService(strategy);

        paymentService.makePayment(1000);
    }
}