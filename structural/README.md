# Structural Design Patterns

Structural patterns are concerned with how classes and objects are composed into larger structures, while keeping those structures flexible and efficient — as opposed to how objects get created (Creational) or how they communicate (Behavioral).

If a problem sounds like "these two interfaces don't match," "I need to add behavior to one object without subclassing," "I need to hide a messy subsystem behind one call," or "individual objects and groups of them should be usable the same way" — it's usually a structural pattern.

---

## Patterns in this folder

All 7 classic GoF structural patterns are built here — this folder is complete, unlike `behavioral/`, which still has gaps.

| Pattern | Status | Solves | Sample project | README |
|---|---|---|---|---|
| Adapter | ✅ Built | Two existing, incompatible interfaces need to work together, without modifying either | [Charger adapters](adapter/sample) (legacy chargers wrapped to a common `ICharger`) | [adapter/README.md](adapter/README.md) |
| Decorator | ✅ Built | Add responsibilities to ONE object at runtime, without touching its class or creating a subclass explosion | [Coffee order](decorator/sample) (milk, caramel — stacked at runtime) | [decorator/README.md](decorator/README.md) |
| Facade | ✅ Built | Simplify a complex, multi-class subsystem behind one clean call | [Order checkout](facade/sample) (inventory + payment + shipping behind one `OrderFacade`) | [facade/README.md](facade/README.md) |
| Proxy | ✅ Built | Control or delay access to an object — lazy-loading, permission checks, caching | [Image loading](proxy/sample) (lazy real image load + a protection check, as 2 separate proxies) | [proxy/README.md](proxy/README.md) |
| Composite | ✅ Built | Treat a single object and a group of objects through the same interface | [File system tree](composite/sample) (files and folders, both a component) | [composite/README.md](composite/README.md) |
| Bridge | ✅ Built | Decouple an abstraction from its implementation so both can vary independently | [Shapes × colors](bridge/sample) (shape hierarchy bridged to a separate color hierarchy) | [bridge/README.md](bridge/README.md) |
| Flyweight | ✅ Built | Huge numbers of similar objects — share common (intrinsic) state instead of duplicating it per instance | [Tree rendering](flyweight/sample) (shared `TreeType` reused across many placed trees) | [flyweight/README.md](flyweight/README.md) |

---

## The 3 that "wrap" another object — and how they're actually different

Adapter, Decorator, and Proxy all put one object behind another object implementing some interface. That surface similarity is exactly why they get mixed up — the difference is *why* you're wrapping.

| | Adapter | Decorator | Proxy |
|---|---|---|---|
| Interface | Changes it — translates an old interface into the one the client expects | Keeps the same interface as what it wraps | Keeps the same interface as what it wraps |
| Purpose | Make 2 already-existing, incompatible interfaces fit together | Add new behavior/responsibility to one object | Control or delay access to an object, without changing what it does |
| Ask yourself | "Do these 2 interfaces just not match?" | "Do I need to add behavior to ONE object at runtime, without subclassing?" | "Do I need to control/delay access (lazy-load, permission, caching) without changing behavior?" |

## The 3 that organize a bigger structure — and how they're actually different

Facade, Composite, and Bridge all involve more than 2 classes working together, but for different structural reasons.

| | Facade | Composite | Bridge |
|---|---|---|---|
| Structure | One new, simplified interface sitting in front of several existing subsystem classes | A tree — a single object and a group of objects share one interface | Two separate hierarchies (abstraction + implementation) that vary independently |
| Purpose | Hide subsystem complexity behind 1 call | Let client code treat "one" and "many" identically, no type-checking | Let 2 different things change independently, neither caring how the other is implemented |
| Ask yourself | "Is this just hiding a multi-step process behind one method?" | "Do I have a tree where leaves and branches should be usable the same way?" | "Do I have 2 axes of variation designed upfront, not retrofitted?" |

---

## Keyword → Pattern Cheat Sheet

The structural rows from the root README's full cheat sheet — same wording, filtered to this category.

| Phrase in the Problem | Reach For |
|---|---|
| "Make 2 incompatible interfaces work together" | Adapter |
| "Add behavior to an object dynamically, without subclass explosion" | Decorator |
| "Simplify a complex subsystem behind 1 clean call" | Facade |
| "Control or delay access to an object" | Proxy |
| "Treat individual objects and groups uniformly (tree structure)" | Composite |
| "Decouple an abstraction from its implementation, both vary independently" | Bridge |
| "Huge number of similar objects, share state to save memory" | Flyweight |

---

For the full interview-priority ranking, the complete cheat sheet across all categories, and the concurrency section, see the [root README](../README.md).
