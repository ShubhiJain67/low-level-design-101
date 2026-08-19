package creational.singleton.sample;

// 4. Double-Checked Locking — only locks on the first call (checks null
// once outside the lock, once inside). 
// `volatile` is required: without it, instruction reordering can let 
// another thread see a half-constructed object.

public class DoubleCheckedLockingSingleton {

    // Volatile ensures that multiple threads handle the INSTANCE variable correctly
    // Without volatile, it's possible for one thread to see a partially constructed instance of the singleton.
    // What volatile does NOT prevent: two threads both deciding to build the object
    private static volatile DoubleCheckedLockingSingleton INSTANCE;

    private DoubleCheckedLockingSingleton() {}

    public static DoubleCheckedLockingSingleton getInstance() {
        if (INSTANCE == null) {
            // What synchronized adds: only one thread at a time can even be inside that block
            synchronized (DoubleCheckedLockingSingleton.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DoubleCheckedLockingSingleton();
                }
            }
        }
        return INSTANCE;
    }
}
