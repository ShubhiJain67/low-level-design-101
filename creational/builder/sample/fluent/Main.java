package creational.builder.sample.fluent;

public class Main {
    public static void main(String[] args) {
        PizzaBuilder builder = new PizzaBuilder()
                .setCrust("Thin")
                .setSize("Large");
        // Operation
        builder.setSize("Medium");
        Pizza pizza = builder.build(); // Medium 
        pizza.showPizza();
    }
}
