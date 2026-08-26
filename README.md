# Design Patterns in Java

A collection of commonly used Design Patterns in Java with simple, practical, and interview-oriented examples.

This repository is focused on:
- Understanding core OOP design principles
- Writing scalable and maintainable code
- Preparing for Machine Coding & LLD interviews
- Learning where and why to use each pattern

---

# Repository Structure

Each pattern contains:
- Problem Statement
- Why this pattern is needed
- UML / Flow (optional)
- Real-world analogy
- Java implementation
- Output
- Pros & Cons
- When to use
- Interview tips

---

# How LLD Interviews Actually Work

Unlike DSA, there's no single correct output to check against - the interviewer is watching HOW you get to a design, not just whether the code runs.

**The typical flow:**
1. **Clarify requirements** - the prompt is deliberately vague ("design a parking lot"). Ask about scale, what operations are needed, what's out of scope. Skipping this is the #1 way strong coders lose points early.
2. **Identify the core entities/nouns** - what are the real objects in this system (Vehicle, Spot, Ticket) before touching a single pattern
3. **Apply SOLID as you design classes** - this happens BEFORE you reach for any named pattern. Patterns are a consequence of good class design, not the starting point
4. **Draw the class diagram (UML)** - relationships (has-a vs is-a), interfaces vs concrete classes, who depends on who
5. **Reach for a pattern only where the problem naturally calls for one** - if you can't say what breaks WITHOUT the pattern, you don't need it yet
6. **Code the core flow** - not the entire system, the 2-3 classes that matter most
7. **Handle the "what if" follow-up** - "what if we now need to support X" - this is where a well-decoupled design (open for extension, closed for modification) visibly pays off or visibly falls apart

**What's actually being evaluated:**
- Can you turn a vague prompt into a clean class model, unprompted
- Do your classes have single, clear responsibilities (SRP in practice, not just as a term you can define)
- Is the design extensible without editing existing classes when the interviewer adds a new requirement
- Can you justify WHY you picked a pattern - "because it decouples X from Y, so adding a new Z doesn't touch existing code" is the answer being fished for, not the pattern's name
- Thread-safety awareness where it's relevant (Singleton, shared resources, concurrent bookings)

---

# Design Patterns Priority List

Ranked by how often they show up in LLD & system-design interviews — not by GoF grouping. Per pattern: where it shows up in real prompts, what's actually being tested, and the mistake that gives away shallow understanding.

## Master Tier — learn these cold, be able to code from memory under pressure

| Pattern | Type | Status | Shows Up In | What's Actually Being Tested | Common Mistake |
|---|---|---|---|---|---|
| Strategy | Behavioral | ✅ | Cache eviction (LRU/LFU), payment methods, sorting/ranking rules, discount calculation | Can you swap an algorithm at runtime without an if-else ladder, via composition not inheritance | Using inheritance + method overriding instead of composition + interface - defeats the whole point |
| Observer | Behavioral | ✅ | Stock ticker, notification systems, pub/sub, UI event handling | Do you decouple the "thing that changes" from "things that react to it" - and do you handle a subject with many observers cleanly | Forgetting to handle observer removal/cleanup - memory leaks from dangling references are a real follow-up question |
| Factory | Creational | ✅ | Payment gateway (Razorpay/Stripe/PayPal), vehicle creation in a parking lot, shape/notification creators | Do you hide object CREATION logic from the client, so adding a new type doesn't touch calling code | Just writing a big switch/if-else inside a method called "factory" - that's not decoupling, it's the same coupling with a new name |
| Singleton | Creational | ✅ | Config manager, logger, connection pool, cache instance | Do you actually understand WHY only 1 instance should exist here, and can you make it thread-safe (double-checked locking / enum singleton) | Reciting `getInstance()` without being able to explain the thread-safety problem it has to solve - this pattern gets probed hardest on the "why" |
| Decorator | Structural | ✅ | Adding toppings to a pizza/coffee order, adding compression/encryption layers to a stream, adding UI widget behaviors | Can you add behavior to ONE object at runtime without touching the class or creating a subclass explosion | Confusing this with Inheritance - if you're subclassing for every combination, you've missed the point of the pattern |
| Adapter | Structural | ✅ | Integrating a legacy or third-party library with an incompatible interface | Can you make 2 incompatible interfaces work together without modifying either one | Reaching for Adapter when you actually control both interfaces - if you can just change one of them, you don't need this pattern |
| Facade | Structural | ✅ | Simplifying a complex subsystem (e.g. hiding a multi-step checkout/booking flow behind 1 call) | Do you know when to hide complexity vs when hiding it removes needed flexibility from the caller | Treating Facade as "just a wrapper class" without articulating what subsystem complexity it's actually hiding |
| Chain of Responsibility | Behavioral | ⌛ TODO | Logging levels, middleware pipelines, approval workflows (expense approval by manager tier), request validation pipelines | Can you let a request pass through a series of handlers where each decides "handle it or pass it on," without the caller knowing how many handlers exist | Not handling the "no handler matched" case - what happens at the end of the chain is a real edge case interviewers check |

