package creational.builder.sample.classic;

import java.util.Arrays;

public class ExtraCheeseVegPizzaBuilder implements IPizzaBuilder {
    private final Pizza pizza = new Pizza();

    @Override
    public void buildSize() {
        pizza.setSize("Large");
    }

    @Override
    public void buildCrust() {
        pizza.setCrust("Thick");
    }

    @Override
    public void buildCheese() {
        pizza.setCheese(2);
    }

    @Override
    public void buildToppings() {
        pizza.setToppings(Arrays.asList("Mushroom", "Olives", "Capsicum"));
    }

    @Override
    public Pizza getPizza() {
        return pizza;
    }
}
