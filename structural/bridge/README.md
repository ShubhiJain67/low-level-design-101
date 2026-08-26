# Bridge Pattern

`Structural` · `Good to Know Tier` · ✅ Built

Decouple an abstraction from its implementation so that the two can vary independently.

---

## Real-World Analogy

A universal remote control and the TVs it controls. The remote (abstraction) defines the operations — power, volume, channel; each brand of TV (implementation) implements how those operations actually happen on its hardware. New remote types and new TV brands can be added independently — neither side needs to know the other's internals.

## UML

```text
Abstraction --has-a--> Implementor (interface)
RefinedAbstraction extends Abstraction
ConcreteImplementorA / ConcreteImplementorB implement Implementor
```

## Advantages vs Disadvantages

| ✅ Advantages | ❌ Disadvantages |
|---|---|
| Abstraction and implementation can be developed and extended independently — two hierarchies instead of one combined one | Adds upfront design complexity — two hierarchies plus a composition link, for a problem that might not need it |
| Avoids a combinatorial explosion of subclasses when a class has two independent dimensions of variation | Only pays off when there are genuinely two (or more) independent axes of variation — for one axis, this is just needless indirection |
| Implementation details are hidden from the client, which only depends on the Abstraction | |

## How It Follows SOLID

- **Open/Closed** — new implementations or new abstractions can be added as new classes on either side, without touching the other hierarchy.
- **Dependency Inversion** — the Abstraction depends on the Implementor interface, not on any concrete implementation.

## When To Use vs Skip

| ✅ Use it when | ❌ Skip it when |
|---|---|
| A class has two independent dimensions that both need to vary (e.g. shape × renderer, remote × device brand) | There's only one axis of variation — that's Strategy, not Bridge |
| You want to avoid a permanent, compile-time binding between abstraction and implementation | The implementation is unlikely to ever change or be swapped — the extra indirection buys nothing |
| Both abstraction and implementation should be independently extensible via subclassing | |

## Related Patterns

- **vs Adapter** — Bridge is designed upfront, before both sides exist, so two axes can vary independently; Adapter is applied after the fact to make two already-existing, incompatible interfaces work together.
- **vs Strategy** — structurally similar (composition + an interface), but Strategy swaps ONE algorithm at runtime; Bridge decouples two independent hierarchies — abstraction and implementation — so both can vary.

## Interview Line

> "I used Bridge because we had two independent axes of variation, and I didn't want a subclass for every combination of the two — the abstraction holds a reference to the implementor interface, so either side can grow independently."

---

## Implementations

`Shape` (Abstraction) holds a `Color` (Implementor) reference instead of extending it — `Circle` (RefinedAbstraction) and any `Color` implementation (`RedColor`, `BlueColor`) combine freely, without a subclass per shape-color pair. See both combinations run in [`sample/Main.java`](sample/Main.java).

[FULL CODE](sample/Shape.java) · [`Color` interface](sample/Color.java) · [`Circle`](sample/Circle.java)

```java
public abstract class Shape {
    protected Color color;

    protected Shape(Color color) {
        this.color = color;
    }

    public abstract void draw();
}

public class Circle extends Shape {
    public Circle(Color color) {
        super(color);
    }

    @Override
    public void draw() {
        System.out.println("Circle filled with " + color.fill());
    }
}
```

Adding a new shape (`Square`) or a new color (`GreenColor`) never requires touching the other hierarchy — that's the two axes varying independently.
             
---

Full breakdown — where this shows up in interviews, what's actually tested, and the common mistake — lives in the Bridge row of the [root README](../../README.md).