## High Priority Tier

| Pattern | Type | Status | Shows Up In | What's Actually Being Tested | Common Mistake |
|---|---|---|---|---|---|
| Abstract Factory | Creational | ⌛ TODO | Cross-platform UI kits, creating families of related objects (e.g. a "theme" that produces matching buttons/checkboxes) | Can you ensure a whole FAMILY of related objects stays consistent, not just one object at a time | Not being able to explain how this differs from plain Factory - if there's only 1 product type, you don't need Abstract Factory |
| Builder | Creational | ✅ | Constructing objects with many optional fields (HTTP request builder, meal order, complex config object) | Do you avoid telescoping constructors and give the caller a readable, safe way to build a complex object step by step | Building a Builder for a class with 2-3 fields - this is over-engineering a problem that didn't need it |
| State | Behavioral | ✅ | Vending machine, order status lifecycle (placed → shipped → delivered), traffic light, media player | Do state TRANSITIONS live in the state objects themselves, not as a giant if-else keyed on an enum | Keeping all transition logic in one class with a switch on current state - that's not the State pattern, that's just a state machine without the pattern's benefit |
| Template Method | Behavioral | ⌛ TODO | Data processing pipelines with fixed steps but varying implementation (e.g. different file parsers sharing a read→parse→validate→save skeleton) | Can you fix the algorithm's SKELETON in a base class while letting subclasses override only the steps that vary | Overriding the whole algorithm instead of just the varying steps - defeats the "template" part |
| Command | Behavioral | ⌛ TODO | Undo/redo systems, task queues, remote controls, transactional operations | Can you encapsulate a REQUEST as an object so it can be queued, logged, undone, or retried later | Not separating the command's execute from its undo logic - undo is usually the actual interview follow-up |
| Proxy | Structural | ✅ | Lazy-loading expensive objects, access control layers, caching a remote/expensive call | Do you understand which proxy type you need - virtual (lazy load), protection (access control), or caching/remote | Conflating Proxy with Decorator - Proxy controls ACCESS to an object, Decorator adds BEHAVIOR to one; mixing these up is a common tell |

## Good to Know Tier

| Pattern | Type | Status | Shows Up In | What's Actually Being Tested | Common Mistake |
|---|---|---|---|---|---|
| Composite | Structural | ✅ | File system trees, org charts, UI component trees (panels containing panels) | Can you treat a single object and a group of objects through the SAME interface | Adding type-checks ("is this a leaf or a composite?") in client code - that's exactly what the pattern is supposed to eliminate |
| Iterator | Behavioral | ⌛ TODO | Custom collection traversal without exposing internal structure | Do you decouple traversal logic from the collection's internal representation | Exposing the internal data structure directly instead of hiding it behind the iterator interface |
| Dependency Injection | Modern / Arch | ⌛ TODO | Any testable, layered system - services receiving their dependencies instead of constructing them | Can you explain WHY constructor injection makes a class testable (swap in a mock) where `new SomeService()` inside the class can't | Only knowing the term from a framework (Spring `@Autowired`) without being able to write it by hand in a framework-free interview |
| Repository | Modern / Arch | ⌛ TODO | Abstracting data access from business logic (so the DB/ORM is swappable) | Do your service classes depend on an interface, not directly on SQL/ORM calls | Letting query logic leak into the service layer "just this once" - that's the abstraction breaking under a follow-up requirement |
| Bridge | Structural | ✅ | Decoupling an abstraction from its implementation so both vary independently (e.g. remote control working across different device brands) | Can you tell this apart from Adapter - Bridge is designed upfront for 2 axes of variation, Adapter retrofits 2 already-existing incompatible things | Using Bridge when Strategy would do - if there's only 1 axis of variation, it's Strategy, not Bridge |

