package creational.prototype.sample;

import java.util.HashMap;
import java.util.Map;

// GoF's optional "prototype manager" — client asks for a clone by key,
// never touches a concrete class or holds a reference to a specific instance.
public class PrototypeRegistry {
    private final Map<String, Prototype> prototypes = new HashMap<>();

    public void register(String key, Prototype prototype) {
        prototypes.put(key, prototype);
    }

    public Prototype get(String key) throws CloneNotSupportedException {
        Prototype prototype = prototypes.get(key);
        if (prototype == null) {
            throw new IllegalArgumentException("No prototype registered for key: " + key);
        }
        return prototype.clone();
    }
}
