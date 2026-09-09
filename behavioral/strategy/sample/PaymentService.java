package behavioral.strategy.sample;

public class PaymentService {
    private final IPaymentStratergy strategy;
    public PaymentService(IPaymentStratergy stratergy){
        this.strategy = stratergy;
    }
    public void makePayment(double amount) {
        this.strategy.pay(amount);
    }
}
