package creational.factory.projects.paymentgatewaysystem;

import creational.factory.projects.paymentgatewaysystem.enums.PaymentMethod;
import creational.factory.projects.paymentgatewaysystem.enums.PaymentRepository;
import creational.factory.projects.paymentgatewaysystem.model.Payment;
import creational.factory.projects.paymentgatewaysystem.service.PaymentService;

public class Main {

    public static void main(String[] args) {

        PaymentService paymentService = new PaymentService(PaymentRepository.HashMap);

        Payment payment = paymentService.createPayment(
                        2500,
                        PaymentMethod.CASH
                );

        paymentService.processPayment(payment.getPaymentId());

        paymentService.refundPayment(payment.getPaymentId());
    }
}