# Vending Machine System Design

## Type
Behavioral Design Pattern — State Pattern

---

# Problem Statement

Design a Vending Machine System that allows users to:

- insert money
- select products
- dispense items
- return change
- handle invalid operations

The vending machine should behave differently depending on its current state.

Example states:

```text
IDLE → HAS_MONEY → DISPENSING → OUT_OF_STOCK
```

The system should be:
- modular
- extensible
- easy to maintain
- scalable for future features

---

# Why State Pattern Is Needed

Without the State Pattern:

- vending machine logic becomes full of `if-else`
- state transitions become difficult to manage
- invalid actions are harder to control
- code becomes tightly coupled

The State Pattern helps by:
- separating behavior by state
- managing transitions cleanly
- improving extensibility
- making code maintainable

---

# Functional Requirements

The vending machine should support:

## Core Features

- Add products to machine
- Insert money
- Select product
- Dispense product
- Return change
- Cancel transaction

---

# Non Functional Requirements

- Extensible design
- Low coupling
- Easy to add new states
- Easy to add new products
- Maintainable code structure

---

# States of Vending Machine

| State | Description |
|---|---|
| IDLE | Waiting for customer |
| HAS_MONEY | Money inserted |
| DISPENSING | Product being dispensed |
| OUT_OF_STOCK | Product unavailable |

---

# Possible State Transitions

```text
IDLE
  ↓ insertMoney
HAS_MONEY
  ↓ selectProduct
DISPENSING
  ↓ dispenseComplete
IDLE
```

Out of stock flow:

```text
HAS_MONEY
  ↓ select unavailable product
OUT_OF_STOCK
```

---

# UML / High Level Flow

```text
                +-------------------+
                |   VendingMachine  |
                +-------------------+
                          |
                          v
                 +----------------+
                 | MachineState   |
                 +----------------+
                  /      |       \
                 /       |        \
                v        v         v

           IdleState  HasMoneyState  DispensingState
```

---

# Core Entities

| Entity | Responsibility |
|---|---|
| VendingMachine | Main controller |
| Product | Product information |
| Inventory | Product stock management |
| MachineState | State behavior abstraction |
| IdleState | Waiting state |
| HasMoneyState | Money inserted state |
| DispensingState | Dispensing state |
| OutOfStockState | Product unavailable state |

---

# Real World Analogy

Think of a real vending machine.

- Without money → cannot select item
- After money → selection allowed
- During dispensing → cannot insert more money
- If product unavailable → machine rejects request

Each state changes machine behavior.

---

# Sample Flow

```text
Insert ₹50
Select Coke
Dispensing Coke
Returning ₹10
```

---

# Edge Cases

- Selecting product without money
- Insufficient balance
- Out of stock product
- Cancel transaction
- Extra money handling
- Invalid product selection

---

# Pros & Cons

## Pros

| Advantage | Description |
|---|---|
| Cleaner Design | Removes huge conditional logic |
| Extensible | Easy to add new states |
| Better Maintainability | State logic isolated |
| SOLID Principles | Follows Open/Closed Principle |
| Scalable | Easy feature expansion |

---

## Cons

| Disadvantage | Description |
|---|---|
| More Classes | Each state requires separate class |
| Initial Complexity | Slightly harder for beginners |

---

# When To Use State Pattern

Use when:
- behavior changes based on state
- workflows/lifecycles exist
- transitions are important
- large `if-else` chains appear

---

# Interview Discussion Points

## Possible Extensions

- Multiple payment methods
- Card/UPI support
- Product recommendations
- Refund system
- Admin mode
- Inventory alerts

---

# Interview Tips

## Strong Signal

Whenever you see:
- workflow transitions
- mode changes
- lifecycle behavior
- state-dependent operations

think:
> “Can State Pattern fit here?”

---

# Common Interview Questions

| Question | Focus Area |
|---|---|
| How to add new states? | Extensibility |
| How to handle invalid actions? | State isolation |
| How to support multiple payments? | Strategy Pattern |
| How to improve scalability? | Abstractions |

---

# Key Learning

The State Pattern helps convert:

```text
Huge if-else logic
```

into:

```text
Clean state-specific behavior
```

making the system:
- modular
- maintainable
- scalable
- extensible