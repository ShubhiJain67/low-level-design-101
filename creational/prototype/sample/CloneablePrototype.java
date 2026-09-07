package creational.prototype.sample;

// Common contract so a registry can call clone() polymorphically,
// without needing to know the concrete class it's cloning.
public interface CloneablePrototype {
    CloneablePrototype clone() throws CloneNotSupportedException;
}
