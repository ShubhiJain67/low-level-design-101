package creational.factory.sample.parameterized.shapes;
import creational.factory.sample.parameterized.interfaces.IShape;

public class Circle implements IShape {
    private final double radius;

    public Circle(CircleConfig config) {
        this.radius = config.radius();
    }

    @Override
    public void draw() {
        System.out.println("Drawing a Circle with radius " + radius);
    }
}
