package modern.dependencyinjection.sample;

import modern.dependencyinjection.sample.MessageSenders.EmailSender;
import modern.dependencyinjection.sample.MessageSenders.SmsSender;
import modern.dependencyinjection.sample.orderservice.OrderServiceConstructorInjection;
import modern.dependencyinjection.sample.orderservice.OrderServiceNoDI;
import modern.dependencyinjection.sample.orderservice.OrderServiceSetterInjection;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- No DI (hard-coupled to EmailSender) ---");
        OrderServiceNoDI noDI = new OrderServiceNoDI();
        noDI.placeOrder("Keyboard");

        System.out.println("\n--- Constructor Injection ---");
        OrderServiceConstructorInjection viaEmail =
                new OrderServiceConstructorInjection(new EmailSender());
        viaEmail.placeOrder("Mouse");

        // Swap the dependency for a completely different implementation —
        // OrderServiceConstructorInjection itself is never touched.
        OrderServiceConstructorInjection viaSms =
                new OrderServiceConstructorInjection(new SmsSender());
        viaSms.placeOrder("Monitor");

        System.out.println("\n--- Setter Injection ---");
        OrderServiceSetterInjection setterBased = new OrderServiceSetterInjection();
        setterBased.setSender(new SmsSender());
        setterBased.placeOrder("Webcam");
    }
}
