package structural.decorator.sample.CoffeeDecorators;

import structural.decorator.sample.CoffeeDecorator;
import structural.decorator.sample.ICoffee;

public class MilkDecorator extends CoffeeDecorator {
    public MilkDecorator(ICoffee coffee) {
        super(coffee);
    }

    @Override
    public String description() {
        return this.coffee.description() + " + Milk";
    }

    @Override
    public double cost() {
        return this.coffee.cost() + 10;
    }
}
