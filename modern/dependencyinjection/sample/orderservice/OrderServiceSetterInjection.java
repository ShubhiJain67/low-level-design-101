package modern.dependencyinjection.sample.orderservice;

import modern.dependencyinjection.sample.IMessageSender;

/**
 * Setter Injection — the dependency is supplied after construction via a setter.
 *
 * Trade-off vs Constructor Injection:
 *  - useful when the dependency is optional, or needs to be reconfigured/swapped
 *    after the object already exists (e.g. a framework wiring a bean, then
 *    re-wiring it later)
 *  - the field can't be final, and the object is technically usable — but
 *    incorrectly wired — for a window before setSender() is called.
 */
public class OrderServiceSetterInjection {
    private IMessageSender sender;

    public void setSender(IMessageSender sender) {
        this.sender = sender;
    }

    public void placeOrder(String item) {
        if (sender == null) {
            throw new IllegalStateException("MessageSender not set — call setSender() first");
        }
        sender.send("Order placed for " + item);
    }
}
