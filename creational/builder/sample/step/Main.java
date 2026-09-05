package creational.builder.sample.step;

public class Main {

    public static void main(String[] args) {

        Pizza pizza = PizzaBuilder.builder()
                .setSize("Large")
                .setCrust("Thin")
                .addCheese()
                .addTopping("Olives")
                .addTopping("Mushroom")
                .build();

        pizza.showPizza(); 
    }
}
