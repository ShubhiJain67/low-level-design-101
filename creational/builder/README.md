# Builder Pattern

`Creational` · `High Priority Tier` · ✅ Built

Separate the construction of a complex object from its representation, so the same step-by-step process can build objects with many optional fields without a telescoping constructor.

---

## Real-World Analogy

Ordering a custom burger. You choose bread, cheese, sauces, toppings — each optional, added one at a time — and only get the finished burger back at the end. You never assemble it yourself; you just specify what you want, step by step.

## UML

```text
Client -- calls withX(), withY(), ... --> Builder -- build() --> fully-constructed, immutable object
```

## Advantages vs Disadvantages

| ✅ Advantages | ❌ Disadvantages |
|---|---|
| Avoids telescoping/overloaded constructors for objects with many optional fields | Extra class (and extra boilerplate) for what a plain constructor could sometimes handle |
| Reads clearly at the call site — named `withX()` calls instead of positional arguments | Overkill for objects with only 2–3 fields |
| Naturally supports immutable objects — all fields set before construction, no setters after | Classic GoF form (separate `Director`) adds even more classes than most real code actually needs |
| Validation can happen once in `build()`, instead of scattered across constructor overloads | |

## How It Follows SOLID

- **Single Responsibility** — object construction is pulled out of the object itself into a dedicated builder; the object's own class doesn't need to juggle multiple constructors or validate itself mid-construction.
- **Open/Closed** — adding a new optional field means adding one new `withX()` method to the builder; existing `build()` call sites elsewhere in the codebase are untouched.

## When To Use vs Skip

| ✅ Use it when | ❌ Skip it when |
|---|---|
| The object has many optional fields / config-heavy construction | The object has only 2–3 fields — a constructor is simpler and sufficient |
| The constructor would otherwise need many overloads (the "telescoping constructor" problem) | All fields are required, none optional — a builder adds nothing over a constructor |
| The object should be immutable once built | |

## Related Patterns

- **vs Factory** — Factory decides *what type* of object to create; Builder decides *how to assemble* one already-known object's optional details. Commonly paired: a factory method can hand back a pre-configured builder (this repo's `Logger.error()` / `.info()` / `.debug()` each return a `LogBuilder` pre-seeded with the right `LogLevel` — factory + builder working together).
- **vs Prototype** — Prototype creates a new object by cloning an existing one; Builder creates a new object from scratch, step by step.
- **vs Abstract Factory** — Abstract Factory creates several separate, simpler objects that belong to the same family, one call each; Builder constructs one complex object step by step across many calls, and the object isn't usable until `build()` finishes.

## Interview Line

> "I used Builder to avoid a telescoping constructor for LogEntry, which has several optional fields, and to guarantee the final object is fully validated and immutable once built."

---

## Implementations

Four variants live in `sample/` — one plain baseline plus three real Builder forms, not interchangeable:

| | No Builder (worst) | Classic GoF (with Director) | Fluent Builder | Step Builder (best) |
|---|---|---|---|---|
| Who orchestrates construction | N/A — one constructor call | A dedicated `Director` class, in a fixed sequence | The client, via chained calls | The client, via chained calls |
| Required-field enforcement | Constructor signature (positional args) | Whatever sequence the Director runs | Runtime check inside `build()` | Compile-time — wrong order simply won't compile |
| Extra classes needed | None | `Builder` interface + `ConcreteBuilder` + `Director` | Just the builder class | One interface per required step |
| Common in | Objects with only 2–3 fields | Classic textbook examples, GUI toolkits with swappable build sequences | Modern Java — Lombok's `@Builder`, `HttpRequest.Builder` | Libraries that want invalid construction to be impossible, not just checked |

Ordered worst → best: no protection at all, to a form that fixes the constructor problem but adds heavy ceremony, to the modern lightweight standard, to the version that closes Fluent's one remaining gap (a required field can still be forgotten until runtime). See all four run back to back in [`sample/Main.java`](sample/Main.java).

#### No Builder — the baseline
[FULL CODE](sample/LogLineNoBuilder.java)

One constructor call, no chaining, no builder object. Fine at 3 fields — this is exactly the "telescoping constructor" problem Builder exists to solve once a class picks up more optional fields.

#### Classic GoF (with Director)
[FULL CODE](sample/LogLineClassic.java)

```java
public interface Builder {
    void buildMessage(String message);
    void buildLevel(String level);
    void buildMetadata(Map<String, String> metadata);
    LogLineClassic getResult();
}

public static class Engineer {
    public LogLineClassic construct(Builder builder, String message, String level, Map<String, String> metadata) {
        builder.buildMessage(message);
        builder.buildLevel(level);
        builder.buildMetadata(metadata);
        return builder.getResult();
    }
}
```

The client hands a builder to `Engineer` (the Director) and never calls `buildMessage()`/`buildLevel()` itself — `Engineer` owns the fixed build sequence. Fixes the telescoping-constructor problem, but at the cost of three extra classes for what's usually a single-object build.

#### Fluent Builder
[FULL CODE](sample/LogLineFluent.java)

```java
public static class LogBuilderFluent {
    private String message;
    private String level;
    private Map<String, String> metadata;

    public LogBuilderFluent withMessage(String message) {
        this.message = message;
        return this;
    }

    public LogBuilderFluent withLogLevel(String logLevel) {
        this.level = logLevel;
        return this;
    }

    public LogLineFluent build() {
        if (this.message == null || this.level == null) {
            throw new IllegalStateException("Message and log level must be provided");
        }
        return new LogLineFluent(this.message, this.level, this.metadata);
    }
}
```

From Joshua Bloch's *Effective Java*, Item 2 — "Consider a builder when faced with many constructor parameters." Self-returning `withX()` methods, chained directly by the caller, no separate `Director`. This is what almost all real-world Java code — and this repo's real `custom-logger` project — actually uses. The one gap: a forgotten required field only surfaces as an `IllegalStateException` at runtime, not at compile time.

#### Step Builder — required fields enforced at compile time
[FULL CODE](sample/LogLineStep.java)

```java
public interface MessageStep {
    LevelStep withMessage(String message);
}

public interface LevelStep {
    BuildStep withLevel(String level);
}

public interface BuildStep {
    BuildStep withMetadata(Map<String, String> metadata);
    LogLineStep build();
}
```

Each interface exposes only the next required step. Calling `.withLevel()` before `.withMessage()`, or `.build()` before both required fields are set, is a compile error — `cannot find symbol` — not a runtime `IllegalStateException` like Fluent Builder's version.

---

Full breakdown — where this shows up in interviews, what's actually tested, and the common mistake — lives in the Builder row of the [root README](../../README.md).
