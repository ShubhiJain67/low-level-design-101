package modern.dependencyinjection.sample.orderservice;

import modern.dependencyinjection.sample.IMessageSender;
import modern.dependencyinjection.sample.MessageSenders.EmailSender;

/**
 * No DI — the service constructs its own dependency internally.
 *
 * Problems this creates:
 *  - OrderServiceNoDI is now hard-coupled to EmailSender specifically.
 *  - Switching to SmsSender means editing this class.
 *  - Unit testing this class means a real EmailSender always runs —
 *    there's no way to substitute a mock/fake without changing this file.
 */
public class OrderServiceNoDI {
    private final IMessageSender sender = new EmailSender();

    public void placeOrder(String item) {
        sender.send("Order placed for " + item);
    }
}
