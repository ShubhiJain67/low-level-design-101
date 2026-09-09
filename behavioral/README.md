# Behavioral Design Patterns

Behavioral patterns are concerned with algorithms and the assignment of responsibility between objects — how objects communicate and share work, as opposed to how they're created (Creational) or composed into larger structures (Structural).

If a problem sounds like "this object's behavior needs to change based on X," "multiple objects need to react automatically when one object changes," or "the same task can be done in more than one way" — it's usually a behavioral pattern.

---

## Patterns in this folder

| Pattern | Status | Solves | Sample project | README |
|---|---|---|---|---|
| Strategy | ✅ Built | Same task, multiple interchangeable algorithms — swap the algorithm at runtime without an if-else ladder | [Cache eviction policies](strategy-pattern/cache-eviction-policies) (LRU / LFU / FIFO / MRU / Random / TTL) | [strategy-pattern/README.md](strategy-pattern/README.md) |
| Observer | ✅ Built | One object's state changes, many dependent objects need to know automatically | [Stock market ticker](observer-pattern/stock-market) (Subject = Stock, Observers = Trader) | [observer-pattern/README.md](observer-pattern/README.md) |
| State | ✅ Built | An object's allowed behavior changes depending on its current state, without a giant if-else/switch keyed on that state | [Vending machine](state-pattern/vending-machine) (Idle → HasMoney → Dispensing → OutOfStock) | [state-pattern/README.md](state-pattern/README.md) |

Only 3 of the 11 GoF behavioral patterns are built here so far — unlike `structural/`, which is complete.

## Not yet built here

Per the root [Design Patterns Priority List](../README.md#design-patterns-priority-list), these behavioral patterns don't have a folder yet:

| Pattern | Priority tier | Shows up in |
|---|---|---|
| Chain of Responsibility | Master Tier | Logging levels, middleware pipelines, approval workflows |
| Template Method | High Priority | Data pipelines with a fixed skeleton, varying steps |
| Command | High Priority | Undo/redo, task queues, remote controls |
| Iterator | Good to Know | Custom collection traversal without exposing internals |
| Mediator | Low Frequency | Many-to-many object communication (chat room, air traffic control) |
| Memento | Low Frequency | Undo / snapshot-restore |
| Visitor | Low Frequency | Adding operations across a class hierarchy without modifying it |
| Interpreter | Low Frequency | Small grammar/expression parsing |

---

## Telling the 3 built patterns apart

They get confused with each other because all three change what an object *does* — the real difference is **who decides, and when**.

| | Strategy | Observer | State |
|---|---|---|---|
| Who picks the behavior | The client, explicitly | Nobody "picks" — every observer just reacts | The object itself, based on its own internal state |
| Relationship | One active algorithm at a time | One-to-many broadcast | Object delegates to its current state object |
| Changes at | Client's discretion, any time | Whenever the subject's state changes | Automatically, as part of a state transition |
| Ask yourself | "Do I have several interchangeable ways to do the same thing?" | "Do multiple things need to react automatically when one thing changes?" | "Does this object's allowed behavior depend on what state it's currently in?" |

---

## Keyword → Pattern Cheat Sheet

The behavioral rows from the root README's full cheat sheet — same wording, filtered to this category.

| Phrase in the Problem | Reach For |
|---|---|
| "Behavior/algorithm needs to be swappable at runtime" | Strategy |
| "Notify multiple dependents when something changes" | Observer |
| "Behavior changes based on internal state, transitions matter" | State |
| "Request passes through a series of handlers until one handles it" | Chain of Responsibility |
| "Fixed algorithm skeleton, steps vary by subclass" | Template Method |
| "Encapsulate a request as an object (undo/redo, queue, log)" | Command |
| "Traverse a collection without exposing its internals" | Iterator |
| "Many objects need to communicate without referencing each other directly" | Mediator |
| "Save/restore an object's state (undo)" | Memento |
| "Add new operations to a class hierarchy without modifying it" | Visitor |
| "Parse/evaluate a simple grammar or expression" | Interpreter |

---

For the full interview-priority ranking, the complete cheat sheet across all categories, and the concurrency section, see the [root README](../README.md).
