package repository;

import model.Payment;

public interface IPaymentRepository {
    void save(Payment payment);
    Payment get(String paymentId);
}
