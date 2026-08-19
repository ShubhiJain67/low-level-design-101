package creational.factory.sample.parameterized.shapes;
import creational.factory.sample.parameterized.interfaces.*;

public class Rectangle implements IShape {
    private final double length;
    private final double breadth;

    public Rectangle(RectangleConfig config) {
        this.length = config.length();
        this.breadth = config.breadth();
    }

    @Override
    public void draw() {
        System.out.println("Drawing a Rectangle " + length + " x " + breadth);
    }
}
