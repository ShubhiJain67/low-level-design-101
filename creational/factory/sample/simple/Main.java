package creational.factory.sample.simple;

public class Main {
    public static void main(String[] args) {
        ShapeFactory shapeFactory = ShapeFactory.getInstance();

        IShape circle = shapeFactory.getShape(ShapeType.CIRCLE);
        circle.draw();

        IShape square = shapeFactory.getShape(ShapeType.SQUARE);
        square.draw();

        IShape triangle = shapeFactory.getShape(ShapeType.TRIANGLE);
        triangle.draw();
    }
}
