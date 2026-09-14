package projects.parkinglot.payment;

import java.util.HashMap;
import java.util.Map;

public class PaymentProcessor {
    private final Map<PaymentModeType, IPaymentStrategy> objects = new HashMap<>();

    private static class PaymentProcessorHolder {
        private final static PaymentProcessor INSTANCE = new PaymentProcessor();
    }

    public static PaymentProcessor getPaymentInstance() {
        return PaymentProcessorHolder.INSTANCE;
    }

    private IPaymentStrategy getInstance(PaymentModeType type){
        if(!objects.containsKey(type)){
            objects.put(type, getNewInstance(type));
        }
        return objects.get(type);
    }

    private IPaymentStrategy getNewInstance(PaymentModeType type){
        switch (type) {
            case CASH -> {
                return new CashPayment();
            }
            case UPI -> {
                return new UPIPayment();
            }
            default -> throw new AssertionError();
        }
    }

    public boolean pay(PaymentModeType type, double amount) {
        IPaymentStrategy strategy = this.getInstance(type);
        return strategy.pay(amount);
    }
}
