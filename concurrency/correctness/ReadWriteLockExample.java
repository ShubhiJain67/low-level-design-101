package concurrency.correctness;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockExample {
    private static String product = "Apple";
    private static final ReadWriteLock lock = new ReentrantReadWriteLock();

    private static String getProduct() {
        lock.readLock().lock();
        try {
            return product;
        } finally {
            lock.readLock().unlock();
        }
    }

    private static void updateProduct(String p) {
        lock.writeLock().lock();
        try {
            System.err.println("Updating Product name from " + product + " to " + p + " via " + Thread.currentThread().getName());
            product = p;
            System.err.println("Updated Product name to " + p + " via " + Thread.currentThread().getName());
        } finally {
            lock.writeLock().unlock();
        }
    }

    public static void main(String[] args) {
        Thread reader1 = new Thread(() -> System.err.println(getProduct() + " via " + Thread.currentThread().getName()), "reader1");
        Thread writer1 = new Thread(() -> updateProduct("Banana"), "writer1");
        Thread reader2 = new Thread(() -> System.err.println(getProduct() + " via " + Thread.currentThread().getName()), "reader2");
        Thread writer2 = new Thread(() -> updateProduct("Kiwi"), "writer2");
        Thread reader3 = new Thread(() -> System.err.println(getProduct() + " via " + Thread.currentThread().getName()), "reader3");

        reader1.start();
        writer1.start();
        reader2.start();
        writer2.start();
        reader3.start();
    }
}
