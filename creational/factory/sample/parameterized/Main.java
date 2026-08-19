package creational.factory.sample.parameterized;
import creational.factory.sample.parameterized.interfaces.*;
import creational.factory.sample.parameterized.shapes.*;

public class Main {
    public static void main(String[] args) {
        bootstrap.Setup();

        IShape circle = ShapeFactory.getShape(ShapeType.CIRCLE, new CircleConfig(5.0));
        circle.draw();

        IShape square = ShapeFactory.getShape(ShapeType.SQUARE, new SquareConfig(4.0));
        square.draw();

        IShape rectangle = ShapeFactory.getShape(ShapeType.RECTANGLE, new RectangleConfig(6.0, 3.0));
        rectangle.draw();

        IShape triangle = ShapeFactory.getShape(ShapeType.TRIANGLE, new TriangleConfig(4.0, 3.0));
        triangle.draw();
    }

}
