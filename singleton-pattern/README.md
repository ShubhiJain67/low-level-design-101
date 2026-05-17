# Singleton Design Pattern

## Problem Statement

In many applications, only one instance of a class should exist throughout the entire application lifecycle.

Examples:
- Database connection manager
- Cache manager
- Logger
- Configuration manager
- Thread pool manager

If multiple objects are created accidentally:
- memory may be wasted
- inconsistent state may occur
- resource conflicts may happen

Singleton Pattern ensures:
- exactly one object exists
- global access to that object is provided

---

# Why This Pattern Is Needed

Singleton Pattern is needed when:
- only one shared instance should exist
- centralized control is required
- shared resources must be coordinated
- object creation is expensive
- global access is beneficial

Without Singleton:
- multiple instances may create inconsistent behavior
- resource-heavy objects may consume unnecessary memory

---

# Definition

Singleton Pattern is a creational design pattern that ensures a class has only one instance and provides a global access point to it.

---

# UML / Flow

```text
Client
   ↓
Singleton Class
   ↓
Single Shared Instance
```

The constructor is hidden from outside classes.

Object creation is controlled internally.

---

# Real-World Analogy

Think of a government passport office.

There is only one official authority managing passport issuance for a region.

Everyone accesses the same centralized authority instead of creating separate independent authorities.

---

# Key Characteristics

- private constructor
- static instance variable
- public static access method
- single shared object

---

# Java Implementation

## Eager Initialization Singleton

```java
public class Singleton {

    private static final Singleton INSTANCE =
            new Singleton();

    private Singleton() {}

    public static Singleton getInstance() {
        return INSTANCE;
    }
}
```

---

## Lazy Initialization Singleton

Object created only when needed.

```java
public class Singleton {

    private static Singleton instance;

    private Singleton() {}

    public static Singleton getInstance() {

        if(instance == null) {
            instance = new Singleton();
        }

        return instance;
    }
}
```

---

## Thread-Safe Singleton

```java
public class Singleton {

    private static Singleton instance;

    private Singleton() {}

    public static synchronized Singleton
    getInstance() {

        if(instance == null) {
            instance = new Singleton();
        }

        return instance;
    }
}
```

---

## Double Checked Locking

```java
public class Singleton {

    private static volatile Singleton instance;

    private Singleton() {}

    public static Singleton getInstance() {

        if(instance == null) {

            synchronized (Singleton.class) {

                if(instance == null) {
                    instance = new Singleton();
                }
            }
        }

        return instance;
    }
}
```

---

## Bill Pugh Singleton (Recommended)

```java
public class Singleton {

    private Singleton() {}

    private static class Holder {

        private static final Singleton INSTANCE =
                new Singleton();
    }

    public static Singleton getInstance() {
        return Holder.INSTANCE;
    }
}
```

---

## Enum Singleton (Safest)

```java
public enum Singleton {

    INSTANCE;
}
```

---

# Output

```text
Both references point to same object instance.
```

---

# Advantages

## 1. Controlled Access

Only one instance exists.

---

## 2. Memory Efficient

Avoids duplicate objects.

---

## 3. Shared Global State

Useful for shared resources.

---

## 4. Lazy Initialization Possible

Object created only when required.

---

## 5. Centralized Management

Single access point simplifies coordination.

---

# Disadvantages

## 1. Global State Can Be Dangerous

Shared mutable state may cause bugs.

---

## 2. Harder Unit Testing

Singletons can create hidden dependencies.

---

## 3. Thread Safety Complexity

Improper implementation may create multiple instances.

---

## 4. Violates Single Responsibility Principle Sometimes

Class may manage both:
- business logic
- lifecycle management

---

# When To Use

Use Singleton when:
- exactly one instance should exist
- shared resources need centralized access
- object creation is expensive
- configuration/state should remain consistent

---

# When NOT To Use

Avoid Singleton when:
- multiple instances may be needed later
- global state becomes risky
- object lifecycle should remain flexible
- dependency injection frameworks already manage lifecycle

---

# Real-World Use Cases

- Logger
- Cache Manager
- Database Connection Pool
- Configuration Manager
- Thread Pool Manager
- Payment Gateway Manager
- Application Context

---

# Singleton in Cache System

Very common usage:

```text
CacheManager.getInstance()
```

Ensures:
- one centralized cache manager
- shared cache state across application

---

# Singleton in Payment Gateway

Useful for:
- transaction manager
- gateway manager
- configuration manager

---

# Interview Tips

## Key Statement

> “Singleton Pattern ensures a class has only one shared instance with global controlled access.”

---

## Most Important Concepts To Mention

- private constructor
- static instance
- thread safety
- lazy initialization
- synchronization

---

## Best Implementation To Mention

For interviews:
- Bill Pugh Singleton
- Double Checked Locking

These demonstrate advanced understanding.

---

## Common Interview Questions

### Why private constructor?

To prevent outside object creation.

---

### Why static instance?

Because object belongs to class level, not object level.

---

### Why volatile keyword?

Prevents instruction reordering issues in multithreading.

---

### Can Singleton be broken?

Yes:
- reflection
- serialization
- cloning

---

### Best way to create Singleton in Java?

Enum Singleton is safest production-level implementation.

---

# Pros vs Cons Summary

| Pros | Cons |
|---|---|
| Single instance | Global state risks |
| Memory efficient | Harder testing |
| Shared access | Hidden dependencies |
| Centralized management | Thread safety complexity |

---

# Strong Interview Line

> “I used Singleton Pattern for centralized shared resource management while ensuring controlled instance creation.”