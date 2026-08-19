package creational.factory.sample.simple;

public class ShapeFactory {

    public static ShapeFactory getInstance() {
        return new ShapeFactory();
    }
    
    // Here we are returning IShape interface, so that we can return any shape object that implements IShape interface.
    public IShape getShape(ShapeType shape) {
        // Whenever a new shape is added,
        // 1. we need to modify this method to add a new condition for that shape.
        // 2. Add a new class for the shape that implements IShape interface.

        // This Enum can be a String as well. -> Better to maintain a ShapeType enum and use that instead of string.
        // As it can be used anywhere else as well with single source of truth. 
        // If we use string, then we need to maintain the string in multiple places
        switch (shape) {
            case CIRCLE -> {
                return new Circle();
            }
            case SQUARE -> {
                return new Square();
            }
            case TRIANGLE -> {
                return new Triangle();
            }
            default -> throw new IllegalArgumentException("Unknown type: " + shape);
        }
    }
}
