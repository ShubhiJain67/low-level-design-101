package modern.dependencyinjection.sample.MessageSenders;

import modern.dependencyinjection.sample.IMessageSender;

public class EmailSender implements IMessageSender {
    @Override
    public void send(String message) {
        System.out.println("[Email] " + message);
    }
}
