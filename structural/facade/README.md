# Facade Pattern

`Structural` · `Master Tier` · ✅ Built

Provide a unified interface to a set of interfaces in a subsystem. Facade defines a higher-level interface that makes the subsystem easier to use.

---

## Real-World Analogy

A hotel concierge. Booking a car, a restaurant reservation, and theater tickets separately means dealing with three different systems; the concierge desk gives you one person who coordinates all of it behind the scenes. You don't lose the ability to book each one individually if you need to — the concierge is just a simpler front door.

## UML

```text
Client --> Facade --> { SubsystemA, SubsystemB, SubsystemC } (Facade coordinates calls across the subsystem classes)
```

## Advantages vs Disadvantages

| ✅ Advantages | ❌ Disadvantages |
|---|---|
| Shields client code from a complex subsystem's internals — one simple call instead of orchestrating many classes | Can become a "god object" if too much orchestration logic accumulates in one Facade class |
| Decouples the client from the subsystem, so the subsystem can be swapped out later by changing only the Facade | Hiding complexity behind a simple call can remove flexibility a caller actually needed |
| Doesn't hide the subsystem entirely — callers that need finer control can still bypass the Facade | |

## How It Follows SOLID

- **Single Responsibility** — the Facade's one job is coordinating the subsystem into a simpler entry point; it doesn't implement subsystem logic itself.
- **Dependency Inversion** (loosely) — client code depends on the Facade's simple interface rather than directly on multiple concrete subsystem classes.

## When To Use vs Skip

| ✅ Use it when | ❌ Skip it when |
|---|---|
| A subsystem is complex and most callers only need a simple, common-case entry point into it | The "subsystem" is really just one or two classes already — wrapping it doesn't simplify anything |
| You want to decouple client code from a subsystem's internal classes | Callers routinely need fine-grained control that the Facade would have to expose anyway, defeating the simplification |
| You're layering a system and want a clean entry point per layer | |

## Related Patterns

- **vs Adapter** — Adapter makes an existing interface match another interface the client expects (translation, usually one class); Facade defines a brand-new, simpler interface over an entire subsystem of many classes.
- **vs Mediator** — both coordinate multiple objects, but Facade offers a one-directional simplified entry point from client to subsystem; Mediator centralizes many-to-many communication between the subsystem's own objects.
- **vs Proxy** — Facade simplifies access to an entire subsystem of many classes; Proxy stands in for ONE specific object, implementing the exact same interface as that object rather than a new, simpler one.
- **vs Controller** — Facade is protocol-agnostic, simplifying a subsystem for any caller; a Controller (MVC) is bound to a specific external entry point (an HTTP route) and also handles request parsing, validation, auth, and response shaping — none of which is Facade's job. A well-designed Controller often calls a Facade internally, but the Facade itself has no idea a Controller, or HTTP, exists.

## Facade vs Controller — When To Use Which

| Situation | Use |
|---|---|
| Only internal callers — another class, a script, a test, no external boundary | Facade only — no Controller needed |
| An external boundary exists (HTTP/CLI/queue), but the subsystem is 1–2 calls | Controller only — a Facade would just be indirection |
| An external boundary exists AND the orchestration is genuinely multi-class | Both — Controller handles the boundary, Facade handles orchestration underneath |

The "both" case earns its keep for 3 reasons: the Facade can be unit-tested directly, with no request/response mocking; a second entry point (a scheduled job, a gRPC endpoint) can reuse the same Facade instead of duplicating the orchestration; and the Controller stays thin instead of rotting into a file that also coordinates services.

## Interview Line

> "I used Facade to give callers one simple entry point into a multi-step subsystem, instead of making every caller coordinate several classes themselves — the subsystem classes are all still usable directly if someone needs finer control."

---

## Implementations

`OrderFacade` coordinates `InventoryService`, `PaymentService`, and `ShippingService` behind one `placeOrder()` call — the client never touches the 3 subsystem classes directly. See the full sequence run in [`sample/Main.java`](sample/Main.java).

[FULL CODE](sample/OrderFacade.java) · [`InventoryService`](sample/InventoryService.java) · [`PaymentService`](sample/PaymentService.java) · [`ShippingService`](sample/ShippingService.java)

```java
public class OrderFacade {
    private final InventoryService inventory = new InventoryService();
    private final PaymentService payment = new PaymentService();
    private final ShippingService shipping = new ShippingService();

    public void placeOrder(String item, double amount) {
        if (!inventory.checkStock(item)) { return; }
        if (!payment.charge(item, amount)) { return; }
        shipping.ship(item);
    }
}
```

The 3 subsystem classes are still ordinary public classes — a caller who needs finer control can still use them directly instead of going through the Facade.

---

Full breakdown — where this shows up in interviews, what's actually tested, and the common mistake — lives in the Facade row of the [root README](../../README.md).
