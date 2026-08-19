package creational.factory.projects.paymentgatewaysystem.repository;

import creational.factory.projects.paymentgatewaysystem.model.Payment;

public interface IPaymentRepository {
    void save(Payment payment);
    Payment get(String paymentId);
}
