package creational.builder.sample.classic;

public interface IPizzaBuilder {
    void buildSize();
    void buildCrust();
    void buildCheese();
    void buildToppings();
    Pizza getPizza();
}