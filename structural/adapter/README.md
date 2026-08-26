# Adapter Pattern

`Structural` · `Master Tier` · ✅ Built

Convert the interface of a class into another interface clients expect. Adapter lets classes work together that couldn't otherwise, because of incompatible interfaces.

---

## Real-World Analogy

A power plug adapter. You have a US plug and a European socket — the adapter doesn't change the plug or the socket, it just sits between them, translating one shape into the other so they can connect.

## Before vs After

**Before — client code depends on the Adaptee directly:**

```java
LegacyCharger legacyCharger = new LegacyCharger();
legacyCharger.oldPlug();
```

Works, until the code also needs to support a different legacy class with a different method name — now the client has to know every legacy method name by heart and branch between them itself. Every new legacy class means touching client code again.

**After — client code depends only on the target interface:**

```java
ICharger charger = new ChargerAdapter(new LegacyCharger());
charger.charge();
```

Swap in any class that implements `ICharger` — `ChargerAdapter`, a modern charger that's already compatible, or a future adapter wrapping something else entirely — and this line never changes. The client doesn't know, and doesn't need to know, what's underneath.

## UML

```text
Client -- calls target interface --> Adapter -- translates the call to --> Adaptee's existing interface
```

## Advantages vs Disadvantages

| ✅ Advantages | ❌ Disadvantages |
|---|---|
| Lets two incompatible interfaces work together without modifying either one's source | Adds an extra layer of indirection — one more class/call in the chain |
| Isolates conversion logic in one dedicated class instead of scattering it across call sites | The common Java form (Object Adapter, via composition) can only wrap the Adaptee's behavior, not override it — Class Adapter would allow overriding but needs multiple inheritance, which Java doesn't have |
| The Adaptee class stays reusable and untouched; one Adaptee can be wrapped by multiple different adapters | If the interfaces are semantically incompatible, not just structurally different, Adapter can hide a real design conflict instead of surfacing it |

## How It Follows SOLID

- **Single Responsibility** — interface-translation logic lives in one dedicated class, not duplicated across every call site that needs the Adaptee.
- **Open/Closed** — new incompatible interfaces can be supported by adding new Adapter classes; neither the Client nor the Adaptee needs to change.

## When To Use vs Skip

| ✅ Use it when | ❌ Skip it when |
|---|---|
| You need to integrate a third-party or legacy class whose interface doesn't match what your code expects | You control both interfaces — just change one of them directly |
| You can't modify the source of the class you need to use | The interfaces are already compatible — Adapter would add indirection for no benefit |
| You want to reuse an existing class without changing its interface | |

## Related Patterns

- **vs Bridge** — Adapter retrofits two already-existing, independently-designed interfaces after the fact; Bridge is designed upfront, before either side exists, so abstraction and implementation can vary independently.
- **vs Decorator** — Adapter changes an interface to match what the client expects; Decorator keeps the same interface and adds new behavior.
- **vs Facade** — Facade defines a new, simplified interface over an entire subsystem; Adapter makes one existing interface match another existing interface. Facade simplifies, Adapter translates.

## Interview Line

> "I used Adapter to integrate a legacy or third-party class whose interface didn't match what our code expected, without touching either side — wrapped it in a class that translates calls between the two."

## Real-World Usage

- **`InputStreamReader`** — adapts a byte-based `InputStream` to the character-based `Reader` interface.
- **`Arrays.asList(T...)`** — adapts a plain array to the `List` interface.
- **`Collections.enumeration()` / `Collections.list()`** — adapts between the legacy `Enumeration` interface and modern `Iterator`/`Collection`.
- **Swing's `MouseAdapter` / `KeyAdapter` / `WindowAdapter`** — adapts a multi-method listener interface down to just the methods you want to override.
- **Spring MVC's `HandlerAdapter`** — adapts different handler types to the one `handle()` call `DispatcherServlet` invokes.

---

## Implementations

Every charger in `sample/` — modern or legacy — is used through one target interface, [`ICharger`](sample/ICharger.java) (`charge()`). `sample/chargers/` holds the concrete charger classes (one already compatible, two legacy), `sample/adapters/` holds the adapters that make the legacy ones fit. All three run back to back in [`sample/Main.java`](sample/Main.java).

#### Already compatible — no adapter needed

[FULL CODE](sample/chargers/CTypeCharger.java)

```java
public class CTypeCharger implements ICharger {
    public void charge() {
        System.out.println("Charging with Type-C charger");
    }
}
```

`CTypeCharger` implements `ICharger` directly — this is the `❌ Skip it when` row from above, made concrete: when a class's interface already matches what the client expects, wrapping it in an adapter would just be indirection for no reason.

#### Legacy device #1 — adapted

[FULL CODE](sample/adapters/ChargerAdapter.java) · [`LegacyCharger`](sample/chargers/LegacyCharger.java)

```java
public class ChargerAdapter implements ICharger {
    private final LegacyCharger legacyCharger;

    public ChargerAdapter(LegacyCharger legacyCharger) {
        this.legacyCharger = legacyCharger;
    }

    @Override
    public void charge() {
        legacyCharger.oldPlug();
    }
}
```

`LegacyCharger` has an interface (`oldPlug()`) the rest of the code doesn't expect — `ChargerAdapter` wraps it and exposes `ICharger.charge()` instead, translating the call underneath.

#### Legacy device #2 — a second, differently-incompatible device, its own adapter

[FULL CODE](sample/adapters/MicroUsbChargerAdapter.java) · [`LegacyMicroUsbCharger`](sample/chargers/LegacyMicroUsbCharger.java)

```java
public class MicroUsbChargerAdapter implements ICharger {
    private final LegacyMicroUsbCharger legacyMicroUsbCharger;

    public MicroUsbChargerAdapter(LegacyMicroUsbCharger legacyMicroUsbCharger) {
        this.legacyMicroUsbCharger = legacyMicroUsbCharger;
    }

    @Override
    public void charge() {
        legacyMicroUsbCharger.plugMicroUsb();
    }
}
```

`LegacyMicroUsbCharger` has yet another incompatible interface (`plugMicroUsb()`, not `oldPlug()`) — proving Open/Closed in practice: supporting a whole new legacy device meant adding 2 new classes, and nothing else — `ICharger`, `CTypeCharger`, `LegacyCharger`, and `ChargerAdapter` were all untouched. All 3 chargers — 1 native, 2 adapted — are indistinguishable to the client, since all 3 are just an `ICharger`.

---

Full breakdown — where this shows up in interviews, what's actually tested, and the common mistake — lives in the Adapter row of the [root README](../../README.md).
