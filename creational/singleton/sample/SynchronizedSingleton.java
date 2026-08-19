package creational.singleton.sample;

// 3. Synchronized Method — fixes LazySingleton's race by locking the whole
// method. Correct, but every single call pays the lock cost, not just the
// first one that actually creates the instance.

public class SynchronizedSingleton {
    private static SynchronizedSingleton INSTANCE;

    private SynchronizedSingleton() {}

    public static synchronized SynchronizedSingleton getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SynchronizedSingleton();
        }
        return INSTANCE;
    }
}