> **Note:** Dependency Injection and Repository aren't part of the classic GoF set, but at SDE3 level they get asked more than half the GoF structural patterns — testability and data-layer abstraction are core senior signals, so they're included here.

## Low Frequency Tier — recognize these, don't over-invest study time

| Pattern | Type | Status | Shows Up In | What's Actually Being Tested |
|---|---|---|---|---|
| Prototype | Creational | ✅ | Cloning expensive-to-construct objects (game entities, deep-copied config objects) | Understanding shallow vs deep copy pitfalls |
| Flyweight | Structural | ✅ | Large numbers of similar objects sharing common state (text rendering, game particle systems) | Separating intrinsic (shared) from extrinsic (per-instance) state |
| Mediator | Behavioral | ⌛ TODO | Complex many-to-many object communication (chat room, air traffic control) | Centralizing communication instead of objects referencing each other directly |
| Memento | Behavioral | ⌛ TODO | Undo functionality that needs to snapshot and restore state | Capturing state without violating encapsulation |
| Visitor | Behavioral | ⌛ TODO | Adding new operations across a class hierarchy without modifying the classes | Double-dispatch mechanics |
| Interpreter | Behavioral | ⌛ TODO | Small grammar/expression parsing (rule engines, simple query languages) | Recognizing when NOT to use this - most "parsing" problems don't need a full Interpreter pattern |

---

# Keyword → Pattern Cheat Sheet

| Phrase in the Problem | Reach For |
|---|---|
| "Behavior/algorithm needs to be swappable at runtime" | Strategy |
| "Notify multiple dependents when something changes" | Observer |
| "Object creation should be decoupled from usage" | Factory |
| "Exactly 1 instance across the whole app" | Singleton |
| "Add behavior to an object dynamically, without subclass explosion" | Decorator |
| "Make 2 incompatible interfaces work together" | Adapter |
| "Simplify a complex subsystem behind 1 clean call" | Facade |
| "Request passes through a series of handlers until one handles it" | Chain of Responsibility |
| "Families of related objects need to be created together, consistently" | Abstract Factory |
| "Complex object construction with many optional parameters" | Builder |
| "Behavior changes based on internal state, transitions matter" | State |
| "Fixed algorithm skeleton, steps vary by subclass" | Template Method |
| "Encapsulate a request as an object (undo/redo, queue, log)" | Command |
| "Control or delay access to an object" | Proxy |
| "Treat individual objects and groups uniformly (tree structure)" | Composite |
| "Traverse a collection without exposing its internals" | Iterator |
| "Classes shouldn't construct their own dependencies (testability)" | Dependency Injection |
| "Abstract the data access layer from business logic" | Repository |
| "Decouple an abstraction from its implementation, both vary independently" | Bridge |
| "Clone existing objects instead of building from scratch" | Prototype |
| "Huge number of similar objects, share state to save memory" | Flyweight |
| "Many objects need to communicate without referencing each other directly" | Mediator |
| "Save/restore an object's state (undo)" | Memento |
| "Add new operations to a class hierarchy without modifying it" | Visitor |
| "Parse/evaluate a simple grammar or expression" | Interpreter |

---

# LLD Practice Problems (Beyond Patterns)

