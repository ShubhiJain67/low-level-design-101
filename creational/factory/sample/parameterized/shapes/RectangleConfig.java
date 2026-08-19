package creational.factory.sample.parameterized.shapes;
import creational.factory.sample.parameterized.interfaces.*;

public record RectangleConfig(double length, double breadth) implements IShapeConfig {
}
