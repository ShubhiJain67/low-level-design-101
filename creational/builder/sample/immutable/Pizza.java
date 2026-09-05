package creational.builder.sample.immutable;

import java.util.List;

public class Pizza {
    private final String size;
    private final String crust;
    private final boolean cheese;
    private final List<String> toppings;

    Pizza(String size, String crust, boolean cheese, List<String> toppings) {
        this.size = size;
        this.crust = crust;
        this.cheese = cheese;
        this.toppings = toppings;
    }

    public void showPizza() {
        System.out.println("Pizza Details:");
        System.out.println("Size: " + size);
        System.out.println("Crust: " + crust);
        System.out.println("Cheese: " + cheese);
        System.out.println("Toppings: " + toppings);
    }
}
