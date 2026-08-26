# Flyweight Pattern

`Structural` · `Low Frequency Tier` · ✅ Built

Use sharing to support large numbers of fine-grained objects efficiently.

---

## Real-World Analogy

A text editor rendering a document. Every occurrence of the letter "a" in Times New Roman 12pt doesn't need its own separate glyph object — the glyph's shape and font data (intrinsic, shared) is stored once and reused; only the position on the page (extrinsic, per-occurrence) differs.

## UML

```text
FlyweightFactory -- getFlyweight(key) --> returns a shared Flyweight instance (cached, keyed by intrinsic state)
Client stores extrinsic state separately, supplies it when calling operations on the Flyweight
```

## Advantages vs Disadvantages

| ✅ Advantages | ❌ Disadvantages |
|---|---|
| Drastically reduces memory usage when you need huge numbers of similar objects, by sharing the state that's identical across instances | Requires cleanly separating intrinsic (shareable) from extrinsic (per-instance) state — genuinely hard to get right if the state doesn't split cleanly |
| Centralizes creation and caching of shared instances in one factory, instead of scattering sharing logic across client code | Client code becomes more complex, since it now has to manage and pass extrinsic state explicitly on every call |
| | Can trade memory for CPU time — extrinsic state often has to be recomputed or looked up instead of just stored per object |

## How It Follows SOLID

- **Single Responsibility** — the FlyweightFactory's one job is creating and caching shared instances; it doesn't handle the extrinsic, per-instance context.
- **Open/Closed** — new Flyweight types can be added to the factory without changing client code that already retrieves and uses flyweights.

## When To Use vs Skip

| ✅ Use it when | ❌ Skip it when |
|---|---|
| An application needs a very large number of similar objects, and object count is a real memory concern | The number of objects is small enough that memory isn't actually a problem |
| Most of an object's state can be made extrinsic — moved out and passed in per call — without much extra client-side complexity | Object state can't be cleanly split into intrinsic/extrinsic without a lot of contortion |
| Object identity doesn't matter for these instances — shared instances are fine, since they're treated as interchangeable | |

## Related Patterns

- **vs Singleton** — Singleton guarantees exactly ONE instance total; Flyweight allows MANY shared instances, one per distinct intrinsic-state key, reused across many logical objects.
- **vs Prototype** — Prototype creates a new independent object by copying an existing one; Flyweight avoids creating new objects where possible, sharing one instance across many logical usages instead.

## Interview Line

> "I used Flyweight to avoid creating a separate object for every entity when most of their state was identical across thousands of instances — I split that shared state out into a cached object and passed the per-instance state in on each call."

---

## Implementations

`TreeFactory` caches one `TreeType` per `(name, color)` key — the intrinsic, shared state. `Tree` holds only its `x`/`y` position, the extrinsic state, plus a reference to the shared `TreeType`. See it run in [`sample/Main.java`](sample/Main.java): two `Tree`s registered as `"Oak", "Green"` share one `TreeType` instance; only one `"Created new TreeType"` line prints for both.

[FULL CODE](sample/TreeFactory.java) · [`TreeType`](sample/TreeType.java) · [`Tree`](sample/Tree.java)

```java
public class TreeFactory {
    private static final Map<String, TreeType> treeTypes = new HashMap<>();

    public static TreeType getTreeType(String name, String color) {
        String key = name + "-" + color;
        TreeType type = treeTypes.get(key);
        if (type == null) {
            type = new TreeType(name, color);
            treeTypes.put(key, type);
        }
        return type;
    }
}
```

Every `Tree` created with the same `name`/`color` combination gets back the exact same `TreeType` object — the shape/color data is stored once, no matter how many trees reference it.

---

Full breakdown — where this shows up in interviews, what's actually tested, and the common mistake — lives in the Flyweight row of the [root README](../../README.md).
