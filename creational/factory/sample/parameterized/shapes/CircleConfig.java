package creational.factory.sample.parameterized.shapes;
import creational.factory.sample.parameterized.interfaces.*;

public record CircleConfig(double radius) implements IShapeConfig {
}
