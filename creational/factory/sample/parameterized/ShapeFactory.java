package creational.factory.sample.parameterized;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import creational.factory.sample.parameterized.interfaces.*;

class ShapeFactory {
    // Function<IShapeConfig, IShape> — takes a config, returns a shape.
    // (Function<T, R> = takes a T, returns an R — this was backwards before.)
    private static final Map<ShapeType, Function<IShapeConfig, IShape>> STORE_MAP = new HashMap<>();

    public static IShape getShape(ShapeType type, IShapeConfig config) {
        Function<IShapeConfig, IShape> creator = STORE_MAP.get(type);
        if (creator == null) {
            throw new IllegalArgumentException("Unknown type: " + type);
        }
        return creator.apply(config);
    }

    // open for extension — no existing code touched to add a type
    public static void register(ShapeType type, Function<IShapeConfig, IShape> creator) {
        STORE_MAP.put(type, creator);
    }
}
