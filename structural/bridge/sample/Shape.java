package structural.bridge.sample;

// Abstraction — holds a reference to the Implementor instead of extending it.
public abstract class Shape {
    protected IColor color;

    protected Shape(IColor color) {
        this.color = color;
    }

    public abstract void draw();
}
