# Composite Pattern

`Structural` · `Good to Know Tier` · ✅ Built

Compose objects into tree structures to represent part-whole hierarchies. Composite lets clients treat individual objects and compositions of objects uniformly.

---

## Real-World Analogy

A file system. A folder can contain files and other folders. Whether you're looking at a single file or an entire folder tree, you interact with both the same way — get size, get name, delete — without needing to know whether you're holding a leaf or a whole subtree.

## UML

```text
Component (interface: operation())
  |-- Leaf implements Component
  \-- Composite implements Component (holds a list of child Components, operation() delegates to each child)
```

## Advantages vs Disadvantages

| ✅ Advantages | ❌ Disadvantages |
|---|---|
| Client code treats individual objects (leaves) and groups of objects (composites) through the exact same interface — no type-checking needed | Can make the design overly general — hard to restrict what types of children a Composite should actually contain |
| Makes it easy to add new component types without changing client code | If Leaf and Composite have genuinely different valid operations, the shared interface either exposes methods that don't make sense for one side (e.g. `add()`/`remove()` on a Leaf) or gets awkward to design |
| Naturally models recursive, tree-shaped data — file systems, UI component trees, org charts | |

## How It Follows SOLID

- **Open/Closed** — new Leaf/Composite types can be added without changing client code, since everything goes through the Component interface.
- **Liskov Substitution** — any Component, leaf or composite, must be usable wherever a Component is expected, without the client needing to distinguish them.

## When To Use vs Skip

| ✅ Use it when | ❌ Skip it when |
|---|---|
| You have a part-whole hierarchy — a tree where "container" and "item" should be treated uniformly | The structure isn't actually a hierarchy — a flat list doesn't need Composite |
| Client code shouldn't need to distinguish between a single object and a collection of objects | Leaf and Composite have such different valid operations that a shared interface creates more confusion than it removes |
| Operations need to apply recursively down a tree without the client manually walking it | |

## Related Patterns

- **vs Decorator** — Composite models a whole-part tree, where a parent has many children; Decorator wraps ONE object to add behavior, forming a chain, not a tree.
- **vs Iterator** — often paired: Composite defines the tree structure, Iterator provides a way to traverse it without exposing the tree's internal representation.

## Interview Line

> "I used Composite so client code could treat a single item and a group of items identically — same interface, no `isLeaf` checks anywhere in the calling code."

---

## Implementations

`File` (Leaf) and `Folder` (Composite) both implement `FileSystemComponent`, so `root.showDetails("")` recurses through an arbitrarily nested tree with one call — no `instanceof` checks anywhere in `Main`. See the full tree printed in [`sample/Main.java`](sample/Main.java).

[FULL CODE](sample/Folder.java) · [`FileSystemComponent` interface](sample/FileSystemComponent.java) · [`File`](sample/File.java)

```java
public class Folder implements FileSystemComponent {
    private final String name;
    private final List<FileSystemComponent> children = new ArrayList<>();

    public void add(FileSystemComponent component) {
        children.add(component);
    }

    @Override
    public void showDetails(String indent) {
        System.out.println(indent + "Folder: " + name);
        for (FileSystemComponent child : children) {
            child.showDetails(indent + "  ");
        }
    }
}
```

`Folder.showDetails()` calls `showDetails()` on each child without caring whether that child is a `File` (prints one line) or another `Folder` (recurses further) — same interface, same call.

---

Full breakdown — where this shows up in interviews, what's actually tested, and the common mistake — lives in the Composite row of the [root README](../../README.md).
