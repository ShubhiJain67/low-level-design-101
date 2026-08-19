package creational.singleton.sample;

// 5. Bill Pugh (static holder) — the Holder class only loads, and only
// then constructs INSTANCE, the first time getInstance() is called. JVM
// class-loading guarantees do the thread-safety work, with zero explicit
// locking. Generally the recommended default. (The real project in this
// repo, configuration-manager, uses this exact technique.)
public class BillPughSingleton {

    private BillPughSingleton() {}

    // A class isn't loaded and initialized until something actually references it
    private static class Holder {
        private static final BillPughSingleton INSTANCE = new BillPughSingleton();
    }

    public static BillPughSingleton getInstance() {
        return Holder.INSTANCE;
    }
}
