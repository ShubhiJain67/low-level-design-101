package concurrency.correctness;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicVariablesP2 {
    private final static AtomicInteger accountA = new AtomicInteger(10);
    private final static AtomicInteger accountB = new AtomicInteger(20);

    private static void decrement(String accountName) {
        if("a".equals(accountName)) {
            accountA.getAndDecrement();
        } else {
            accountB.getAndDecrement();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                decrement("a");
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                decrement("b");
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.err.println("Expected Value of account A 5");
        System.err.println("Actual Value of account A " + accountA);
        System.err.println("Expected Value of account B 15");
        System.err.println("Actual Value of account B " + accountB);
    }
       
}
