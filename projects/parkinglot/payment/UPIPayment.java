package projects.parkinglot.payment;

public class UPIPayment implements IPaymentStrategy{
    @Override 
    public boolean pay(double amount){
        System.err.println("Paid amount " + amount + " by UPI");
        return true;
    }
}
