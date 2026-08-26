package structural.bridge.sample;

import structural.bridge.sample.colors.BlueColor;
import structural.bridge.sample.colors.RedColor;

public class Main {
    public static void main(String[] args) {
        Shape redCircle = new Circle(new RedColor());
        Shape blueCircle = new Circle(new BlueColor());
        redCircle.draw();
        blueCircle.draw();
    }
}
