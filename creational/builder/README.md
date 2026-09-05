# Builder Pattern

`Creational` · `High Priority Tier` · ✅ Built

Separate the construction of a complex object from its representation, so the same step-by-step process can build objects with many optional fields without a telescoping constructor.

---

## Real-World Analogy

Ordering a custom pizza at the counter. You pick size, crust, cheese, toppings — each specified one step at a time — and only get the finished pizza back once every step is done. You never assemble it yourself; you just specify what you want, step by step.

## UML

```text
Client -- calls withX(), withY(), ... --> Builder -- build() --> fully-constructed object
```

## Advantages vs Disadvantages

| ✅ Advantages | ❌ Disadvantages |
|---|---|
| Avoids telescoping/overloaded constructors for objects with many optional fields | Extra class (and extra boilerplate) for what a plain constructor could sometimes handle |
| Reads clearly at the call site — named `setX()`/`withX()` calls instead of positional arguments | Overkill for objects with only 2–3 fields |
| Naturally supports immutable objects — all fields set before construction, no setters after | Classic GoF form (separate `Director`) adds even more classes than most real code actually needs |
| Validation can happen once in `build()`, instead of scattered across constructor overloads | |

## How It Follows SOLID

- **Single Responsibility** — object construction is pulled out of the object itself into a dedicated builder; the object's own class doesn't need to juggle multiple constructors or validate itself mid-construction.
- **Open/Closed** — adding a new optional field means adding one new `setX()`/`withX()` method to the builder; existing `build()` call sites elsewhere in the codebase are untouched.

## When To Use vs Skip

| ✅ Use it when | ❌ Skip it when |
|---|---|
| The object has many optional fields / config-heavy construction | The object has only 2–3 fields — a constructor is simpler and sufficient |
| The constructor would otherwise need many overloads (the "telescoping constructor" problem) | All fields are required, none optional — a builder adds nothing over a constructor |
| The object should be immutable once built | |

## Related Patterns

- **vs Factory** — Factory decides *what type* of object to create; Builder decides *how to assemble* one already-known object's optional details. Commonly paired: a factory method can hand back a pre-configured builder.
- **vs Prototype** — Prototype creates a new object by cloning an existing one; Builder creates a new object from scratch, step by step.
- **vs Abstract Factory** — Abstract Factory creates several separate, simpler objects that belong to the same family, one call each; Builder constructs one complex object step by step across many calls, and the object isn't usable until `build()` finishes.

## Interview Line

> "I used Builder to avoid a telescoping constructor for a Pizza with several optional fields — crust, cheese, toppings — and to guarantee the final object is only usable once every required field is actually set."

---

## Implementations

Four variants live in `sample/`, all building the same `Pizza`:

| | Classic (Director) | Fluent | Immutable | Step Builder |
|---|---|---|---|---|
| Who orchestrates construction | A dedicated `PizzaDirector`, in a fixed sequence | The client, via chained calls | The client, via chained calls | The client, via chained calls |
| Required-field enforcement | Structural — `PizzaDirector` always calls all 4 `buildX()` steps, so nothing can be skipped | Runtime check inside `build()` (`IllegalStateException`) | Runtime check inside `build()` (`IllegalStateException`) | Compile-time — wrong order simply won't compile |
| Values come from | Passed into `VegPizzaBuilder`'s constructor by the client | Passed into each chained `setX()`/`addX()` call by the client | Passed into each chained `setX()`/`addX()` call by the client | Passed into each chained `setX()`/`addX()` call by the client |
| Extra classes needed | `IPizzaBuilder` interface + `VegPizzaBuilder` + `PizzaDirector` | Just the builder class | Just the builder class | One interface per required step (`ISizeStep`, `ICrustStep`, `IOptionalStep`) |
| Common in | Classic textbook examples, GUI toolkits with swappable build sequences | Modern Java — Lombok's `@Builder`, `HttpRequest.Builder` | Modern Java — Lombok's `@Builder`, `HttpRequest.Builder` | Libraries that want invalid construction to be impossible, not just checked |

> **Note on Fluent vs Immutable:** as currently coded, these two are the same technique under two names — both use a mutable, reusable builder with a self-returning `setX()`/`addX()` chain, validate required fields inside `build()`, and hand back a `Pizza` whose own fields are already `final`. A build that actually earns a separate "Immutable Builder" label usually means the *builder itself* is single-use or produces a new builder instance per step, not just that the final object has `final` fields — worth keeping in mind if these two ever diverge on purpose.

