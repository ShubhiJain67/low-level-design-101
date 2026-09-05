package creational.builder.sample.classic;

public class Main {
    public static void main(String[] args) {
        IPizzaBuilder builder = new VegPizzaBuilder();
        PizzaDirector director = new PizzaDirector();
        Pizza pizza = director.construct(builder);
        pizza.showDetails();
    }
}
