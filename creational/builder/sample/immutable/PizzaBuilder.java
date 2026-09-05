package creational.builder.sample.immutable;

import java.util.ArrayList;
import java.util.List;

public class PizzaBuilder {

    private final String size;
    private final String crust;
    private final boolean cheese;
    private final List<String> toppings;
    
    // Initial empty builder
    public PizzaBuilder() {
        this.size = null;
        this.crust = null;
        this.cheese = false;
        this.toppings = List.of();
    }

    // Private constructor used to create the next builder state
    private PizzaBuilder(String size,String crust,boolean cheese,List<String> toppings) {
        this.size = size;
        this.crust = crust;
        this.cheese = cheese;
        this.toppings = List.copyOf(toppings);
    }

    public PizzaBuilder setSize(String size) {
        return new PizzaBuilder(size, this.crust,this.cheese,this.toppings);
    }

    public PizzaBuilder setCrust(String crust) {
        return new PizzaBuilder(this.size,crust,this.cheese,this.toppings);
    }

    public PizzaBuilder addCheese() {
        return new PizzaBuilder(this.size,this.crust,true,this.toppings);
    }

    public PizzaBuilder addTopping(String topping) {
        List<String> newToppings = new ArrayList<>(this.toppings);
        newToppings.add(topping);

        return new PizzaBuilder(this.size,this.crust,this.cheese,newToppings);
    }

    public Pizza build() {
        if (size == null) {
            throw new IllegalStateException("Pizza size is required");
        }
        if (crust == null) {
            throw new IllegalStateException("Pizza crust is required");
        }
        return new Pizza(size,crust,cheese,toppings);
    }
}