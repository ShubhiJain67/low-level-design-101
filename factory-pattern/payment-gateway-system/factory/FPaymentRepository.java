package factory;

import enums.PaymentRepository;
import repository.IPaymentRepository;
import repository.MapPaymentRepository;

public class FPaymentRepository {
    public static IPaymentRepository getPaymentStrategy(PaymentRepository method) {
        switch(method) {
            case HashMap -> {
                return new MapPaymentRepository();
            }
            default -> throw new IllegalArgumentException("Invalid payment method");
        }
    }
}
