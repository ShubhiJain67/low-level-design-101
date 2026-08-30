package modern.dependencyinjection.sample.orderservice;

import modern.dependencyinjection.sample.IMessageSender;

/**
 * Constructor Injection — the recommended default (Fowler's IoC/DI article).
 *
 * The dependency is required and supplied at construction time, so:
 *  - the object is never in a half-wired state (no "forgot to call setSender()" bugs)
 *  - the field can be final — immutable once built
 *  - swapping EmailSender for SmsSender, or for a test mock, means passing a
 *    different argument at the call site — this class is never touched.
 */
public class OrderServiceConstructorInjection {
    private final IMessageSender sender;

    public OrderServiceConstructorInjection(IMessageSender sender) {
        this.sender = sender;
    }

    public void placeOrder(String item) {
        sender.send("Order placed for " + item);
    }
}
