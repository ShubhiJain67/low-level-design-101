package creational.factory.projects.paymentgatewaysystem.model;
import java.util.UUID;

import creational.factory.projects.paymentgatewaysystem.enums.PaymentMethod;
import creational.factory.projects.paymentgatewaysystem.enums.PaymentStatus;

public class Payment {
    private final UUID paymentId;
    private final double amount;
    private PaymentStatus status;
    private final PaymentMethod method;
    private final long timestamp;

    public Payment(double amount) {
        this.paymentId = UUID.randomUUID();
        this.amount = amount;
        this.status = PaymentStatus.PENDING;
        this.method = PaymentMethod.CASH;
        this.timestamp = System.currentTimeMillis();
    }

    public Payment(double amount, PaymentMethod method) {
        this.paymentId = UUID.randomUUID();
        this.amount = amount;
        this.status = PaymentStatus.PENDING;
        this.method = method;
        this.timestamp = System.currentTimeMillis();
    }
    public String getPaymentId() {
        return paymentId.toString();
    }
    public double getAmount() {
        return amount;
    }
    public PaymentStatus getStatus() {
        return status;
    }
    public PaymentMethod getMethod() {
        return method;
    }
    public long getTimestamp() {
        return timestamp;
    } 
    public void setStatus(PaymentStatus status) {
        this.status = status;
    } 
}
