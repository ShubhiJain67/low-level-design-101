package structural.decorator.sample;

// Wraps a Coffee and satisfies the same interface — decorators can be stacked.
public abstract class CoffeeDecorator implements ICoffee {
    protected final ICoffee coffee;

    protected CoffeeDecorator(ICoffee coffee) {
        this.coffee = coffee;
    }
}
