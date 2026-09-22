package projects.splitwise.repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import projects.splitwise.model.Payment;

public class InMemoryPaymentRepository implements IPaymentRepository {
    private final Map<String, Payment> payments;

    public InMemoryPaymentRepository() {
        this.payments = new ConcurrentHashMap<>();
    }

    @Override
    public Payment findById(String id) {
        return this.payments.get(id);
    }

    @Override
    public void save(Payment payment) {
        this.payments.put(payment.getId(), payment);
    }
}
