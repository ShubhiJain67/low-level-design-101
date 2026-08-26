package structural.bridge.sample;

public class Circle extends Shape {
    public Circle(IColor color) {
        super(color);
    }

    @Override
    public void draw() {
        System.out.println("Circle filled with " + color.fill());
    }
}
