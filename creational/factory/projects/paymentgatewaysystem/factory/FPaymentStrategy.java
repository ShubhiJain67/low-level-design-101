package creational.factory.projects.paymentgatewaysystem.factory;

import creational.factory.projects.paymentgatewaysystem.enums.PaymentMethod;
import creational.factory.projects.paymentgatewaysystem.strategy.*;

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
            case UPI -> {
                return new UPIPaymentStrategy();
            }
            case WALLET -> {
                return new WalletPaymentStrategy();
            }
            default -> throw new IllegalArgumentException("Invalid payment method");
        }
    }
}
