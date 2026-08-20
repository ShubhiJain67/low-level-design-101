# Prototype Pattern

`Creational` · `Low Frequency Tier` · ✅ Built

Create new objects by copying an existing, fully-configured instance — the "prototype" — instead of building each one from scratch.

---

## Real-World Analogy

A rubber stamp. Instead of hand-drawing the same design every time, you press an existing, fully-formed stamp onto a new page. The new copy is independent of the original — inking one doesn't ink the other.

## UML

```text
Client -- clone() --> Prototype -- returns --> new, independent copy (same concrete class)
```

## Advantages vs Disadvantages

| ✅ Advantages | ❌ Disadvantages |
|---|---|
| Avoids re-running an expensive construction step (DB call, heavy computation, network fetch) | Java's `Cloneable`/`Object.clone()` mechanism is notoriously awkward — a marker interface with no `clone()` method on it, a `protected` method you must override, a checked exception to handle |
| Client can create a new object without knowing its concrete class — it just calls `clone()` on the interface | Default `clone()` is a **shallow copy** — reference fields end up shared between original and clone unless you explicitly deep-copy them |
| Avoids a subclass explosion when you need many similar-but-slightly-varied objects | Objects with circular references make correct deep cloning genuinely harder to get right |

## How It Follows SOLID

- **Open/Closed** — adding a new prototype type means adding a new class that implements the clone contract; existing client code, which only ever calls `clone()` through the interface, is untouched.
- **Dependency Inversion** — the client depends on the `Prototype`/`Cloneable`-style abstraction, never on the concrete class it happens to be copying.

## When To Use vs Skip

| ✅ Use it when | ❌ Skip it when |
|---|---|
| Building the object from scratch is expensive (DB round trip, heavy computation) and a similar instance already exists | The object is cheap to construct fresh — cloning adds complexity for no real saving |
| You need many similar-but-slightly-varied objects, without a subclass per variant | The object has complex circular references that make deep cloning error-prone |
| The client shouldn't need to know the concrete class to get a copy | Only one instance of the object will ever exist |

## Related Patterns

- **vs Factory** — Factory builds a new object from scratch via logic/parameters; Prototype builds a new object by copying an *existing* instance. No construction logic re-runs.
- **vs Builder** — Builder assembles a new object step by step from nothing; Prototype starts from an already-fully-built object and copies it in one call.

## Interview Line

> "I used Prototype to avoid re-doing an expensive construction step — cloning an existing, fully-configured instance instead of rebuilding it from scratch, and made sure to deep-copy the mutable fields so the clone doesn't silently share state with the original."

---

## Shallow Copy vs Deep Copy

This is the one thing this pattern actually gets tested on — get this wrong and the pattern is actively dangerous, not just unhelpful.

**Shallow copy** — Java's default `Object.clone()` behavior. Each field is copied by value: primitives (`int`, `boolean`, etc.) become true independent copies, but reference fields (a `List`, a nested object) copy only the *reference* — the clone and the original end up pointing at the exact same underlying object. Mutate that shared object through the clone, and the change shows up in the original too, even though they're supposedly two separate objects now.

**Deep copy** — the fix: inside your overridden `clone()`, explicitly clone (or reconstruct) every mutable reference field as well, so the new object owns its own independent copies of everything, with zero shared mutable state with the original.

**Worth knowing for the interview:** Joshua Bloch's *Effective Java* explicitly argues against implementing `Cloneable` at all, recommending a copy constructor or a static factory copy method instead — cleaner, no checked exception, no fighting `Object.clone()`'s awkward contract. Knowing this critique, and being able to explain *why* `Cloneable` is considered a design mistake, is itself a strong interview signal.

---

## Implementations

Two variants live in `sample/`, both cloning a class with one mutable reference field (`hobbies`, an `ArrayList<String>`) — the field that actually exposes the shallow-copy bug. See both run back to back, with output proving the aliasing, in [`sample/Main.java`](sample/Main.java).

#### Shallow Copy — the default `Object.clone()` behavior
[FULL CODE](sample/ShallowCopyClass.java)

```java
@Override
protected Object clone() throws CloneNotSupportedException {
    ShallowCopyClass newObj = (ShallowCopyClass) super.clone();
    return newObj;
}
```

No field-level override — `hobbies` is copied by reference. Adding to `clone.getHobbies()` also mutates `original.getHobbies()`, because both point at the same `ArrayList`.

#### Deep Copy — manually cloning the mutable field
[FULL CODE](sample/DeepCopyClass.java)

```java
@Override
protected Object clone() throws CloneNotSupportedException {
    DeepCopyClass newObj = (DeepCopyClass) super.clone();
    newObj.hobbies = new ArrayList<>(this.hobbies);
    return newObj;
}
```

One extra line — `newObj.hobbies = new ArrayList<>(this.hobbies)` — gives the clone its own independent list. Mutating the clone's hobbies afterward no longer touches the original.

**Note:** `clone()` is left `protected` on both classes here (as `Object` declares it), not widened to `public`. `Main.java` can still call `original.clone()` only because `Main` sits in the same package (`creational.prototype.sample`) — `protected` allows same-package access. Calling it from outside the package would fail to compile; that's part of why `Cloneable`'s contract is considered awkward.

---

## Prototype Registry

GoF's optional "prototype manager" — a keyed lookup of pre-configured prototypes. Instead of the client holding a reference to a specific prototype instance (or knowing its concrete class at all), it registers prototypes against a string key up front, then asks the registry for a clone by key whenever it needs one.

[FULL CODE](sample/PrototypeRegistry.java) · [`Prototype` interface](sample/Prototype.java) · [`RegistryPrototype`](sample/RegistryPrototype.java)

```java
public interface Prototype {
    Prototype clone() throws CloneNotSupportedException;
}

public class PrototypeRegistry {
    private final Map<String, Prototype> prototypes = new HashMap<>();

    public void register(String key, Prototype prototype) {
        prototypes.put(key, prototype);
    }

    public Prototype get(String key) throws CloneNotSupportedException {
        Prototype prototype = prototypes.get(key);
        if (prototype == null) {
            throw new IllegalArgumentException("No prototype registered for key: " + key);
        }
        return prototype.clone();
    }
}
```

`RegistryPrototype` implements `Prototype` so the registry can call `.clone()` polymorphically without knowing the concrete class. It's a separate class from `DeepCopyClass` — same deep-copy logic (clone `hobbies` into a new `ArrayList`), kept apart so `DeepCopyClass` stays a clean, standalone example of overriding `Object.clone()` directly, without also having to explain the `Prototype` interface. `RegistryPrototype.clone()` is `public`, unlike `DeepCopyClass.clone()`, because `Prototype.clone()` is a public interface method — any implementation must be at least as visible. See it registered and cloned twice in [`sample/Main.java`](sample/Main.java).

**Why the registry needs a deep-copying prototype:** a registry hands out clones of the *same* stored prototype to every caller, potentially many times over the program's life. If you registered a shallow-copy-only class instead, the shallow-copy bug wouldn't just affect one pair of objects — every clone the registry ever issues for that key would share the same mutable field, so one caller's mutation would silently leak into every other caller's copy. A registry is exactly the situation where getting deep-copy right stops being optional.

---

Full breakdown — where this shows up in interviews, what's actually tested, and the common mistake — lives in the Prototype row of the [root README](../../README.md).
