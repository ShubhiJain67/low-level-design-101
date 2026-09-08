package structural.bridge.sample;

import structural.bridge.sample.colors.*;
import structural.bridge.sample.shapes.*;

public class Main {
    public static void main(String[] args) {
        Shape redCircle = new Circle(new RedColor());
        Shape blueCircle = new Circle(new BlueColor());
        redCircle.draw();
        blueCircle.draw();
        Shape redSquare = new Square(new RedColor());
        Shape blueSquare = new Square(new BlueColor());
        redSquare.draw();
        blueSquare.draw();
    }
}
