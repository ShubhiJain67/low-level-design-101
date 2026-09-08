package structural.bridge.sample.shapes;

import structural.bridge.sample.IColor;
import structural.bridge.sample.Shape;

public class Circle extends Shape {
    public Circle(IColor color) {
        super(color);
    }

    @Override
    public void draw() {
        System.out.println("Circle filled with " + color.fill());
    }
}
