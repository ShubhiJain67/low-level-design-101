package structural.decorator.sample;

public class SimpleCoffee implements ICoffee {
    @Override
    public String description() {
        return "Coffee";
    }

    @Override
    public double cost() {
        return 50;
    }
}
