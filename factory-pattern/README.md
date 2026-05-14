# Factory Pattern

### Problem Statement

In large applications, object creation logic can become repetitive, tightly coupled, and difficult to manage. Different types of objects may need to be created dynamically based on runtime conditions.

Directly creating objects throughout the codebase leads to:

* duplicated creation logic
* tight coupling between client and implementation
* poor maintainability
* difficult extensibility

Factory Pattern solves this by centralizing object creation.

---

### Why This Pattern Is Needed

Factory Pattern is needed when:

* multiple implementations of an object exist
* object creation logic is complex
* client should not know exact implementation details
* object creation must be centralized
* loose coupling is desired

Instead of clients creating objects directly, they request objects from a factory.

This improves:

* maintainability
* scalability
* abstraction
* readability

---

### Definition

Factory Pattern is a creational design pattern that provides a centralized mechanism for creating objects without exposing the creation logic to the client.

The client interacts with abstractions instead of concrete implementations.

---

### UML / Flow

```
Client
↓
Factory
↓
Concrete Implementations
↓
Returned as Interface/Abstract Type
```

The factory decides:

* which object to create
* when to create it
* how to configure it

### Real-World Analogy

A restaurant works like a factory.

The customer places an order without knowing:

* how ingredients are prepared
* how cooking happens
* how the final product is assembled

The kitchen handles all creation logic and returns the final product.

The customer only interacts with the final abstraction.


## Advantages

### 1. Loose Coupling

Client depends on abstractions instead of concrete implementations.

### 2. Centralized Object Creation

All creation logic exists in one place, making maintenance easier.

### 3. Improved Readability

Client code becomes cleaner and simpler.

### 4. Easy Extensibility

New implementations can be added with minimal client-side changes.

### 5. Better Maintainability

Changes in creation logic affect only the factory.

### 6. Supports SOLID Principles

Especially:

* Open/Closed Principle
* Dependency Inversion Principle


## Disadvantages

### 1. Additional Abstraction

Can introduce unnecessary complexity for small systems.

### 2. Factory Class Can Grow Large

If too many object types exist, the factory may become difficult to maintain.

### 3. Modification May Still Be Needed

Basic Factory Pattern may require updating factory logic when adding new types.

---

### When To Use

Use Factory Pattern when:

* multiple object types exist
* object creation logic is complicated
* clients should remain loosely coupled
* centralized creation is beneficial
* implementations may change in future

---

### When NOT To Use

Avoid Factory Pattern when:

* object creation is extremely simple
* only one implementation exists
* abstraction adds unnecessary complexity

---

### Real-World Use Cases

* Notification Systems
* Payment Gateways
* Database Connectors
* Cache Creation Systems
* Logging Frameworks
* UI Component Libraries
* Cloud Service Providers

---

### Factory Pattern in Cache System

In cache systems, Factory Pattern is useful for creating:

* LRU Cache
* LFU Cache
* FIFO Cache
* Two-Tier Cache

The client requests a cache type without knowing internal configuration details.

This keeps cache creation modular and extensible.

---

### Difference Between Factory and Strategy Pattern

Factory Pattern:

* focuses on object creation

Strategy Pattern:

* focuses on selecting behavior/algorithms

Factory decides:

* what object to create

Strategy decides:

* how the object behaves

Both patterns are often used together in scalable systems.

---

## Interview Tips

### Key Statement

“I used Factory Pattern to centralize object creation and reduce coupling between client code and implementations.”

### Important Concepts To Mention

* loose coupling
* abstraction
* centralized creation logic
* extensibility
* dependency inversion

## Common Interview Questions

### Why not directly create objects?

Direct object creation increases coupling and duplicates creation logic across the application.

### Is Factory Pattern a creational pattern?

Yes. Factory Pattern belongs to creational design patterns.

### Difference between Factory and Abstract Factory?

Factory creates one type/family of objects.

Abstract Factory creates multiple related object families.

# Summary

Factory Pattern:

* centralizes object creation
* hides implementation details
* improves maintainability
* reduces coupling
* supports scalable architecture

It is one of the most commonly used design patterns in backend engineering and machine coding interviews.
