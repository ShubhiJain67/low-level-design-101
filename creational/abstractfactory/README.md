# Abstract Factory Pattern

`Creational` · `High Priority Tier`

Provide an interface for creating **families of related objects** without specifying their concrete classes — so the objects a client gets back are guaranteed to belong to the same consistent family.

---

## Real-World Analogy

A furniture store that sells matching sets. Order "Victorian" and you get a Victorian chair, sofa, and coffee table together. Order "Modern" and you get all three in matching modern style. You never end up with a Victorian chair next to a Modern sofa — the store guarantees the whole set matches.

## Advantages vs Disadvantages

| ✅ Advantages | ❌ Disadvantages |
|---|---|
| Guarantees products from one family are always used together | Adding a new PRODUCT TYPE to the family means editing the abstract factory interface AND every concrete factory |
| Client depends only on abstract factory + abstract product interfaces | More upfront classes/interfaces than plain Factory |
| Swapping an entire family = swapping one factory instance | Overkill if you'll only ever need one product type |

## How It Follows SOLID

- **Dependency Inversion** — client depends on `AbstractFactory` and the abstract product interfaces, never on a concrete factory or concrete product.
- **Open/Closed — only on one axis.** Adding a new *family* (e.g. a new theme) is additive: one new concrete factory, no existing code touched. Adding a new *product type* to the family (e.g. a `Slider` alongside `Button`/`Checkbox`) is NOT additive — it forces a change to the abstract factory interface and every concrete factory that implements it. This asymmetry is the most commonly tested nuance of this pattern.
- **Single Responsibility** — each concrete factory's only job is producing one consistent family of products.

## When To Use vs Skip

| ✅ Use it when | ❌ Skip it when |
|---|---|
| Multiple families of related products must be used together consistently | Only one product type exists — that's plain Factory, not Abstract Factory |
| The system needs to stay independent of how its products are created and composed | The "family" axis will never grow — the extra ceremony isn't worth it |
| You expect to add new families over time (new theme, new platform, new region) | Products don't actually need to stay consistent as a set |

## Related Patterns

- **vs Factory Method** — Factory Method creates one product; Abstract Factory typically composes several Factory Methods (one per product in the family) behind a single interface.
- **vs Builder** — Builder constructs one complex object step by step; Abstract Factory creates several separate, simpler objects that must belong to the same family.

## Interview Line

> "I used Abstract Factory when I needed to guarantee a whole family of related objects — like a UI theme's button and checkbox — stayed consistent, without the client ever knowing which concrete family it was working with."

---

## Implementations

A UI toolkit where each theme (`LIGHT` / `DARK`) produces a matching button and checkbox — the classic Abstract Factory case. `UIFactory.getUIFactory(UIType)` hands back one concrete factory (`LightUIFactory` or `DarkUIFactory`); both the button and checkbox it then creates come from that same factory instance, so they're guaranteed to belong to the same family. See it run in [`sample/Main.java`](sample/Main.java).

[FULL CODE](sample/factories/UIFactory.java) · [`IUIFactory`](sample/interfaces/IUIFactory.java) · [`DarkUIFactory`](sample/factories/DarkUIFactory.java) · [`LightUIFactory`](sample/factories/LightUIFactory.java) · [`IButton`](sample/interfaces/IButton.java) · [`ICheckBox`](sample/interfaces/ICheckBox.java)

```java
public interface IUIFactory {
    IButton renderButton();
    ICheckBox renderCheckBox();
}

public class DarkUIFactory implements IUIFactory {
    @Override
    public IButton renderButton() { return new DarkButton(); }

    @Override
    public ICheckBox renderCheckBox() { return new DarkCheckBox(); }
}

public class UIFactory {
    // Picks the concrete factory for a given theme. Both the button and
    // checkbox below come from this ONE factory instance, so they're
    // guaranteed to belong to the same family.
    public static IUIFactory getUIFactory(UIType type) {
        switch (type) {
            case LIGHT -> { return new LightUIFactory(); }
            case DARK -> { return new DarkUIFactory(); }
            default -> throw new IllegalArgumentException("Invalid UI type");
        }
    }
}
```

Swapping `UIType.DARK` for `UIType.LIGHT` in the client changes both the button AND the checkbox together — the client (`Main`) never touches `DarkButton`/`LightButton`/`DarkCheckBox`/`LightCheckBox` directly, only the `IButton`/`ICheckBox` interfaces and the `UIType` enum.

```
DarkUIFactory  --> DarkButton, DarkCheckBox    (family 1)
LightUIFactory --> LightButton, LightCheckBox  (family 2)
```

---

Full breakdown — where this shows up in interviews, what's actually tested, and the common mistake — lives in the Abstract Factory row of the [root README](../../README.md).
