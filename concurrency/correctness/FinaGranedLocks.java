package concurrency.correctness;

public class FinaGranedLocks {
    private static int accountA = 10;
    private static int accountB = 20;

    private static final Object lockA = new Object();
    private static final Object lockB = new Object();

    private static void decrement(String accountName) {
        if("a".equals(accountName)) {
            synchronized(lockA) {
                accountA--;
            }
        } else {
            synchronized(lockB) {
                accountB--;
            }
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
