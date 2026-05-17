package factory;

import enums.PaymentMethod;
import strategy.*;

public class FPaymentStrategy {
    public static IPaymentStrategy getPaymentStrategy(PaymentMethod method) {
        switch(method) {
            case DEBIT_CARD -> {
                return new DebitCardPaymentStrategy();
            }
            case CREDIT_CARD -> {
                return new CreditCardPaymentStrategy();
            }
            case NET_BANKING -> {
                return new NetBankingPaymentStrategy();
            }
            case CASH -> {
                return new CashPaymentStrategy();
            }
            default -> throw new IllegalArgumentException("Invalid payment method");
        }
    }
}
