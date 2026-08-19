package creational.factory.projects.paymentgatewaysystem.repository;

import java.util.HashMap;
import java.util.Map;

import creational.factory.projects.paymentgatewaysystem.model.Payment;

public class MapPaymentRepository implements IPaymentRepository {
    private final Map<String, Payment> storage;

    public MapPaymentRepository() {
        this.storage = new HashMap<>();
    }

    @Override
    public void save(Payment payment) {
        storage.put(payment.getPaymentId(),payment);
    }

    @Override
    public Payment get(String paymentId) {
        return storage.get(paymentId);
    }
}
