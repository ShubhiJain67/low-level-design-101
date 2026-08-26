package structural.facade.sample;

// One entry point coordinating 3 subsystem classes the client would otherwise have to call itself.
public class OrderFacade {
    private final InventoryService inventory = new InventoryService();
    private final PaymentService payment = new PaymentService();
    private final ShippingService shipping = new ShippingService();

    public void placeOrder(String item, double amount) {
        if (!inventory.checkStock(item)) {
            System.out.println("Out of stock: " + item);
            return;
        }
        if (!payment.charge(item, amount)) {
            System.out.println("Payment failed for " + item);
            return;
        }
        shipping.ship(item);
        System.out.println("Order placed for " + item);
    }
}
