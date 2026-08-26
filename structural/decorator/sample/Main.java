package structural.decorator.sample;

import structural.decorator.sample.CoffeeDecorators.CaramelDecorator;
import structural.decorator.sample.CoffeeDecorators.MilkDecorator;

public class Main {
    public static void main(String[] args) {
        ICoffee coffee = new SimpleCoffee();
        System.out.println(coffee.description() + " -> " + coffee.cost());

        ICoffee milkCoffee = new MilkDecorator(coffee);
        System.out.println(milkCoffee.description() + " -> " + milkCoffee.cost());

        ICoffee milkCaramelCoffee = new CaramelDecorator(new MilkDecorator(coffee));
        System.out.println(milkCaramelCoffee.description() + " -> " + milkCaramelCoffee.cost());
    }
}
