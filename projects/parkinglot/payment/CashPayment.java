package projects.parkinglot.payment;

public class CashPayment implements IPaymentStrategy{
    @Override 
    public boolean pay(double amount){
        System.err.println("Paid amount " + amount + " by Cash");
        return true;
    }
}
