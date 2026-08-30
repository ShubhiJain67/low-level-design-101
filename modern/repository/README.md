# Repository Pattern

`Modern / Arch` · `Good to Know Tier` · ✅ Built

Mediates between the domain and data-mapping layers using a collection-like interface for accessing domain objects (Martin Fowler, *Patterns of Enterprise Application Architecture*).

Not a classic GoF pattern — it comes from PoEAA and Eric Evans' *Domain-Driven Design* — but it's one of the highest-frequency "modern" patterns in real interviews, because it's how almost every production service keeps its business logic independent of its database.

---

## Real-World Analogy

A librarian. You ask for "the copy of *Dune*," not which shelf, aisle, or filing system it's in. Whether the library uses a card catalog or a barcode scanner internally is invisible to you — you interact with one simple interface: ask, receive, return.

## UML

```text
UserService --> UserRepository (interface) <-- InMemoryUserRepository
                                                 (or JdbcUserRepository,
                                                  MongoUserRepository, ...)
```

## Advantages vs Disadvantages

| ✅ Advantages | ❌ Disadvantages |
|---|---|
| Business logic depends on an interface, never on SQL/ORM/file details — the storage technology is swappable without touching services | An extra abstraction layer for what could, for a trivial app, be a single DAO call |
| Trivial to unit-test business logic with an in-memory or fake repository — no real database needed | Can leak into being a thin, pointless wrapper around an ORM if it just re-exposes ORM methods 1:1 with no real abstraction |
| Centralizes query/data-access logic in one place instead of scattering it across services | Doesn't remove the need for a real data-mapping layer underneath — it just gives that layer a clean seam |

## How It Follows SOLID

- **Dependency Inversion** — `UserService` depends on the `UserRepository` interface, an abstraction; the concrete `InMemoryUserRepository` depends on that same abstraction too. Neither the high-level service nor the low-level storage code depends on the other directly.
- **Single Responsibility** — `UserService` owns business rules; `UserRepository` implementations own persistence. Mixing SQL into `UserService` would violate this immediately.
- **Open/Closed** — adding a new storage backend means writing a new class that implements `UserRepository`; `UserService` and every existing repository implementation stay untouched.

## When To Use vs Skip

| ✅ Use it when | ❌ Skip it when |
|---|---|
| Business logic should be testable without hitting a real database | The app is a small script or prototype where the ORM/DB call *is* the whole app — no business logic to protect |
| The storage technology might change, or you need multiple implementations (real DB in prod, in-memory for tests) | You're already using an ORM's own repository/query abstraction and would just be wrapping it with no added behavior |
| Query/data-access logic is non-trivial and would otherwise get duplicated across services | |

## Related Patterns

- **vs Dependency Injection** — different layers, commonly paired: Repository abstracts *data access*; DI is *how* a service receives its Repository (via its constructor) instead of constructing one itself. This repo's `UserService` takes a `UserRepository` through constructor injection — the two patterns working together in the same 5 files.
- **vs Facade** — both hide complexity behind a simpler interface, but Facade simplifies a subsystem of *operations* (multiple services, a multi-step flow); Repository specifically simplifies *data access*, presenting persistence as a collection-like interface (`save`, `findById`, `findAll`, `deleteById`).
- **vs Factory** — Factory's job ends once an object is *created*; Repository's job covers an object's full data lifecycle — save, retrieve, update, delete — not just construction.

## Interview Line

> "I put a Repository interface between the service layer and the data store, so UserService only ever talks to UserRepository — swapping the in-memory store for a real database, or mocking it in tests, never touches the service's business logic."

---

## Implementations

[FULL CODE](sample/UserRepository.java) · [`InMemoryUserRepository`](sample/InMemoryUserRepository.java) · [`UserService`](sample/UserService.java) · [`User`](sample/User.java)

```java
public interface UserRepository {
    void save(User user);
    Optional<User> findById(int id);
    List<User> findAll();
    void deleteById(int id);
}

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void registerUser(int id, String name) {
        userRepository.save(new User(id, name));
    }
    // ...
}
```

`InMemoryUserRepository` implements `UserRepository` with a `Map` standing in for a real database. `UserService` never references `InMemoryUserRepository` by name — only [`sample/Main.java`](sample/Main.java), the composition root, wires the concrete implementation in:

```java
UserRepository repository = new InMemoryUserRepository();
UserService userService = new UserService(repository);
```

Swap that one line for `new JdbcUserRepository(...)` later, and `UserService` doesn't change at all.

---

Full breakdown — where this shows up in interviews, what's actually tested, and the common mistake — lives in the Repository row of the [root README](../../README.md).