Real LLD interviews almost never say "implement Singleton." They say "design a parking lot" or "design a vending machine," and expect you to combine OOP + SOLID + multiple patterns into one working system live, in 30-45 minutes. Pattern-in-isolation practice (everything above) is necessary but not sufficient - this is the part that's actually missing until now.

Each problem gets its own folder (`lld-problems/01_parking_lot/` etc.), same depth as your pattern folders: problem statement, requirement clarification, class diagram, Java implementation, and a note on which patterns you used and why.

## Tier 1 - Foundational (single-actor, simpler state machines - start here)
| # | Problem | Patterns/Concepts Tested | Companies | Status |
|---|---------|---------------------------|-----------|--------|
| 1 | Parking Lot | Strategy (fee calc), Factory (vehicle types), Singleton (manager) | Amazon, Microsoft, Google, Uber | 🔲 TODO (01_parking_lot) |
| 2 | Vending Machine | State | Amazon, Microsoft | 🔲 TODO (02_vending_machine) |
| 3 | ATM | State, Strategy | Amazon, Microsoft, Visa | 🔲 TODO (03_atm) |
| 4 | Tic-Tac-Toe | Basic OOP, Strategy (win-check) | Amazon, Google | 🔲 TODO (04_tic_tac_toe) |

## Tier 2 - Core Pattern Practice
| # | Problem | Patterns/Concepts Tested | Companies | Status |
|---|---------|---------------------------|-----------|--------|
| 1 | Elevator System | State, Strategy (scheduling), Observer | Amazon, Microsoft, Google, Uber | 🔲 TODO (05_elevator_system) |
| 2 | Library Management System | Factory, Observer (due-date alerts) | Amazon, Microsoft | 🔲 TODO (06_library_management) |
| 3 | Chess | Strategy (move validation per piece), Command (undo), Memento | Amazon, Google, Microsoft | 🔲 TODO (07_chess) |
| 4 | Snake & Ladder | Basic OOP, Strategy | Amazon | 🔲 TODO (08_snake_and_ladder) |
| 5 | LRU/LFU Cache | HashMap + Doubly Linked List design (classic machine coding, no single GoF pattern) | Amazon, Google, Microsoft, Meta | 🔲 TODO (09_lru_lfu_cache) |
| 6 | Rate Limiter (LLD) | Strategy (Token Bucket / Sliding Window swap) | Amazon, Google, Stripe | 🔲 TODO (10_rate_limiter) |
| 7 | Logging Framework | Singleton, Chain of Responsibility (log levels), Strategy (output destination) | Amazon, Microsoft | 🔲 TODO (11_logging_framework) |
| 8 | Task Scheduler | Command, Strategy (priority), Observer | Amazon, Google | 🔲 TODO (12_task_scheduler) |

## Tier 3 - Multi-Actor / High-Frequency Interview Questions
| # | Problem | Patterns/Concepts Tested | Companies | Status |
|---|---------|---------------------------|-----------|--------|
| 1 | BookMyShow / Movie Ticket Booking | Factory, Observer, concurrency (seat locking) | Amazon, Microsoft, BookMyShow | 🔲 TODO (13_movie_ticket_booking) |
| 2 | Hotel Management System | Factory, Strategy (pricing), Observer | Amazon, Microsoft | 🔲 TODO (14_hotel_management) |
| 3 | Splitwise / Expense Sharing | Strategy (equal/exact/percent split), Observer | Amazon, Google | 🔲 TODO (15_splitwise) |
| 4 | Amazon Locker | Strategy (size assignment), State | Amazon | 🔲 TODO (16_amazon_locker) |
| 5 | Meeting Room Scheduler | Strategy, concurrency (double-booking prevention) | Amazon, Microsoft, Google | 🔲 TODO (17_meeting_room_scheduler) |
| 6 | Pub/Sub System | Observer, Singleton | Amazon, Google | 🔲 TODO (18_pub_sub_system) |

