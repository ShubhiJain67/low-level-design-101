package creational.factory.sample.parameterized.shapes;
import creational.factory.sample.parameterized.interfaces.*;

public record SquareConfig(double side) implements IShapeConfig {
}
