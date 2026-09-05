package creational.builder.sample.classic;

public class PizzaDirector {

    public Pizza construct(IPizzaBuilder builder) {
        builder.buildSize();
        builder.buildCrust();
        builder.buildToppings();
        builder.buildCheese();

        return builder.getPizza();
    }
}