## Tier 4 - Advanced / Leverage Your Blinkit Background
| # | Problem | Patterns/Concepts Tested | Companies | Status |
|---|---------|---------------------------|-----------|--------|
| 1 | Ride Sharing (Uber LLD) | Strategy (matching), State (ride lifecycle), Observer | Uber, Amazon, Google | 🔲 TODO (19_ride_sharing) |
| 2 | Food Delivery (Swiggy/Zomato/Blinkit-style) | State (order lifecycle), Strategy (delivery partner assignment), Observer | Swiggy, Zomato, Blinkit | 🔲 TODO (20_food_delivery) |
| 3 | Payment Gateway | Strategy (payment method), State (transaction status), Chain of Responsibility (fraud checks) | Amazon, Stripe, PayPal | ✅ partial (reuse factory-pattern/payment-gateway-system) |
| 4 | Social Media Feed / Notification Service | Observer, Strategy (ranking), Factory | Meta, Amazon | 🔲 TODO (21_social_feed_notifications) |

---

# Concurrency & Thread-Safety for LLD

This is the follow-up question that separates "I can design" from "I can design for production," and it comes up on any problem with shared mutable state.

**Core things to actually know, not just name-drop:**
- `synchronized` (method vs block) - what it locks and why locking too broadly kills throughput
- `ReentrantLock` - when it's worth the extra control over plain `synchronized` (tryLock with timeout, fairness)
- `ConcurrentHashMap` vs `synchronized HashMap` - segment-level locking vs whole-map locking
- Atomic types (`AtomicInteger`, `AtomicReference`) - lock-free updates for simple counters
- Thread-safe Singleton - double-checked locking with `volatile`, or the static holder idiom, or enum singleton - be able to explain WHY the naive version breaks under concurrent first-access
- Producer-consumer via `BlockingQueue` - the standard answer for task queues / job schedulers

**Which of your practice problems actually need this said out loud:**
- Parking Lot - 2 cars grabbing the same spot at once
- Ticket/Movie booking - 2 users booking the same seat
- Rate Limiter - the counter itself is shared, concurrent requests must not double-count
- Inventory-adjacent problems (ties back to your Blinkit background) - concurrent stock decrement on purchase

---

# Practice Habits That Actually Move the Needle (Beyond Knowledge)

- **Timed mock rounds** - a blank prompt, 30-45 minutes, no notes, no LLM. Knowing 25 patterns cold doesn't help if you freeze on "where do I even start." This is a rehearsal skill, not a knowledge gap, and it's only built by doing it under a clock.
- **API/interface design instincts** - method names that read like a contract, deciding interface vs concrete class before writing either, constructors that don't take 8 positional args. This is what "clean code" cashes out to in an LLD round, separate from knowing pattern names.
- **Write runnable code, not just diagrams** - a design that only exists as boxes and arrows doesn't survive "okay, now code the booking flow." Every practice problem above should end in actual compiling Java, not a stopping point at the class diagram.

---

# Common LLD Interview Mistakes (Beyond Just Knowing the Patterns)

- **Pattern soup** - forcing 3 patterns into a design that only needed 1. Interviewers notice over-engineering as much as under-engineering; if you can't justify why each pattern is there, cut it.
- **Naming without justifying** - saying "I'll use Strategy here" isn't the answer, "I'll use Strategy here because the discount rule changes per promotion and I don't want an if-else that grows every time we add one" is.
- **Skipping SOLID reasoning** - patterns are a RESULT of good class design, not a checklist to apply first. If your classes already violate SRP, no pattern fixes that.
- **Jumping to code before requirements/classes** - the fastest way to lose an LLD round is writing code in the first 5 minutes instead of clarifying scope and sketching entities first.
- **Ignoring concurrency where it's relevant** - Singleton, shared caches, and booking/inventory systems all have real thread-safety follow-ups; not addressing it unprompted is a missed signal opportunity, not a neutral omission.
- **Not handling the "what if" extension** - the interviewer WILL add a requirement mid-design. A good design absorbs it by adding a class, not by editing five existing ones. If your first design can't survive that, that's the actual test, not the initial code.
