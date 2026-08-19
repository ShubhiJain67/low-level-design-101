package creational.factory.sample.parameterized;

import java.util.function.Function;

import creational.factory.sample.parameterized.interfaces.*;
import creational.factory.sample.parameterized.shapes.*;

public class bootstrap {
    
    // On addition of new shape, we need to add a new entry here to register the shape with the factory.
    // NO OTHER CHANGE REQUIRED IN THE FACTORY CLASS OR THE CLIENT CODE.
    public static void Setup(){
        // Can register more factories here in future if we have more factories for different types of objects.
        registerShapes();
    }

    private static void registerShapes() {
        // public interface Function<T, R> {
        //      R apply(T t);
        // }
        ShapeFactory.register(ShapeType.CIRCLE, new Function<IShapeConfig, IShape>() {
            @Override
            public IShape apply(IShapeConfig cfg) {
                return new Circle((CircleConfig) cfg);
            }
        });

        ShapeFactory.register(ShapeType.SQUARE, new Function<IShapeConfig, IShape>() {
            @Override
            public IShape apply(IShapeConfig cfg) {
                return new Square((SquareConfig) cfg);
            }
        });

        ShapeFactory.register(ShapeType.RECTANGLE, new Function<IShapeConfig, IShape>() {
            @Override
            public IShape apply(IShapeConfig cfg) {
                return new Rectangle((RectangleConfig) cfg);
            }
        });

        ShapeFactory.register(ShapeType.TRIANGLE, new Function<IShapeConfig, IShape>() {
            @Override
            public IShape apply(IShapeConfig cfg) {
                return new Triangle((TriangleConfig) cfg);
            }
        });
    }
}
