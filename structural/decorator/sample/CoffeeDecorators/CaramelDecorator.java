package structural.decorator.sample.CoffeeDecorators;

import structural.decorator.sample.CoffeeDecorator;
import structural.decorator.sample.ICoffee;

public class CaramelDecorator extends CoffeeDecorator {
    public CaramelDecorator(ICoffee coffee) {
        super(coffee);
    }

    @Override
    public String description() {
        return this.coffee.description() + " + Caramel";
    }

    @Override
    public double cost() {
        return this.coffee.cost() + 15;
    }
}