See all four run in [`sample/classic/Main.java`](sample/classic/Main.java), [`sample/fluent/Main.java`](sample/fluent/Main.java), [`sample/immutable/Main.java`](sample/immutable/Main.java), and [`sample/step/Main.java`](sample/step/Main.java) — all 4 take their values (size, crust, cheese, toppings) as variables declared in `main()`, not as literals baked into the builder itself.

#### Classic (GoF, with Director)
[FULL CODE](sample/classic/VegPizzaBuilder.java) · [`IPizzaBuilder`](sample/classic/IPizzaBuilder.java) · [`PizzaDirector`](sample/classic/PizzaDirector.java) · [`Pizza`](sample/classic/Pizza.java)

```java
public interface IPizzaBuilder {
    void buildSize();
    void buildCrust();
    void buildCheese();
    void buildToppings();
    Pizza getPizza();
}

public class PizzaDirector {
    public Pizza construct(IPizzaBuilder builder) {
        builder.buildSize();
        builder.buildCrust();
        builder.buildCheese();
        builder.buildToppings();
        return builder.getPizza();
    }
}

public class VegPizzaBuilder implements IPizzaBuilder {
    private final Pizza pizza = new Pizza();
    private final String size;
    private final String crust;
    private final boolean cheese;
    private final List<String> toppings;

    public VegPizzaBuilder(String size, String crust, boolean cheese, List<String> toppings) {
        this.size = size;
        this.crust = crust;
        this.cheese = cheese;
        this.toppings = toppings;
    }

    @Override
    public void buildSize() { pizza.setSize(size); }
    // buildCrust(), buildCheese(), buildToppings() follow the same shape
}
```

The client (`Main`) hands a configured `VegPizzaBuilder` to `PizzaDirector` and never calls `buildSize()`/`buildCrust()`/etc. itself — `PizzaDirector` owns the fixed build sequence, `VegPizzaBuilder` owns the actual values. That split is the real GoF distinction: the Director can't get the *order* wrong, and — now that the values come from the constructor instead of being hardcoded — the client controls *what* gets built without touching either class.

#### Fluent Builder
[FULL CODE](sample/fluent/PizzaBuilder.java) · [`Pizza`](sample/fluent/Pizza.java)

```java
public class PizzaBuilder {
    private String size;
    private String crust;
    private boolean cheese;
    private List<String> toppings = new ArrayList<>();

    public PizzaBuilder setSize(String size) {
        this.size = size;
        return this;
    }

    public PizzaBuilder addTopping(String topping) {
        this.toppings.add(topping);
        return this;
    }

    public Pizza build() {
        if (size == null) throw new IllegalStateException("Size is required");
        if (crust == null) throw new IllegalStateException("Crust is required");
        return new Pizza(size, crust, cheese, new ArrayList<>(toppings));
    }
}
```

Self-returning `setX()`/`addX()` methods, chained directly by the caller, no separate `Director`. This is what most real-world Java code reaches for. The one gap: a forgotten required field only surfaces as an `IllegalStateException` at runtime, not at compile time.

#### Immutable Builder
[FULL CODE](sample/immutable/PizzaBuilder.java) · [`Pizza`](sample/immutable/Pizza.java)

Structurally identical to the Fluent variant above — same chained `setX()`/`addX()` calls, same `build()`-time validation, same defensive copy of the toppings list (`new ArrayList<>(toppings)`) so the returned `Pizza`'s list can't be mutated through a reference the builder still holds. Kept as a separate file in this repo; see the note in the table above on what would need to change for it to represent a genuinely different technique.

#### Step Builder — required fields enforced at compile time
[FULL CODE](sample/step/PizzaBuilder.java) · [`ISizeStep`](sample/step/ISizeStep.java) · [`ICrustStep`](sample/step/ICrustStep.java) · [`IOptionalStep`](sample/step/IOptionalStep.java)

```java
public interface ISizeStep {
    ICrustStep setSize(String size);
}

public interface ICrustStep {
    IOptionalStep setCrust(String crust);
}

public interface IOptionalStep {
    IOptionalStep addCheese();
    IOptionalStep addTopping(String topping);
    Pizza build();
}
```

`PizzaBuilder` implements all three interfaces, but each method's return type only exposes the next legal step. Calling `.setCrust()` before `.setSize()`, or `.build()` before both required fields are set, is a compile error — `cannot find symbol` — not a runtime `IllegalStateException` like the Fluent/Immutable variants.

---

Full breakdown — where this shows up in interviews, what's actually tested, and the common mistake — lives in the Builder row of the [root README](../../README.md).
