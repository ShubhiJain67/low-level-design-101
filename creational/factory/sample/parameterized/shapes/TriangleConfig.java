package creational.factory.sample.parameterized.shapes;
import creational.factory.sample.parameterized.interfaces.*;

public record TriangleConfig(double base, double height) implements IShapeConfig {
}
