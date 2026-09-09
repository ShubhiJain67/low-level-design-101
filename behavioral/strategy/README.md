# Strategy Pattern

## Problem Statement

You have a system where the same task can be performed in multiple ways.

Example:
A payment system can process payments using:
- UPI
- Credit Card
- Net Banking
- Wallet

A naive implementation uses large `if-else` or `switch` statements.

```java
if(type.equals("UPI")) {
   // UPI payment
}
else if(type.equals("CARD")) {
   // Card payment
}
else if(type.equals("WALLET")) {
   // Wallet payment
}
```

As the application grows:
- code becomes messy
- hard to extend
- difficult to test
- violates SOLID principles

---

# Why This Pattern Is Needed

Strategy Pattern is needed when:

✅ Multiple ways exist to perform the same operation  
✅ Behavior should change dynamically at runtime  
✅ You want to avoid massive conditional logic  
✅ New behaviors should be added easily  
✅ Code should follow Open/Closed Principle

Instead of hardcoding logic:
- encapsulate each behavior separately
- make them interchangeable

---

# Real-World Analogy

Imagine going for shopping, you can shop using different payment methods.

Possible strategies:
- Card
- UPI
- Wallet

Agenda remains same:
> Pay for the product

But payment mode changes depending on chosen strategy.

You can switch payment mode anytime without changing the destination logic.

---

# UML / Flow

```text
                +----------------------+
                |   PaymentStrategy    |
                |----------------------|
                | + pay(amount)        |
                +----------+-----------+
                           ^
          -----------------------------------------
          |                  |                    |
+----------------+  +----------------+  +-------------------+
| UpiStrategy    |  | CardStrategy   |  | WalletStrategy    |
+----------------+  +----------------+  +-------------------+
| pay(amount)    |  | pay(amount)    |  | pay(amount)       |
+----------------+  +----------------+  +-------------------+

                     used by
                           |
                           v

                +----------------------+
                |   PaymentService     |
                +----------------------+
                | PaymentStrategy      |
                +----------------------+
                | processPayment()     |
                +----------------------+
```

---

# Advantages

### 1. Removes Huge If-Else Chains

Cleaner and maintainable code.

### 2. Easy to Extend

Add new strategy without modifying old code.

Example:

```java
BankTransferPaymentStrategy
```

### 3. Follows Open/Closed Principle

Open for extension  
Closed for modification

### 4. Better Testing

Each strategy can be unit tested independently.

### 5. Runtime Flexibility

Behavior can change dynamically at runtime.

---

# Disadvantages

### 1. More Classes

Can increase number of files/classes.

### 2. Slightly Complex

Overkill for very small/simple logic.

### 3. Client Must Know Strategies

Client needs awareness of available strategies.

---

# When To Use

Use Strategy Pattern when:

✅ Multiple ways exist to perform the same task  
✅ You want runtime behavior selection  
✅ Large conditional logic exists  
✅ Algorithms may change frequently  
✅ Extensibility is important

---

# When NOT To Use

❌ Only one simple algorithm exists  
❌ Logic is unlikely to change  
❌ Adding many classes adds unnecessary complexity

---

# Real Industry Use Cases

## Payment Systems
- UPI
- Credit Card
- Wallet
- COD

---

## Delivery Pricing
- Surge pricing
- Festival pricing
- Distance pricing

---

## Search Ranking
At companies like :contentReference[oaicite:0]{index=0}:
- relevance ranking
- sponsored ranking
- popularity ranking

---

## Retry Mechanisms
- exponential retry
- fixed retry
- no retry

---

## Cache Eviction
- LRU
- LFU
- FIFO

---

# Interview Tips

## 1. Key Line To Say

> “I used Strategy Pattern to remove hardcoded conditional logic and make the system extensible.”

---

## 2. Mention SOLID Principles

Especially:
- Open/Closed Principle
- Dependency Inversion Principle

---

## 3. Common Interview Questions

### Q1: Why not use if-else?
Because:
- poor scalability
- hard maintenance
- violates OCP

---

### Q2: Difference between Strategy and State Pattern?

| Strategy | State |
|---|---|
| Behavior chosen by client | Behavior changes internally |
| Independent algorithms | Object state transitions |
| Example: Payment method | Example: Order state |

---

### Q3: Can Strategy be combined with Factory?
Yes. Very common.

```java
PaymentStrategy strategy =
    PaymentFactory.getStrategy("UPI");
```
___

### Q4: Strategy vs Factory

| Strategy Pattern | Factory Pattern |
|---|---|
| Chooses behavior | Creates objects |
| Focuses on algorithms | Focuses on instantiation |
| Runtime behavior change | Object creation |
| Example: Payment type | Example: Vehicle creation |

 Often used together.
---

# How To Identify Strategy Pattern

Ask:

> “Do I have multiple interchangeable ways to perform the same operation?”

If YES → Strategy Pattern is likely applicable.

---

# Common Machine Coding Problems Using Strategy Pattern

- Parking Lot Pricing
- Splitwise Settlement
- Ride Fare Calculation
- Notification Sending
- Search Ranking
- Discount Engine
- Delivery Fee Calculation
- Cache Eviction Policies

---