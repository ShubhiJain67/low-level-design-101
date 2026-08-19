package creational.factory.projects.paymentgatewaysystem.service;

import creational.factory.projects.paymentgatewaysystem.enums.PaymentMethod;
import creational.factory.projects.paymentgatewaysystem.enums.PaymentRepository;
import creational.factory.projects.paymentgatewaysystem.enums.PaymentStatus;
import creational.factory.projects.paymentgatewaysystem.exception.PaymentFailedException;
import creational.factory.projects.paymentgatewaysystem.factory.FPaymentRepository;
import creational.factory.projects.paymentgatewaysystem.factory.FPaymentStrategy;
import creational.factory.projects.paymentgatewaysystem.model.Payment;
import creational.factory.projects.paymentgatewaysystem.repository.IPaymentRepository;
import creational.factory.projects.paymentgatewaysystem.strategy.IPaymentStrategy;

public class PaymentService {

    private final IPaymentRepository repository;

    public PaymentService(PaymentRepository repositoryType) {

        this.repository = FPaymentRepository.getPaymentRepository(repositoryType);
    }

    public Payment createPayment(double amount, PaymentMethod type) {
        Payment payment = new Payment(amount, type);
        repository.save(payment);
        return payment;
    }

    public void processPayment(String paymentId) {
        Payment payment = repository.get(paymentId);
        if(payment == null) {
            throw new PaymentFailedException("Payment not found");
        }

        IPaymentStrategy strategy = FPaymentStrategy.getPaymentStrategy(payment.getMethod());

        try {
            strategy.pay(payment);
            payment.setStatus(PaymentStatus.COMPLETED);

            System.out.println("Payment Successful");

        } catch (Exception ex) {

            payment.setStatus(
                    PaymentStatus.FAILED
            );

            throw new PaymentFailedException(
                    "Payment Failed"
            );
        }
    }

    public void refundPayment(String paymentId) {

        Payment payment = repository.get(paymentId);

        if(payment == null) { 
                throw new PaymentFailedException("Payment not found");
        }

        if(payment.getStatus()!= PaymentStatus.COMPLETED) {
            throw new PaymentFailedException(
                    "Only successful payments can be refunded"
            );
        }

        IPaymentStrategy strategy = FPaymentStrategy.getPaymentStrategy(payment.getMethod());
        strategy.refund(payment);
        payment.setStatus(PaymentStatus.REFUNDED);
        System.out.println("Refund Successful");
    }
}