package structural.bridge.sample;

import structural.bridge.sample.colors.*;
import structural.bridge.sample.shapes.*;

public class Main {
    public static void main(String[] args) {
        IColor redColor = new RedColor();
        IColor blueColor = new BlueColor();
        Shape redCircle = new Circle(redColor);
        Shape blueCircle = new Circle(blueColor);

        redCircle.draw();
        blueCircle.draw();


        IColor greenColor = new GreenColor();
        Shape redSquare = new Square(redColor);
        Shape blueSquare = new Square(blueColor);
        Shape greenSquare = new Square(greenColor);


        redSquare.draw();
        blueSquare.draw();
        greenSquare.draw();
    }
}
