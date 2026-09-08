package structural.bridge.sample.shapes;

import structural.bridge.sample.IColor;
import structural.bridge.sample.Shape;

public class Square extends Shape {
    public Square(IColor color) {
        super(color);
    }

    @Override
    public void draw() {
        System.out.println("Square filled with " + color.fill());
    }
}
