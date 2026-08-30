package modern.dependencyinjection.sample.MessageSenders;

import modern.dependencyinjection.sample.IMessageSender;

public class SmsSender implements IMessageSender {
    @Override
    public void send(String message) {
        System.out.println("[SMS] " + message);
    }
}
