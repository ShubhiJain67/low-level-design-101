package creational.factory.sample.parameterized.shapes;
import creational.factory.sample.parameterized.interfaces.*;

public class Triangle implements IShape {
    private final double base;
    private final double height;

    public Triangle(TriangleConfig config) {
        this.base = config.base();
        this.height = config.height();
    }

    @Override
    public void draw() {
        System.out.println("Drawing a Triangle, base=" + base + " height=" + height);
    }
}
