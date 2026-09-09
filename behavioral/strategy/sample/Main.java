package behavioral.strategy.sample;

import behavioral.strategy.sample.PaymentStratergies.UPIPayment;

public class Main {
    public static void main(String[] args) {
        IPaymentStratergy strategy = new UPIPayment();

        PaymentService paymentService =
                new PaymentService(strategy);

        paymentService.makePayment(1000);
    }    
}