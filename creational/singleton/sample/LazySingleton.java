package creational.singleton.sample;

// 2. Lazy Initialization (naive) — defers creation to the first call, but
// broken under concurrency: two threads can both pass the null check
// before either finishes constructing, producing two instances.
// Included for completeness / to show what NOT to ship — not thread-safe.

public class LazySingleton {
    private static LazySingleton INSTANCE;

    private LazySingleton() {}

    public static LazySingleton getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LazySingleton();
        }
        return INSTANCE;
    }
}