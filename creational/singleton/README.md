# Singleton Pattern

`Creational` · `Master Tier` · ✅ Built

Ensure a class has exactly one instance, and provide a single, controlled global access point to it.

---

## Real-World Analogy

A country's central bank. There's exactly one — every transaction, policy, and rate decision routes through that same authority, not a separately-spun-up copy per department.

## UML

```text
Client -- getInstance() --> Singleton -- returns --> the one shared instance (created once, reused after)
```

## Advantages vs Disadvantages

| ✅ Advantages | ❌ Disadvantages |
|---|---|
| Guarantees exactly one instance exists app-wide | Global mutable state can hide dependencies and cause bugs |
| Centralized, controlled access point | Harder to unit test — hard to mock or reset between tests |
| Avoids re-creating an expensive object | Naive lazy versions aren't thread-safe without extra care |
| Lazy variants defer creation until actually needed | Can be broken via reflection, serialization, or cloning if not explicitly guarded |

## How It Follows SOLID

Singleton is the GoF pattern most often in *tension* with SOLID, not reinforcing it — worth stating that honestly rather than forcing a clean fit:

- **Single Responsibility — usually violated.** The class owns both its actual logic (e.g. loading config) and its own instantiation lifecycle (`getInstance()`, the locking strategy) — two responsibilities bundled into one class.
- **Dependency Inversion — usually violated too.** Callers reach `getInstance()` directly instead of receiving the instance through injection, so they depend on a concrete class, not an abstraction.
- This tension is itself the common interview follow-up: "isn't Singleton kind of anti-SOLID?" — yes, and knowing why is the actual signal, not defending it.

## When To Use vs Skip

| ✅ Use it when | ❌ Skip it when |
|---|---|
| Exactly one instance must exist app-wide (config, logger, connection pool) | You might need multiple instances later, even if it's "just one" today |
| Object creation is expensive and should happen once | A DI framework already manages the lifecycle for you |
| Shared state must stay consistent across the app | Global state would make unit tests fragile or hard to isolate |

## Related Patterns

- **vs Factory** — Factory decides *which class* to instantiate; Singleton restricts *how many instances* of one class can exist. Orthogonal concerns — a factory can itself be implemented as a Singleton.
- **vs static utility class** — Singleton is a real object: it can implement an interface, hold state, and be lazily constructed with dependencies. A static utility class can't do any of that.
- **vs Flyweight** — Singleton guarantees exactly ONE instance total; Flyweight allows MANY shared instances, one per distinct intrinsic-state key, reused across many logical objects. Both are about controlling instance creation, but Singleton caps the count at 1 and Flyweight caps it at "one per unique key."

## Interview Line

> "I used Singleton for the configuration manager so every part of the app reads from the same in-memory config, loaded exactly once, instead of every caller re-parsing config files."

---

## Implementations

Six known ways to build one, in increasing order of correctness/cost tradeoff:

| Implementation | Thread-safe? | Lazy? | Note |
|---|---|---|---|
| Eager | ✅ | ❌ | Instance created at class load, even if never used |
| Lazy (naive) | ❌ | ✅ | Breaks under concurrent first-call access — two threads can both see `null` |
| Synchronized method | ✅ | ✅ | Correct, but pays a lock on *every* call, not just the first |
| Double-Checked Locking | ✅ | ✅ | Needs `volatile` — without it, instruction reordering can expose a half-constructed instance |
| Bill Pugh (static holder) | ✅ | ✅ | JVM class-loading guarantees handle thread-safety — no explicit locking. Generally recommended. |
| Enum | ✅ | ❌ | JVM-guaranteed singleton, also blocks reflection/serialization attacks by construction — safest, but least flexible (can't extend a class) |

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

#### Sample Code
[FULL CODE](projects/configuration-manager/manager/ConfigurationManager.java)

The real project in this repo (`configuration-manager`) goes one step further than the bare pattern: it pairs the Bill Pugh holder with a `ReadWriteLock` so config reads can happen concurrently while a reload takes an exclusive write lock — the pattern guarantees *one instance*, the lock guarantees that instance is *safe to read/write concurrently*, which are two separate problems people often conflate.

---

## Common Interview Questions

- **Why a private constructor?** To prevent outside code from calling `new` directly.
- **Why a static instance/holder?** Because the single instance belongs at the class level, not tied to any one object.
- **Why `volatile` in double-checked locking?** Prevents instruction reordering from exposing a partially-constructed instance to another thread.
- **Can Singleton be broken?** Yes — reflection, deserialization, and cloning can all create a second instance unless explicitly guarded against (Enum Singleton is immune to all three by construction).
- **Best production choice?** Bill Pugh holder for most cases; Enum Singleton when you specifically need immunity to reflection/serialization attacks.

---

Full breakdown — where this shows up in interviews, what's actually tested, and the common mistake — lives in the Singleton row of the [root README](../../README.md).
