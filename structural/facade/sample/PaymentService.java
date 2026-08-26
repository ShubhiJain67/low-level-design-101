package structural.facade.sample;

public class PaymentService {
    public boolean charge(String item, double amount) {
        System.out.println("Charging " + amount + " for " + item);
        return true;
    }
}
