package creational.builder.sample.fluent;

import java.util.ArrayList;
import java.util.List;

public class PizzaBuilder {
    private String size;
    private String crust;
    private boolean cheese;
    private final List<String> toppings = new ArrayList<>();

    public PizzaBuilder setSize(String size) {
        this.size = size;
        return this;
    }

    public PizzaBuilder setCrust(String crust) {
        this.crust = crust;
        return this;
    }

    public PizzaBuilder addCheese() {
        this.cheese = true;
        return this;
    }

    public PizzaBuilder addTopping(String topping) {
        this.toppings.add(topping);
        return this;
    }

    public Pizza build() {

        if (size == null) {
            throw new IllegalStateException("Size is required");
        }

        if (crust == null) {
            throw new IllegalStateException("Crust is required");
        }

        return new Pizza(
                size,
                crust,
                cheese,
                new ArrayList<>(toppings)
        );
    }
}