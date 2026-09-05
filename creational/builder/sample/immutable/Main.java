package creational.builder.sample.immutable;

public class Main {
    public static void main(String[] args) {
        Pizza pizza = new PizzaBuilder()
                .addTopping("Mushroom")
                .setCrust("Thin")
                .setSize("Large")
                .addCheese()
                .addTopping("Olives")
                .build();
        pizza.showPizza();
    }
}
