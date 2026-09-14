package projects.parkinglot.payment;

/**
 * IPricingStrategy
 */
public interface IPaymentStrategy {
    abstract boolean pay(double amount);
}
