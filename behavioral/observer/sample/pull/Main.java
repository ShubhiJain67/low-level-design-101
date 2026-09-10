package behavioral.observer.sample.pull;

public class Main {
    public static void main(String[] args) {
        Stock itc = new Stock("ITC");
        Stock rbm = new Stock("RBM Infra");

        IObserver shubhi = new User("Shubhi");
        rbm.watch(shubhi);
        itc.watch(shubhi);


        IObserver grow = new TradingApp("Grow APP");
        rbm.watch(grow);
        itc.watch(grow);

        rbm.updateDetails(20);
        
        itc.updateDetails(30);
        itc.updateDetails(20);
    }
}
