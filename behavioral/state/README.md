# State Pattern

## Type
Behavioral Design Pattern

---

# Problem Statement

Design an Order Management System where an order goes through different states such as:

```text
CREATED → PAID → SHIPPED → DELIVERED
```

The behavior of the order should change depending on its current state.

The system should avoid:
- large `if-else` statements
- tightly coupled state logic
- difficult state management

---

# Why This Pattern Is Needed

Without the State Pattern:

- state handling becomes messy
- multiple conditions are added everywhere
- code becomes difficult to extend
- adding new states requires modifying existing code

The State Pattern helps by:
- separating state-specific behavior
- making transitions cleaner
- improving extensibility
- following SOLID principles

---

# UML / Flow

```text
           +----------------+
           |  OrderContext  |
           +----------------+
                    |
                    v
             +-------------+
             | OrderState  |
             +-------------+
              /     |      \
             /      |       \
            v       v        v

     Created   Paid   Shipped
```

---

# Real-World Analogy

Think of a food delivery order.

An order behaves differently depending on its state:

| State | Allowed Action |
|---|---|
| CREATED | Can make payment |
| PAID | Can prepare shipment |
| SHIPPED | Can track delivery |
| DELIVERED | Cannot modify order |

Instead of writing conditions everywhere, each state manages its own behavior.

---

# Output

```text
Order Created
Order Paid
Order Shipped
Order Delivered
```

---

# Pros & Cons

## Pros

| Advantage | Description |
|---|---|
| Cleaner Code | Removes large conditional logic |
| Extensible | Easy to add new states |
| Better Maintainability | Each state is isolated |
| Follows SOLID Principles | Especially Open/Closed Principle |
| Better Readability | State transitions become explicit |

---

## Cons

| Disadvantage | Description |
|---|---|
| More Classes | Every state requires a separate class |
| Slightly Complex Structure | Overkill for very small systems |

---

# When to Use

Use the State Pattern when:
- object behavior changes based on state
- multiple state transitions exist
- workflows/lifecycles are involved
- many `if-else` conditions appear

---

# Common Use Cases

- Order Management Systems
- ATM Machines
- Elevator Systems
- Traffic Signals
- Vending Machines
- Media Players
- Workflow Engines

---

# Interview Tips

## Strong Signal

Whenever you see:
- lifecycle management
- workflow transitions
- mode changes
- state-dependent behavior

think:
> “Can State Pattern be used here?”

---

## Common Interview Problems

| Problem | Usage |
|---|---|
| Elevator System | Moving Up / Down / Idle |
| Vending Machine | Idle / Has Money / Dispensing |
| Order System | Created / Paid / Delivered |
| Traffic Signal | Red / Green / Yellow |

---

# Key Learning

The State Pattern helps convert:

```text
Huge if-else chains
```

into:

```text
Clean state-specific classes
```

making the system:
- modular
- scalable
- maintainable
- extensible