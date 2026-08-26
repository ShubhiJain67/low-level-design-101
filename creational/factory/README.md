# Factory Pattern

`Creational` · `Master Tier` · ✅ Built

Centralize object creation behind a factory so the client depends on an abstraction, not on scattered `new ConcreteClass()` calls throughout the codebase.

---

## Real-World Analogy

A restaurant kitchen — you order "pasta," not how it's made. The kitchen decides ingredients, prep, and assembly; you only ever see the finished plate.

## UML

```text
Client -- asks --> Factory -- creates --> ConcreteImpl (returned as Interface)
```

## Advantages vs Disadvantages

| ✅ Advantages | ❌ Disadvantages |
|---|---|
| Client depends on an abstraction, not concrete classes | Adds a layer of indirection for very simple cases |
| Creation logic lives in one place | Basic (Simple Factory) form still needs editing per new type |
| New implementations added with minimal client-side change | Factory class can grow large if too many types pile in |
| Keeps client code clean and focused on business logic | |

## How It Follows SOLID

- **Dependency Inversion** — client depends on the returned interface/abstract type, never on the concrete class being instantiated.
- **Open/Closed** — only true once the factory is registry-based (see the registry-based implementation under Subtypes below); a plain switch-case factory still violates this on every new type.
- **Single Responsibility** — object construction is pulled out of the client entirely, into one class whose only job is creation.

## When To Use vs Skip

| ✅ Use it when | ❌ Skip it when |
|---|---|
| Multiple related implementations exist | Only one implementation exists |
| Creation logic is non-trivial | Creation is a bare `new X()` with no branching |
| Client shouldn't know concrete types | The abstraction would add complexity for nothing |

## Related Patterns

- **vs Abstract Factory** — Factory creates one product type; Abstract Factory creates whole families of related products that must stay consistent together.
- **vs Strategy** — Factory decides *what object to create*, Strategy decides *how an object behaves*. Commonly paired: a factory hands back the right Strategy implementation.
- **vs Singleton** — orthogonal concerns, easy to conflate: Singleton restricts *how many instances* of one class can exist; Factory decides *which class* to instantiate. A factory can itself be implemented as a Singleton, and neither pattern implies the other.

## Interview Line

> "I used Factory Pattern to centralize object creation and decouple client code from concrete implementations."

---

## Subtypes

Classic Factory Method uses subclassing — an abstract `Creator` with an abstract `factoryMethod()`, one `ConcreteCreator` per product. The **Parameterized Factory Method** below is a different variant: one method, one parameter, logic decides which product to return, instead of one subclass per product. ("Simple Factory" is the same technique under another name — not a separate pattern.) The two headings below are two *implementation choices* for it — switch vs. registry — not two subtypes.

| | Switch-based | Registry-based |
|---|---|---|
| Lookup mechanism | if-else / switch statement | `Map<Type, Supplier<T>>` |
| Add a new type | Edit the factory method | Call `register()` — factory method untouched |
| Open/Closed Principle | Violated — every new type edits existing code | Held — new type is additive |
| Setup cost | None — works immediately | Registry must be populated (bootstrap step) before first use |
| Failure mode | Compiler catches an unhandled case if using a switch expression | Unregistered type fails at runtime (`IllegalArgumentException`), not compile time |
| Best for | Small, fixed set of types that rarely change | Growing set of types, or when OCP is explicitly being tested |

### Parameterized Factory Method — switch/if-else implementation

What most people mean when they say "Simple Factory." One class, one static method, an if-else/switch decides which concrete class to return.

- **Advantage:** one place to change when creation logic changes; client never touches concrete classes.
- **Disadvantage:** violates Open/Closed — every new type means editing the factory's switch, not adding a class.
- **When to use:** small, fixed set of related types, trivial creation logic, no need for runtime extensibility.
- **Commonly used in:** shape/notification creators, vehicle creation in Parking Lot problems, a first-pass payment-method creator before Strategy is layered in.


#### Sample Code 
[FULL CODE](../factory/sample/simple/Main.java)

```java
interface Shape {
    void draw();
}

class Circle implements Shape {
    public void draw() { System.out.println("Drawing Circle"); }
}

class Square implements Shape {
    public void draw() { System.out.println("Drawing Square"); }
}

class ShapeFactory {
    public static Shape create(String type) {
        return switch (type) {
            case "CIRCLE" -> new Circle();
            case "SQUARE" -> new Square();
            default -> throw new IllegalArgumentException("Unknown type: " + type);
        };
    }
}
```

### Parameterized Factory Method — registry-based implementation

Same technique, modern implementation: a `Map<Type, Supplier<T>>` instead of a switch, so adding a new type means *registering* it, not editing the factory. Fixes the switch version's OCP violation while solving the exact same problem.

- **Advantage:** genuinely open/closed — new types are added via `register()`, existing code untouched.
- **Disadvantage:** the registry has to be populated somewhere (a bootstrap/registration step) before first use — slightly more setup than the switch version.
- **When to use:** the set of types grows over time, or you actually need OCP to hold (interviewer explicitly probes "what if we add a new type without touching this class").
- **Commonly used in:** plugin/module registries, payment gateway selection by method + region, notification channel dispatch — matches `factory/FPaymentStrategy.java` and `factory/FPaymentRepository.java` in `payment-gateway-system` in this repo.


#### Sample Code 
[FULL CODE](../factory/sample/parameterized/Main.java)

```java
class ShapeFactory {
    // Function<IShapeConfig, IShape> — takes a config, returns a shape.
    // (Function<T, R> = takes a T, returns an R — this was backwards before.)
    private static final Map<ShapeType, Function<IShapeConfig, IShape>> STORE_MAP = new HashMap<>();

    public static IShape getShape(ShapeType type, IShapeConfig config) {
        Function<IShapeConfig, IShape> creator = STORE_MAP.get(type);
        if (creator == null) {
            throw new IllegalArgumentException("Unknown type: " + type);
        }
        return creator.apply(config);
    }

    // open for extension — no existing code touched to add a type
    public static void register(ShapeType type, Function<IShapeConfig, IShape> creator) {
        STORE_MAP.put(type, creator);
    }
}
```

---

Full breakdown — where this shows up in interviews, what's actually tested, and the common mistake — lives in the Factory row of the [root README](../../README.md).
