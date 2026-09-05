package creational.builder.sample.step;

import java.util.ArrayList;
import java.util.List;

public class PizzaBuilder implements ISizeStep, ICrustStep, IOptionalStep {
    private String size;
    private String crust;
    private boolean cheese;
    private final List<String> toppings = new ArrayList<>();

    public static ISizeStep builder() {
        return new PizzaBuilder();
    }

    @Override
    public ICrustStep setSize(String size) {
        this.size = size;
        return this;
    }

    @Override
    public IOptionalStep setCrust(String crust) {
        this.crust = crust;
        return this;
    }

    @Override
    public IOptionalStep addCheese() {
        this.cheese = true;
        return this;
    }

    @Override
    public IOptionalStep addTopping(String topping) {
        toppings.add(topping);
        return this;
    }

    @Override
    public Pizza build() {
        return new Pizza(size, crust, cheese, new ArrayList<>(toppings));
    }
}
