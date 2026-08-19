package creational.singleton.sample;

public class Main {

    public static void main(String[] args) {
        // Each check proves the same thing: two calls to getInstance()
        // return the identical object (== is reference equality, not
        // .equals()) — that's the whole contract Singleton promises.

        EagerSingleton eager1 = EagerSingleton.getInstance();
        EagerSingleton eager2 = EagerSingleton.getInstance();
        System.out.println("Eager singleton test: " + (eager1 == eager2));

        LazySingleton lazy1 = LazySingleton.getInstance();
        LazySingleton lazy2 = LazySingleton.getInstance();
        System.out.println("Lazy singleton test: " + (lazy1 == lazy2));

        SynchronizedSingleton sync1 = SynchronizedSingleton.getInstance();
        SynchronizedSingleton sync2 = SynchronizedSingleton.getInstance();
        System.out.println("Synchronized singleton test: " + (sync1 == sync2));

        DoubleCheckedLockingSingleton dcl1 = DoubleCheckedLockingSingleton.getInstance();
        DoubleCheckedLockingSingleton dcl2 = DoubleCheckedLockingSingleton.getInstance();
        System.out.println("Double-checked locking singleton test: " + (dcl1 == dcl2));

        BillPughSingleton billPugh1 = BillPughSingleton.getInstance();
        BillPughSingleton billPugh2 = BillPughSingleton.getInstance();
        System.out.println("Bill Pugh singleton test: " + (billPugh1 == billPugh2));

        EnumSingleton enum1 = EnumSingleton.INSTANCE;
        EnumSingleton enum2 = EnumSingleton.INSTANCE;
        System.out.println("Enum singleton test: " + (enum1 == enum2));
        enum1.doWork();
    }
}
