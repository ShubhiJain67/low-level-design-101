package creational.builder.sample.classic;

import java.util.List;

public class Pizza {
    private String size;
    private String crust;
    private int cheese;
    private List<String> toppings;

    public void setSize(String size) {
        this.size = size;
    }

    public void setCrust(String crust) {
        this.crust = crust;
    }

    public void setCheese(int cheese) {
        this.cheese = cheese;
    }

    public void setToppings(List<String> toppings) {
        this.toppings = toppings;
    }

    public void showDetails() {
        System.out.println("Pizza Details:");
        System.out.println("Size: " + size);
        System.out.println("Crust: " + crust);
        System.out.println("Cheese: " + cheese);
        System.out.println("Toppings: " + toppings);
    }
}