# Abstract Factory Pattern

`Creational` · `High Priority Tier` · ⌛ TODO

Provide an interface for creating **families of related objects** without specifying their concrete classes — so the objects a client gets back are guaranteed to belong to the same consistent family.

---

## Real-World Analogy

A furniture store that sells matching sets. Order "Victorian" and you get a Victorian chair, sofa, and coffee table together. Order "Modern" and you get all three in matching modern style. You never end up with a Victorian chair next to a Modern sofa — the store guarantees the whole set matches.

## UML

```text
Client -- asks --> AbstractFactory -- creates --> AbstractProductA, AbstractProductB, ...
                         |
              ConcreteFactory1 --> ConcreteProductA1, ConcreteProductB1   (family 1)
              ConcreteFactory2 --> ConcreteProductA2, ConcreteProductB2   (family 2)
```

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

## Sample Code

Illustrative example — no folder built yet for this pattern (see `⌛ TODO` above). This one's the classic case: a UI toolkit where each "theme" produces a matching button and checkbox.

```java
interface Button {
    void render();
}

interface Checkbox {
    void render();
}

// Family 1: Dark theme
class DarkButton implements Button {
    public void render() { System.out.println("Rendering Dark Button"); }
}
class DarkCheckbox implements Checkbox {
    public void render() { System.out.println("Rendering Dark Checkbox"); }
}

// Family 2: Light theme
class LightButton implements Button {
    public void render() { System.out.println("Rendering Light Button"); }
}
class LightCheckbox implements Checkbox {
    public void render() { System.out.println("Rendering Light Checkbox"); }
}

// Abstract Factory — one method per product in the family
interface UIFactory {
    Button createButton();
    Checkbox createCheckbox();
}

class DarkUIFactory implements UIFactory {
    public Button createButton() { return new DarkButton(); }
    public Checkbox createCheckbox() { return new DarkCheckbox(); }
}

class LightUIFactory implements UIFactory {
    public Button createButton() { return new LightButton(); }
    public Checkbox createCheckbox() { return new LightCheckbox(); }
}

// Client — never touches DarkButton/LightButton directly, only the interfaces
class Application {
    private final Button button;
    private final Checkbox checkbox;

    public Application(UIFactory factory) {
        this.button = factory.createButton();
        this.checkbox = factory.createCheckbox();
    }

    public void render() {
        button.render();
        checkbox.render();
    }
}
```

Swap `new DarkUIFactory()` for `new LightUIFactory()` in the client and every product returned changes families together — that's the whole guarantee this pattern buys you.

---

Full breakdown — where this shows up in interviews, what's actually tested, and the common mistake — lives in the Abstract Factory row of the [root README](../../README.md).
