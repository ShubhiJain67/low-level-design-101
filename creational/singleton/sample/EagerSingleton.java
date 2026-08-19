package creational.singleton.sample;

// 1. Eager Initialization — instance built at class-load time, before
// anyone asks for it. Thread-safe by default (JVM guarantees class init
// happens exactly once), but pays the construction cost even if
// getInstance() is never called.

public class EagerSingleton{
    private static final EagerSingleton INSTANCE = new EagerSingleton();

    private EagerSingleton() {}

    public static EagerSingleton getInstance() {
        return INSTANCE;
    }
}