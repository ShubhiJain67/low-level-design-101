package structural.facade.sample;

public class Main {
    public static void main(String[] args) {
        OrderFacade orderFacade = new OrderFacade();
        orderFacade.placeOrder("Headphones", 1999.0);
    }
}
