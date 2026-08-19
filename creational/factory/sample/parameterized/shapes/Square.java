package creational.factory.sample.parameterized.shapes;
import creational.factory.sample.parameterized.interfaces.*;

public class Square implements IShape {
    private final double side;

    public Square(SquareConfig config) {
        this.side = config.side();
    }

    @Override
    public void draw() {
        System.out.println("Drawing a Square with side " + side);
    }
}
