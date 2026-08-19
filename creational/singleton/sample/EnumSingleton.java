package creational.singleton.sample;

// 6. Enum Singleton — JVM-guaranteed single instance, and immune to
// reflection and serialization attacks by construction (every other
// variant here needs extra guarding to resist those). 

// Trade-off: an enum can't extend another class.
public enum EnumSingleton {
    INSTANCE;

    public void doWork() {
        System.out.println("Working from " + this);
    }
}
