package creational.factory.projects.paymentgatewaysystem.factory;

import creational.factory.projects.paymentgatewaysystem.enums.PaymentRepository;
import creational.factory.projects.paymentgatewaysystem.repository.IPaymentRepository;
import creational.factory.projects.paymentgatewaysystem.repository.MapPaymentRepository;

public class FPaymentRepository {
    public static IPaymentRepository getPaymentRepository(PaymentRepository method) {
        switch(method) {
            case HashMap -> {
                return new MapPaymentRepository();
            }
            default -> throw new IllegalArgumentException("Invalid payment method");
        }
    }
}
