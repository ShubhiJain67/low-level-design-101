# Proxy Pattern

`Structural` · `High Priority Tier` · ✅ Built

Provide a surrogate or placeholder for another object to control access to it.

---

## Real-World Analogy

A credit card is a proxy for a bank account — it controls and mediates access to your money without being the money itself, and it can add checks (spending limits, fraud detection) before the real transaction goes through.

## UML

```text
Client --> Subject (interface) <-- RealSubject
                      ^
                      |
                    Proxy (holds a reference to RealSubject, controls access, may defer creation)
```

## Advantages vs Disadvantages

| ✅ Advantages | ❌ Disadvantages |
|---|---|
| Controls access to the real object — can add lazy initialization, permission checks, logging, or caching transparently | Adds a layer of indirection — every call passes through the proxy, which can add latency or complexity |
| Client code depends only on the Subject interface, so swapping the real object for a proxy requires no client changes | Response can be delayed if the proxy needs extra work (initial load, network round-trip, permission check) before delegating |
| Can defer expensive object creation until it's actually needed (virtual proxy) | Easy to conflate with Decorator if you're not precise about intent — Proxy controls access, Decorator adds behavior |

## How It Follows SOLID

- **Single Responsibility** — the Proxy's one job is controlling access (lazy loading, permission checks, caching); it delegates the actual work to the RealSubject.
- **Open/Closed** — client code written against the Subject interface doesn't need to change whether it's talking to the RealSubject or a Proxy.

## When To Use vs Skip

| ✅ Use it when | ❌ Skip it when |
|---|---|
| You need to defer creating an expensive object until it's actually used (virtual proxy) | There's no actual access-control, lazy-loading, or caching need — a direct reference is simpler |
| You need an access-control layer in front of a sensitive object (protection proxy) | What you actually want is to ADD new behavior to an object, not control access to it — that's Decorator, not Proxy |
| You need to cache results of an expensive or remote call transparently (caching/remote proxy) | |

## Related Patterns

- **vs Decorator** — Proxy controls ACCESS to an object, deciding whether/when/how a call reaches it; Decorator adds BEHAVIOR to an object — the call always reaches it, decorators just add extra work before/after. Conflating the two is a common interview tell.
- **vs Facade** — Facade simplifies access to an entire subsystem of many classes; Proxy stands in for ONE specific object, implementing the exact same interface as that object.
- **vs Singleton** — different axis entirely, easy to conflate since both "sit in front of" object creation: Singleton controls *how many instances exist* (exactly one); Proxy controls *access* to an instance regardless of count — the client can't even tell whether it's talking to the real object or a stand-in.

## Interview Line

> "I used a virtual/protection/caching Proxy to control access to the real object — it implements the same interface, so the client can't tell the difference, but the proxy handles lazy-loading, permission checks, or caching before the real object is ever touched."

---

## Implementations

`ProxyImage` implements the same `Image` interface as `RealImage`, but doesn't construct the (simulated) expensive `RealImage` until `display()` is actually called — a virtual proxy. See it run in [`sample/Main.java`](sample/Main.java): the "Loading ... from disk" line only appears on the first `display()` call, not when the `ProxyImage` is created.

[FULL CODE](sample/ProxyImage.java) · [`Image` interface](sample/Image.java) · [`RealImage`](sample/RealImage.java)

```java
public class ProxyImage implements Image {
    private final String filename;
    private RealImage realImage;

    @Override
    public void display() {
        if (realImage == null) {
            realImage = new RealImage(filename);
        }
        realImage.display();
    }
}
```

The second `display()` call reuses the already-loaded `realImage` — the expensive load only happens once, and only if `display()` is ever called at all.

---

Full breakdown — where this shows up in interviews, what's actually tested, and the common mistake — lives in the Proxy row of the [root README](../../README.md).
