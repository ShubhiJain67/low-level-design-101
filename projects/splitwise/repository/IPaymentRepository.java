package projects.splitwise.repository;

import projects.splitwise.model.Payment;

public interface IPaymentRepository {
    Payment findById(String id);
    void save(Payment payment);
}
