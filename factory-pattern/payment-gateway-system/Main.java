import enums.PaymentMethod;
import enums.PaymentRepository;
import model.Payment;
import service.PaymentService;

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