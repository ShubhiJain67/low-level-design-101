# Decorator Pattern

`Structural` · `Master Tier` · ✅ Built

Attach additional responsibilities to an object dynamically. Decorators provide a flexible alternative to subclassing for extending functionality.

---

## Real-World Analogy

Ordering a coffee. You start with a plain coffee, wrap it with milk, wrap that with a shot of caramel, wrap that with whipped cream — each wrapper adds something on top, and the result still "is a" coffee at every stage, just with layered-on extras. You could stop wrapping at any layer and still have a fully valid coffee.

## UML

```text
Component (interface)
  |-- ConcreteComponent implements Component
  \-- Decorator implements Component (holds a wrapped Component)
        \-- ConcreteDecoratorA / ConcreteDecoratorB extend Decorator, add behavior, delegate the rest
```

## Advantages vs Disadvantages

| ✅ Advantages | ❌ Disadvantages |
|---|---|
| Adds responsibilities to ONE object at runtime, without touching the object's class or affecting other instances of it | Can result in a large number of small classes that are hard to keep track of |
| Avoids a subclass explosion — each feature is its own decorator that can be composed, instead of one subclass per combination | Decorators stacked in different orders can produce different results — order-dependence is a subtle source of bugs |
| Decorators are transparent to the client — a decorated object still satisfies the same interface as the undecorated one | A decorated object is not identical to the object it wraps — code relying on object identity can break unexpectedly |

## How It Follows SOLID

- **Open/Closed** — new behavior is added via a new Decorator class, without modifying the ConcreteComponent or any existing decorators.
- **Single Responsibility** — each decorator adds exactly one responsibility, instead of one class trying to handle every combination of features.

## When To Use vs Skip

| ✅ Use it when | ❌ Skip it when |
|---|---|
| You need to add responsibilities to individual objects, dynamically and transparently, without affecting other objects of the same class | You're subclassing for every combination anyway instead of composing — that's the exact anti-pattern this exists to avoid |
| Subclassing would produce too many subclasses to cover every combination of optional features | The set of extensions is small and fixed — a couple of subclasses may be simpler than the decorator machinery |
| The added responsibility should be removable later, not permanent like subclassing | |

## Related Patterns

- **vs Composite** — Decorator wraps a SINGLE component to add behavior in a chain; Composite builds a tree of many components treated uniformly. Structurally similar (both use composition + a shared interface), different intent.
- **vs Proxy** — both wrap another object behind the same interface, but Decorator ADDS behavior/responsibility, while Proxy CONTROLS access (lazy-loading, permission checks, caching) without usually changing what the object does.

## Interview Line

> "I used Decorator to add behavior to a single object at runtime, without subclassing — each decorator wraps the same interface and adds one responsibility, so they can be composed in any combination instead of needing a subclass per combination."

---

## Implementations

`MilkDecorator` and `CaramelDecorator` both wrap a `Coffee` and are themselves a `Coffee` — so they stack: `new CaramelDecorator(new MilkDecorator(coffee))` builds up cost and description one layer at a time. See all three stages run in [`sample/Main.java`](sample/Main.java).

[FULL CODE](sample/CoffeeDecorator.java) · [`Coffee` interface](sample/Coffee.java) · [`MilkDecorator`](sample/MilkDecorator.java) · [`CaramelDecorator`](sample/CaramelDecorator.java)

```java
public abstract class CoffeeDecorator implements Coffee {
    protected final Coffee wrapped;

    protected CoffeeDecorator(Coffee wrapped) {
        this.wrapped = wrapped;
    }
}

public class MilkDecorator extends CoffeeDecorator {
    public MilkDecorator(Coffee wrapped) {
        super(wrapped);
    }

    @Override
    public double cost() {
        return wrapped.cost() + 10;
    }
}
```

Each decorator only adds its own piece (`+ 10` for milk, `+ 15` for caramel) and delegates the rest to `wrapped` — no decorator needs to know how many layers are underneath it.

---

Full breakdown — where this shows up in interviews, what's actually tested, and the common mistake — lives in the Decorator row of the [root README](../../README.md).
