# Dependency Injection

`Modern / Arch` · `Good to Know Tier` · ✅ Built

A class receives its dependencies from the outside instead of constructing them itself, so it depends on an abstraction (an interface) rather than a concrete implementation it created.

Not a classic GoF pattern — it comes from Martin Fowler's *Inversion of Control Containers and the Dependency Injection pattern* (2004) — but at SDE3 level it gets asked more than half the GoF structural patterns, because testability is a core senior signal.

---

## Real-World Analogy

A restaurant kitchen doesn't grow its own vegetables — a supplier delivers them. If one supplier goes out of business, the kitchen swaps in another; the recipes (the kitchen's own logic) never change. The kitchen depends on "a supplier," not on any one specific farm.

## UML

```text
Client -- constructs --> ConcreteDependency
                              |
                              v (passed in, not built internally)
                         Service -- depends on --> Dependency (interface)
```

## Advantages vs Disadvantages

| ✅ Advantages | ❌ Disadvantages |
|---|---|
| A class is testable in isolation — pass in a mock/fake instead of the real dependency | Indirection: to see what a class actually does at runtime, you have to trace what was injected, not just read the class |
| Swapping an implementation (e.g. EmailSender → SmsSender) never touches the dependent class | Constructors can grow long if a class ends up with many dependencies — often a sign the class has too many responsibilities |
| Dependencies are visible in the constructor signature — no hidden `new X()` buried in a method body | DI frameworks (Spring, Guice) add real complexity and "magic" wiring that's hard to reason about from the code alone |

## How It Follows SOLID

- **Dependency Inversion** — this principle *is* what DI implements: high-level classes depend on abstractions (interfaces), not on low-level concrete classes; the concrete class is decided outside, at the composition root.
- **Single Responsibility** — a class no longer has to know *how* to construct its dependencies, only how to use them — construction and usage are two responsibilities pulled apart.
- **Open/Closed** — adding a new implementation of a dependency (a new `MessageSender`) never requires editing the class that uses it.

## When To Use vs Skip

| ✅ Use it when | ❌ Skip it when |
|---|---|
| A class needs to be unit-testable in isolation, with mocked collaborators | The dependency is a stateless, stable utility with no reason to ever be swapped or mocked (e.g. `Math`) |
| More than one implementation of a dependency exists, or might exist later | The extra indirection genuinely adds nothing — a tiny script with one obvious, permanent dependency |
| You're building a layered system (service → repository → data source) where each layer shouldn't know the layer below's concrete details | |

## Related Patterns

- **vs Factory** — Factory *creates* an object and hands back a concrete instance; DI is about *how that created object reaches the class that needs it* (as a constructor/setter argument, not built internally). A Factory is often what a DI container calls internally to produce the object it injects.
- **vs Repository** — different layers, commonly paired: Repository abstracts *data access*; DI is *how* a service receives its Repository (via its constructor) instead of constructing one itself. See this repo's `UserService`, which takes a `UserRepository` through DI.
- **vs Strategy** — structurally similar (both pass in an interface implementation from outside), but different intent: Strategy is about swapping an *algorithm/behavior* at runtime by design; DI is about supplying *any* collaborator — including a algorithm-holding Strategy — for testability and decoupling, often wired once at startup rather than swapped at runtime.

## Interview Line

> "I passed dependencies into the constructor instead of constructing them inside the class, so the class depends on an interface, not a concrete type — that's what let me swap in a mock in unit tests without touching the class itself."

---

## Implementations

Three variants live in `sample/`, run back to back in [`sample/Main.java`](sample/Main.java):

| | No DI (baseline) | Constructor Injection | Setter Injection |
|---|---|---|---|
| Who supplies the dependency | The class itself, via `new EmailSender()` | The caller, via the constructor | The caller, via a setter, after construction |
| Field can be `final`? | Yes, but it's hard-coded to one implementation | Yes | No |
| Testable with a mock? | No — always runs the real `EmailSender` | Yes | Yes |
| Risk of an unwired/half-built object | None — but also no flexibility | None — object is only ever fully wired | Yes — usable before `setSender()` is called; guarded here with an `IllegalStateException` |
| Fowler's recommendation | — | Preferred default for required dependencies | Reasonable for optional/reconfigurable dependencies |

#### No DI — the baseline
[FULL CODE](sample/OrderServiceNoDI.java)

```java
public class OrderServiceNoDI {
    private final MessageSender sender = new EmailSender();

    public void placeOrder(String item) {
        sender.send("Order placed for " + item);
    }
}
```

Hard-coupled to `EmailSender`. Switching to SMS, or unit-testing with a fake sender, means editing this class — exactly the coupling DI exists to remove.

#### Constructor Injection — the recommended default
[FULL CODE](sample/OrderServiceConstructorInjection.java)

```java
public class OrderServiceConstructorInjection {
    private final MessageSender sender;

    public OrderServiceConstructorInjection(MessageSender sender) {
        this.sender = sender;
    }

    public void placeOrder(String item) {
        sender.send("Order placed for " + item);
    }
}
```

`sender` is required, `final`, and set exactly once. Passing an `EmailSender`, `SmsSender`, or a test mock never requires touching this class — see [`sample/Main.java`](sample/Main.java) swap `EmailSender` for `SmsSender` at the call site only.

#### Setter Injection — for optional/reconfigurable dependencies
[FULL CODE](sample/OrderServiceSetterInjection.java)

```java
public class OrderServiceSetterInjection {
    private MessageSender sender;

    public void setSender(MessageSender sender) {
        this.sender = sender;
    }

    public void placeOrder(String item) {
        if (sender == null) {
            throw new IllegalStateException("MessageSender not set — call setSender() first");
        }
        sender.send("Order placed for " + item);
    }
}
```

Useful when the dependency is genuinely optional or needs to be reconfigured after the object already exists — the trade-off is a window where the object exists but isn't correctly wired yet.

---

Full breakdown — where this shows up in interviews, what's actually tested, and the common mistake — lives in the Dependency Injection row of the [root README](../../README.md).
