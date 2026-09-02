# Singleton Pattern

`Creational` · `Master Tier` ·

The Singleton Design Pattern ensures that a class has only one instance and provides a global access point to it. 

## Real-World Analogy

There's one principal making decisions for the whole school — you don't create a new principal every time a student needs something.

## When To Use vs Skip

| ✅ Use it when |
|---|
| Exactly one instance must exist app-wide (config, logger, connection pool) |
| Object creation is expensive and should happen once |
| Shared state must stay consistent across the app |

---

## Implementations

Six known ways to build:


### 1. Eager Initialization

Instance built at class-load time, before anyone asks for it. Simple, thread-safe by default (JVM guarantees class init happens once) — but pays the construction cost even if `getInstance()` is never called.

```java
public class Singleton {
    private static final Singleton INSTANCE = new Singleton();
    private Singleton() {}
    public static Singleton getInstance() { return INSTANCE; }
}
```

### 2. Lazy Initialization (naive)

Defers creation to first call — but broken under concurrency: two threads can both pass the `null` check before either finishes constructing, producing two instances.

```java
public class Singleton {
    private static Singleton instance;
    private Singleton() {}
    public static Singleton getInstance() {
        if (instance == null) instance = new Singleton();
        return instance;
    }
}
```

### 3. Synchronized Method

Fixes the race by locking the whole method — correct, but every single call pays the lock cost, not just the first one that actually creates the instance.

```java
public class Singleton {
    private static Singleton instance;
    private Singleton() {}
    public static synchronized Singleton getInstance() {
        if (instance == null) instance = new Singleton();
        return instance;
    }
}
```

### 4. Double-Checked Locking

Only locks on the first call (checks `null` once outside the lock, once inside). Needs `volatile` — without it, instruction reordering can let another thread see a half-constructed object.

```java
public class Singleton {
    private static volatile Singleton instance;
    private Singleton() {}
    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) instance = new Singleton();
            }
        }
        return instance;
    }
}
```

### 5. Bill Pugh (static holder) — what this repo actually uses

The `Holder` class only loads — and only then constructs `INSTANCE` — the first time `getInstance()` is called. JVM class-loading guarantees do the thread-safety work, with zero explicit locking. Generally the recommended default.

```java
public final class ConfigurationManager {
    private ConfigurationManager() {
        // real init happens here
    }
    private static class Holder {
        private static final ConfigurationManager INSTANCE = new ConfigurationManager();
    }
    public static ConfigurationManager getInstance() { return Holder.INSTANCE; }
}
```

### 6. Enum Singleton — safest, least flexible

JVM-guaranteed single instance, and immune to reflection and serialization attacks by construction (the other five variants all need extra guarding to resist those). Trade-off: an enum can't extend another class.

```java
public enum Singleton {
    INSTANCE;
}
```

#### Difference between each type
| Implementation | Thread-safe? | Lazy? | Note |
|---|---|---|---|
| Eager | ✅ | ❌ | Instance created at class load, even if never used |
| Lazy (naive) | ❌ | ✅ | Breaks under concurrent first-call access — two threads can both see `null` |
| Synchronized method | ✅ | ✅ | Correct, but pays a lock on *every* call, not just the first |
| Double-Checked Locking | ✅ | ✅ | Needs `volatile` — without it, instruction reordering can expose a half-constructed instance |
| Bill Pugh (static holder) | ✅ | ✅ | JVM class-loading guarantees handle thread-safety — no explicit locking. Generally recommended. |
| Enum | ✅ | ❌ | JVM-guaranteed singleton, also blocks reflection/serialization attacks by construction — safest, but least flexible (can't extend a class) |


## How It Follows SOLID

Singleton is the GoF pattern most often in *tension* with SOLID, not reinforcing it — worth stating that honestly rather than forcing a clean fit:

- **Single Responsibility — usually violated.** The class owns both its actual logic (e.g. loading config) and its own instantiation lifecycle (`getInstance()`, the locking strategy) — two responsibilities bundled into one class.
- **Dependency Inversion — usually violated too.** Callers reach `getInstance()` directly instead of receiving the instance through injection, so they depend on a concrete class, not an abstraction.
- This tension is itself the common interview follow-up: "isn't Singleton kind of anti-SOLID?" — yes, and knowing why is the actual signal, not defending it.

## Advantages vs Disadvantages

| ✅ Advantages | ❌ Disadvantages |
|---|---|
| Guarantees exactly one instance exists app-wide | Global mutable state can hide dependencies and cause bugs |
| Centralized, controlled access point | Harder to unit test — hard to mock or reset between tests |
| Avoids re-creating an expensive object | Naive lazy versions aren't thread-safe without extra care |
| Lazy variants defer creation until actually needed | Can be broken via reflection, serialization, or cloning if not explicitly guarded |

## Interview Line

> "I used Singleton for the configuration manager so every part of the app reads from the same in-memory config, loaded exactly once, instead of every caller re-parsing config files."


---

