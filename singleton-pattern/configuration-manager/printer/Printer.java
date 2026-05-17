package printer;

public class Printer {

    public static void print(String message) {
        Thread t = Thread.currentThread();
        System.out.printf("[%s] (id=%d) %s%n", t.getName(), t.threadId(), message);
    }
}
